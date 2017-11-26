package com.dungeonstory.ui.view.character.wizard;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.CharacterDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.CharacterView;
import com.vaadin.server.Page;

public class CharacterWizard extends Wizard implements WizardProgressListener {

    private static final long serialVersionUID = -8481346074235692258L;

    protected Character original;
    protected Character character;
    protected Character characterFromPreviousStep;
    private DSClass     chosenClass;

    protected CharacterDataService characterService;

    public final static String RACE          = "raceChoice";
    public final static String CLASS         = "classChoice";
    public final static String CLASS_SPEC    = "classSpecChoice";
    public final static String ABILITY       = "abilityScores";
    public final static String CLASS_FEATURE = "classFeatures";
    public final static String SPELL         = "spellChoice";
    public final static String BACKGROUND    = "backgroundChoice";
    public final static String INFO          = "info";
    public final static String SUMMARY       = "summary";
    public final static String DUMMY         = "dummy";

    public CharacterWizard() {
        super();

        characterService = Services.getCharacterService();

        setUriFragmentEnabled(false);
        addListener(this);

        Messages messages = Messages.getInstance();
        getBackButton().setCaption(messages.getMessage("button.back"));
        getCancelButton().setCaption(messages.getMessage("button.cancel"));
        getNextButton().setCaption(messages.getMessage("button.next"));
        getFinishButton().setCaption(messages.getMessage("button.finish"));
    }

    public Character getCharacter() {
        return character;
    }
    
    public void setCharacter(Character character) {
        this.character = character;

        //set the new character for relationships
        if (this.character.getBackground() != null) {
            this.character.getBackground().setCharacter(this.character);
        }
        if (!this.character.getClasses().isEmpty()) {
            this.character.getClasses().forEach(c -> c.setCharacter(this.character));
        }
        if (!this.character.getEquipment().isEmpty()) {
            this.character.getEquipment().forEach(e -> e.setCharacter(this.character));
        }
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

    public boolean isActive(String stepId) {
        WizardStep step = idMap.get(stepId);
        if (step == null) {
            return false;
        }
        return super.isActive(step);
    }

}
