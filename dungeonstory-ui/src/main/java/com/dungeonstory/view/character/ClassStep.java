package com.dungeonstory.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class ClassStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = 335212747439261092L;

    public ClassStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        Messages messages = Messages.getInstance();
        return messages.getMessage("classStep.caption");
    }

    @Override
    public Component getContent() {
        form = new ClassChoiceForm();
        setSaveButton();
        form.setEntity(wizard.getCharacter());
        return form;
    }

    @Override
    public boolean onAdvance() {

        wizard.setChosenClass(((ClassChoiceForm) form).getChosenClass());

        return super.onAdvance();
    }

}
