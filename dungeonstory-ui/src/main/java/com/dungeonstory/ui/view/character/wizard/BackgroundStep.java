package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.CharacterBackground;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.BackgroundChoiceForm;
import com.vaadin.ui.Component;

public class BackgroundStep extends CharacterWizardStep<CharacterBackground> {

    private static final long serialVersionUID = 5433452854744035692L;

    public BackgroundStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("backgroundStep.caption");
    }

    @Override
    public Component getContent() {
        form = new BackgroundChoiceForm(wizard.getCharacter());
        form.setEntity(new CharacterBackground());
        return form;
    }

}
