package com.dungeonstory.view.character;

import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class ClassStep extends CharacterWizardStep {

    private static final long serialVersionUID = 335212747439261092L;

    private ClassChoiceForm form;

    public ClassStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de classe";
    }

    @Override
    public Component getContent() {
        form = new ClassChoiceForm();
        form.setSaveButton(wizard.getNextButton());
        form.setEntity(wizard.getCharacter());
        form.setBeanLevelValidationEnabled(false);
        //        form.classe.addMValueChangeListener(event -> {
        //            getWizard().getNextButton().setEnabled(event.getValue() != null);
        //        });
        return form;
    }

    @Override
    public boolean onAdvance() {

        //        DSClass chosenClass = form.getClasse().getValue();
        //
        //        Optional<CharacterClass> assignedClass = wizard.getCharacter().getClasses().stream()
        //                .filter(characterClasse -> characterClasse.getClass().equals(chosenClass)).findFirst();
        //
        //        if (assignedClass.isPresent()) {
        //            assignedClass.get().setClassLevel(assignedClass.get().getClassLevel() + 1);
        //        } else {
        //            CharacterClass classe = new CharacterClass();
        //            classe.setCharacter(wizard.getCharacter());
        //            classe.setClasse(chosenClass);
        //            classe.setClassLevel(1);
        //            wizard.getCharacter().getClasses().add(classe);
        //        }


        wizard.setChosenClass(form.getClasse().getValue());

        return super.onAdvance();
    }

}
