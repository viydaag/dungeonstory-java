package com.dungeonstory.ui.view.character.wizard;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.view.character.wizard.form.AbilityScoreInitForm;
import com.dungeonstory.ui.view.character.wizard.form.AbilityScoreUpdateForm;
import com.vaadin.ui.Component;

public class AbilityScoreStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -2104340592912821051L;

    private boolean levelUp = false;

    public AbilityScoreStep(CharacterWizard wizard, boolean levelUp) {
        super(wizard);
        this.levelUp = levelUp;
    }

    @Override
    public String getCaption() {
        Messages messages = Messages.getInstance();
        return messages.getMessage("abilityScoreStep.caption");
    }

    @Override
    public Component getContent() {
        if (levelUp) {
            form = new AbilityScoreUpdateForm(getWizard().getOriginal());
        } else {
            form = new AbilityScoreInitForm();
        }
        setSaveButton();
        form.setEntity(wizard.getCharacter());
        return form;
    }


}
