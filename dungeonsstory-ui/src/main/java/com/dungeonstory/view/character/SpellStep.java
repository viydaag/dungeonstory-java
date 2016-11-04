package com.dungeonstory.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.util.CharacterWizardStep;
import com.vaadin.ui.Component;

public class SpellStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = 13480232906069179L;

    public SpellStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Choix de sorts";
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
        form.setValidateOnlyDefinedFields(true);
        super.afterActivateStep();
    }

    //    @Override
    //    public boolean onAdvance() {

        //        List<Spell> characterKnownSpells = new ArrayList<>();
        //        Iterator<Component> iteratorCantrip = form.knownCantripLayout.iterator();
        //        while (iteratorCantrip.hasNext()) {
        //            Component c = iteratorCantrip.next();
        //            if (c instanceof Button) {
        //                Button b = (Button) c;
        //                Spell cantrip = (Spell) b.getData();
        //                characterKnownSpells.add(cantrip);
        //            }
        //        }
        //        Iterator<Component> iteratorSpell = form.knownSpellLayout.iterator();
        //        while (iteratorSpell.hasNext()) {
        //            Component c = iteratorSpell.next();
        //            if (c instanceof Button) {
        //                Button b = (Button) c;
        //                Spell spell = (Spell) b.getData();
        //                characterKnownSpells.add(spell);
        //            }
        //        }
        //
        //        Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(wizard.getCharacter(),
        //                wizard.getChosenClass());
        //        if (assignedClass.isPresent()) {
        //            assignedClass.get().setKnownSpells(characterKnownSpells);
        //        }

    //        return super.onAdvance();
    //    }

}
