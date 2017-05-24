package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.SpellChoiceForm;
import com.vaadin.ui.Component;

public class SpellStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = 13480232906069179L;

    public SpellStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("spellStep.caption");
    }

    @Override
    public Component getContent() {
        form = new SpellChoiceForm();
        setSaveButton();
        return form;
    }

    @Override
    public void afterActivateStep() {
        ((SpellChoiceForm) form).setClass(wizard.getChosenClass());
        form.setEntity(wizard.getCharacter());
        super.afterActivateStep();
    }

}
