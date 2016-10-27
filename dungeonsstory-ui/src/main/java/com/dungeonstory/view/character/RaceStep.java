package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class RaceStep extends CharacterWizardStep {

    private static final long serialVersionUID = -7594021716313932613L;

    private RaceChoiceForm form;

    public RaceStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de race";
    }

    @Override
    public Component getContent() {
        form = new RaceChoiceForm();
        form.setSaveButton(wizard.getNextButton());
        form.setEntity(wizard.getCharacter());
        form.setBeanLevelValidationEnabled(false);
        //        form.race.addMValueChangeListener(event -> {
        //            getWizard().getNextButton().setEnabled(event.getValue() != null);
        //        });

        return form;
    }

}
