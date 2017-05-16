package com.dungeonstory.view.character;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.i18n.Messages;
import com.vaadin.ui.Component;

public class ClassStep extends CharacterWizardStep<CharacterClass> {

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
        form = new ClassChoiceForm(wizard.getCharacter());
        setSaveButton();
        form.setEntity(new CharacterClass());
        return form;
    }

    @Override
    public boolean onAdvance() {

        wizard.setChosenClass(((ClassChoiceForm) form).getChosenClass());

        return super.onAdvance();
    }

}
