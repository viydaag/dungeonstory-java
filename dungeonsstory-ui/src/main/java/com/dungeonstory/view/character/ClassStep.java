package com.dungeonstory.view.character;

import java.util.Optional;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
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
        form.setEntity(wizard.getCharacter());
        form.classe.addMValueChangeListener(event -> {
            getWizard().getNextButton().setEnabled(event.getValue() != null);
        });
        return form;
    }

    @Override
    public boolean onAdvance() {

        DSClass chosenClass = form.getClasse().getValue();

        Optional<CharacterClass> assignedClass = wizard.getCharacter().getClasses().stream()
                .filter(characterClasse -> characterClasse.getClass().equals(chosenClass)).findFirst();

        if (assignedClass.isPresent()) {
            assignedClass.get().setClassLevel(assignedClass.get().getClassLevel() + 1);
        } else {
            CharacterClass classe = new CharacterClass();
            classe.setCharacter(wizard.getCharacter());
            classe.setClasse(chosenClass);
            classe.setClassLevel(1);
            wizard.getCharacter().getClasses().add(classe);
        }

        //the spell choice is available only if its a spell casting class and the spells are known or if its the first time prepared spells
        if (!chosenClass.getIsSpellCasting()
                || (chosenClass.getSpellCastingType() == SpellCastingType.PREPARED && assignedClass.isPresent())) {
            wizard.removeStep("spellChoice");
        }

        wizard.setChosenClass(chosenClass);

        return super.onAdvance();
    }

    @Override
    public void afterActivateStep() {
        if (form.classe.getValue() == null) {
            getWizard().getNextButton().setEnabled(false);
        }
    }

}
