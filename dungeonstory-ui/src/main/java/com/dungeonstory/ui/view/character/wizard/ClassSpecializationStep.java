package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.view.character.wizard.form.ClassSpecChoiceForm;
import com.vaadin.ui.Component;

public class ClassSpecializationStep extends CharacterWizardStep<CharacterClass> {

    private static final long serialVersionUID = 335212747439261092L;

    public ClassSpecializationStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        //        Messages messages = Messages.getInstance();
        //        return messages.getMessage("Spécialisation");
        return "Spécialisation";
    }

    @Override
    public Component getContent() {
        form = new ClassSpecChoiceForm(wizard.getCharacter());
        setSaveButton();
        form.setEntity(ClassUtil.getCharacterClass(wizard.getCharacter(), wizard.getChosenClass()));
        return form;
    }

}
