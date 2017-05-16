package com.dungeonstory.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.i18n.Messages;
import com.vaadin.ui.Component;

public class InformationStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = 8434596006783102543L;

    public InformationStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("informationStep.caption");
    }

    @Override
    public Component getContent() {
        form = new InformationForm();
        setSaveButton();
        form.setEntity(wizard.getCharacter());
        return form;
    }

}
