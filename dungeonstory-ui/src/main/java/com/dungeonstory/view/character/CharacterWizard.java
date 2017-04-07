package com.dungeonstory.view.character;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;

public class CharacterWizard extends Wizard implements WizardProgressListener {

    private static final long serialVersionUID = -8481346074235692258L;

    protected Character original;
    protected Character character;
    protected Character characterFromPreviousStep;
    private DSClass     chosenClass;

    protected CharacterService characterService = CharacterService.getInstance();

    private ClickListener nextButtonListener;

    public final static String RACE       = "raceChoice";
    public final static String CLASS      = "classChoice";
    public final static String ABILITY    = "abilityScores";
    public final static String SPELL      = "spellChoice";
    public final static String BACKGROUND = "backgroundChoice";
    public final static String INFO       = "info";
    public final static String SUMMARY    = "summary";

    public CharacterWizard() {
        super();
        setUriFragmentEnabled(false);
        addListener(this);

        Messages messages = Messages.getInstance();
        getBackButton().setCaption(messages.getMessage("button.back"));
        getCancelButton().setCaption(messages.getMessage("button.cancel"));
        getNextButton().setCaption(messages.getMessage("button.next"));
        getFinishButton().setCaption(messages.getMessage("button.finish"));

        nextButtonListener = (ClickListener) getNextButton().getListeners(Button.ClickEvent.class).toArray()[0];

        //remove the click listener from the next button. It will be added when the step is activated.
        getNextButton().getListeners(Button.ClickEvent.class)
                .forEach(listener -> getNextButton().removeListener(Button.ClickEvent.class, listener));
    }

    public Character getCharacter() {
        return character;
    }
    
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getOriginal() {
        return original;
    }

    public void setOriginal(Character original) {
        this.original = original;
        if (original != null) {
            this.character = this.original.clone();
            this.characterFromPreviousStep = this.character.clone();
        } else {
            this.character = null;
            this.characterFromPreviousStep = null;
        }
    }

    public Character getCharacterFromPreviousStep() {
        return characterFromPreviousStep;
    }

    public void setCharacterFromPreviousStep(Character characterFromPreviousStep) {
        this.characterFromPreviousStep = characterFromPreviousStep;
    }

    public DSClass getChosenClass() {
        return chosenClass;
    }

    public void setChosenClass(DSClass chosenClass) {
        this.chosenClass = chosenClass;
    }

    @Override
    public void activeStepChanged(WizardStepActivationEvent event) {
        Page.getCurrent().setTitle(event.getActivatedStep().getCaption());
        if (!getNextButton().getListeners(Button.ClickEvent.class).contains(nextButtonListener)) {
            getNextButton().addClickListener(nextButtonListener);
        }
    }

    @Override
    public void stepSetChanged(WizardStepSetChangedEvent event) {

    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        EventBus.post(new NavigationEvent(CharacterView.URI));
    }

    @Override
    public void wizardCancelled(WizardCancelledEvent event) {
        setOriginal(null);
        EventBus.post(new NavigationEvent(""));
    }

    @Override
    protected void activateStep(WizardStep step) {
        super.activateStep(step);
        if (step instanceof CharacterWizardStep) {
            ((CharacterWizardStep<?>) step).afterActivateStep();
        }
    }

}
