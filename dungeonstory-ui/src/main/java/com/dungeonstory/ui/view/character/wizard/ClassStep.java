package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.ClassChoiceForm;
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
        form.setEntity(new CharacterClass());
        return form;
    }

    @Override
    public boolean onAdvance() {
        boolean advance = super.onAdvance();
        wizard.setChosenClass(((ClassChoiceForm) form).getChosenClass());

        return advance;
    }

}
