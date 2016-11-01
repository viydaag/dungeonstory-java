package com.dungeonstory.view.character;

import com.dungeonstory.backend.data.CharacterBackground;
import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class BackgroundStep extends CharacterWizardStep {

    private static final long serialVersionUID = 5433452854744035692L;

    public BackgroundStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de background";
    }

    @Override
    public Component getContent() {
        BackgroundChoiceForm form = new BackgroundChoiceForm(wizard.getCharacter());
        form.setSaveButton(wizard.getNextButton());
        form.setEntity(new CharacterBackground());
        return form;
    }

}
