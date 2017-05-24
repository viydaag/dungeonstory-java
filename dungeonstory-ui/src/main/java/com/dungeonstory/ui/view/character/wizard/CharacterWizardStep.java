package com.dungeonstory.ui.view.character.wizard;

import java.io.Serializable;

import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.vaadin.ui.Button;

public abstract class CharacterWizardStep<T> implements WizardStep, Serializable {

    private static final long serialVersionUID = 1566483351479714073L;

    protected CharacterWizard wizard;
    protected AbstractForm<T> form;
    protected Character       stepCharacter;           //character state at the start of the step
    protected boolean         isSaveButtonSet = false; //this is to prevent setting twice the click listener on the button

    public CharacterWizardStep(CharacterWizard wizard) {
        this.wizard = wizard;
    }

    public CharacterWizard getWizard() {
        return wizard;
    }

    @Override
    public boolean onAdvance() {
        wizard.setCharacterFromPreviousStep(stepCharacter);
        removeSaveButtonClickListener();
        return true;
    }

    @Override
    public boolean onBack() {
        wizard.setCharacter(wizard.getCharacterFromPreviousStep());
        removeSaveButtonClickListener();
        return true;
    }

    public void afterActivateStep() {
        if (form != null) {
            setSaveButton();
            form.getBinder().validate();
        }

        //sets the character from previous step
        int index = wizard.getSteps().indexOf(this);
        if (index > 0) {
            CharacterWizardStep<?> previousStep = (CharacterWizardStep<?>) wizard.getSteps().get(index - 1);
            wizard.setCharacterFromPreviousStep(previousStep.getStepCharacter());
        }

        stepCharacter = wizard.getCharacter().clone();
    }

    private void removeSaveButtonClickListener() {
        if (form != null) {
            form.getSaveButton().getListeners(Button.ClickEvent.class)
                    .forEach(listener -> form.getSaveButton().removeListener(Button.ClickEvent.class, listener));
            isSaveButtonSet = false;
        }
    }

    protected void setSaveButton() {
        if (form != null && !isSaveButtonSet) {
            form.setSaveButton(wizard.getNextButton());
            isSaveButtonSet = true;
        }
    }

    public Character getStepCharacter() {
        return stepCharacter;
    }

}
