package com.dungeonstory.util;

import java.io.Serializable;

import org.vaadin.teemu.wizards.WizardStep;

import com.dungeonstory.view.character.CharacterWizard;

public abstract class CharacterWizardStep implements WizardStep, Serializable {

    private static final long serialVersionUID = 1566483351479714073L;

    protected CharacterWizard wizard;

    public CharacterWizardStep(CharacterWizard wizard) {
        this.wizard = wizard;
    }

    public CharacterWizard getWizard() {
        return wizard;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return true;
    }

    public abstract void afterActivateStep();

}
