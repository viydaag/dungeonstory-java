package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class AbilityScoreStep extends CharacterWizardStep {

    private static final long serialVersionUID = -2104340592912821051L;

    private AbilityScoreInitForm form;

    public AbilityScoreStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Scores de caractéristique";
    }

    @Override
    public Component getContent() {
        form = new AbilityScoreInitForm();
        form.setSaveButton(wizard.getNextButton());
        form.setEntity(wizard.getCharacter());
        form.setBeanLevelValidationEnabled(false);
        //        form.pointsToSpend.addValueChangeListener(event -> {
        //            getWizard().getNextButton().setEnabled((Integer) event.getProperty().getValue() == 0);
        //        });
        return form;
    }

    //    @Override
    //    public void afterActivateStep() {
    //        if (form.pointsToSpend.getValue() != 0) {
    //            getWizard().getNextButton().setEnabled(false);
    //        }
    //    }

}
