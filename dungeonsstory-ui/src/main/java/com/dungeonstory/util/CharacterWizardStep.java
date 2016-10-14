package com.dungeonstory.util;

import java.io.Serializable;

import com.dungeonstory.view.character.CharacterWizard;

public class CharacterWizardStep implements Serializable {

    private static final long serialVersionUID = 1566483351479714073L;

    protected CharacterWizard wizard;

    public CharacterWizardStep(CharacterWizard wizard) {
        this.wizard = wizard;
    }

    public CharacterWizard getWizard() {
        return wizard;
    }

}
