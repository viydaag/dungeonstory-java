package com.dungeonstory.view.character;

import java.util.Optional;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Component;

public class SpellSubSetSelector extends DSSubSetSelector<Spell> {

    private static final long serialVersionUID = 1999118326831507295L;

    private SpellService spellService = SpellService.getInstance();

    public SpellSubSetSelector() {
        super(Spell.class);
        setVisibleProperties("level", "name", "school");
        setColumnHeader("Niveau du sort", "Sort");
        setColumnHeader("name", "Sort");
        setColumnHeader("school", "École de magie");

        getTable().setItemDescriptionGenerator(new ItemDescriptionGenerator() {

            private static final long serialVersionUID = -3475401659477278855L;

            @Override
            public String generateDescription(Component source, Object itemId, Object propertyId) {
                Spell spell = (Spell) itemId;
                return spell.getDescription();
            }
        });
    }

    public void fill(Character character, DSClass classe, int spellLevel) {

        Optional<CharacterClass> assignedClass = character.getClasses().stream()
                .filter(characterClass -> characterClass.getClasse().equals(classe)).findFirst();

        if (assignedClass.isPresent()) {

            int classLevel = assignedClass.get().getClassLevel();

            Optional<ClassSpellSlots> spellSlotOpt = classe.getSpellSlots().stream()
                    .filter(slot -> slot.getLevel().getId().intValue() == classLevel).findFirst();
            if (spellSlotOpt.isPresent()) {
                ClassSpellSlots spellSlot = spellSlotOpt.get();
                if (spellLevel == 0) {
                    Integer nbCantrips = spellSlot.getCantripsKnown();
                    if (nbCantrips != null) {
                        setOptions(spellService.findAllUnknownSpellsByLevel(spellLevel, character.getId()));
                        setValue(spellService.findAllKnownSpellsByLevel(spellLevel, character.getId()));
                    }
                } else {
                    Integer nbSpells = spellSlot.getSpellsKnown();
                    if (nbSpells != null) {
                        setOptions(spellService.findAllUnknownSpellsByLevel(spellLevel, character.getId()));
                        setValue(spellService.findAllKnownSpellsByLevel(spellLevel, character.getId()));
                    }
                }
            }
        }
    }

}
