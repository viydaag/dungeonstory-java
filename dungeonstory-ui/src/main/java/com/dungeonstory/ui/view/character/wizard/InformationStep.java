package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.InformationForm;
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
