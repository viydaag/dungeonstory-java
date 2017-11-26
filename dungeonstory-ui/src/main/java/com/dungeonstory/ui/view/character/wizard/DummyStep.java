package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.repository.Entity;
import com.vaadin.ui.Component;

public class DummyStep
        extends CharacterWizardStep<Entity> {

    private static final long serialVersionUID = 5965117228576643364L;

    public DummyStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public Component getContent() {
        return null;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return true;
    }
    
    @Override
    public void afterActivateStep() {
        //sets the character from previous step
        int index = wizard.getSteps().indexOf(this);
        if (index > 0) {
            CharacterWizardStep<?> previousStep = (CharacterWizardStep<?>) wizard.getSteps().get(index - 1);
            wizard.setCharacterFromPreviousStep(previousStep.getStepCharacter());
            stepCharacter = previousStep.getStepCharacter(); //just pass it to next step
        }
    }

    @Override
    public String getCaption() {
        return "dummy";
    }

}
