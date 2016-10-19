package com.dungeonstory.view.character;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class CharacterWizard extends Wizard implements WizardProgressListener {

    private static final long serialVersionUID = -8481346074235692258L;

    private Character character;
    private DSClass   chosenClass;

    private CharacterService characterService = CharacterService.getInstance();

    public CharacterWizard() {
        super();
        setUriFragmentEnabled(false);
        addListener(this);
        character = characterService.create();

        getBackButton().setCaption("Précédent");
        getCancelButton().setCaption("Annuler");
        getNextButton().setCaption("Suivant");
        getFinishButton().setCaption("Terminer");
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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
        character.setUser(CurrentUser.get());
        characterService.create(character);
        Notification.show("Personnage créé!", Type.HUMANIZED_MESSAGE);
        EventBus.post(new NavigationEvent(""));
        //EventBus.post(new NavigationEvent("character"));
    }

    @Override
    public void wizardCancelled(WizardCancelledEvent event) {
        setCharacter(null);
        EventBus.post(new NavigationEvent(""));
    }

    @Override
    protected void activateStep(WizardStep step) {
        super.activateStep(step);
        if (step instanceof CharacterWizardStep) {
            ((CharacterWizardStep) step).afterActivateStep();
        }
    }

}
