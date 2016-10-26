package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class HunterStep extends CharacterWizardStep {

    private static final long serialVersionUID = 7906929877964259808L;

    private HunterChoiceForm form;

    public HunterStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Chasseur";
    }

    @Override
    public Component getContent() {
        form = new HunterChoiceForm();
        form.setEntity(wizard.getCharacter());
        return form;
    }

    @Override
    public void afterActivateStep() {

    }


}
