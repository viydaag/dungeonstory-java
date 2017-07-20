package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.view.character.wizard.form.ClassFeatureChoiceForm;
import com.vaadin.ui.Component;

public class ClassFeatureStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -7594021716313932613L;

    public ClassFeatureStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Dons";
    }

    @Override
    public Component getContent() {
        CharacterClass assignedClass = ClassUtil.getCharacterClass(wizard.getCharacter(), wizard.getChosenClass());
        form = new ClassFeatureChoiceForm(assignedClass);
        form.setEntity(wizard.getCharacter());
        return form;
    }

}
