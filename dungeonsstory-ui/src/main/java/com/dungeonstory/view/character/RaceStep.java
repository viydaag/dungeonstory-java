package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class RaceStep extends CharacterWizardStep {

    private static final long serialVersionUID = -7594021716313932613L;

    public RaceStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de race";
    }

    @Override
    public Component getContent() {
        RaceChoiceForm form = new RaceChoiceForm();
        form.setEntity(wizard.getCharacter());
        return form;
    }

}
