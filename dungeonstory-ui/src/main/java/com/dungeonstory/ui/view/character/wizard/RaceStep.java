package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.RaceChoiceForm;
import com.vaadin.ui.Component;

public class RaceStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -7594021716313932613L;

    public RaceStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("raceStep.caption");
    }

    @Override
    public Component getContent() {
        form = new RaceChoiceForm();
        setSaveButton();
        form.setEntity(wizard.getCharacter());
        return form;
    }

}
