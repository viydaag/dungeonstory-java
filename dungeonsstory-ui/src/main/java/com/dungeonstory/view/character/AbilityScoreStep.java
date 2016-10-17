package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class AbilityScoreStep extends CharacterWizardStep {

    private static final long serialVersionUID = -2104340592912821051L;

    public AbilityScoreStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Scores de caractéristique";
    }

    @Override
    public Component getContent() {
        AbilityScoreInitForm form = new AbilityScoreInitForm();
        form.setEntity(wizard.getCharacter());
        return form;
    }

}
