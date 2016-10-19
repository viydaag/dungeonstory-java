package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class SpellStep extends CharacterWizardStep {

    private static final long serialVersionUID = 13480232906069179L;

    private SpellChoiceForm form;

    public SpellStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de sorts";
    }

    @Override
    public Component getContent() {
        form = new SpellChoiceForm();
        return form;
    }

    @Override
    public void afterActivateStep() {
        form.setClass(wizard.getChosenClass());
        form.setEntity(wizard.getCharacter());
    }

}
