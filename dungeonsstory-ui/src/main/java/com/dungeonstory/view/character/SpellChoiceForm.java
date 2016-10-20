package com.dungeonstory.view.character;

import java.util.Optional;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Component;

public class SpellChoiceForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private SpellService     spellService     = SpellService.getInstance();
    private CharacterService characterService = CharacterService.getInstance();
    
    private DSClass classe;
    
    private MLabel label;
    private DSSubSetSelector<Spell> cantrips;

    public SpellChoiceForm() {
        super();
    }

    public void setClass(DSClass classe) {
        this.classe = classe;
    }

    @Override
    protected Component createContent() {
        
        VerticalSpacedLayout layout = new VerticalSpacedLayout();

        label = new MLabel();
        cantrips = new DSSubSetSelector<Spell>(Spell.class);
        cantrips.setCaption("Sorts mineurs");
        cantrips.setVisibleProperties("name", "school");
        cantrips.setColumnHeader("name", "Sort");
        cantrips.setColumnHeader("school", "École de magie");

        cantrips.getTable().setItemDescriptionGenerator(new ItemDescriptionGenerator() {

            private static final long serialVersionUID = -3475401659477278855L;

            @Override
            public String generateDescription(Component source, Object itemId, Object propertyId) {
                Spell spell = (Spell) itemId;
                return spell.getDescription();
            }
        });

        layout.addComponent(label);
        layout.addComponent(cantrips);

        return layout;
    }

    @Override
    public void afterSetEntity() {

        Optional<CharacterClass> assignedClass = getEntity().getClasses().stream()
                .filter(characterClass -> characterClass.getClasse().equals(this.classe)).findFirst();

        if (assignedClass.isPresent()) {

            int classLevel = assignedClass.get().getClassLevel();
            label.setValue("Classe = " + assignedClass.get().toString());

            Optional<ClassSpellSlots> spellSlot = this.classe.getSpellSlots().stream()
                    .filter(slot -> slot.getLevel().getId().intValue() == classLevel).findFirst();
            if (spellSlot.isPresent()) {
                Integer nbCantrips = spellSlot.get().getCantripsKnown();
                if (nbCantrips != null) {
                    cantrips.setOptions(spellService.findAllUnknownSpellsByLevel(0, getEntity().getId()));
                    cantrips.setValue(spellService.findAllKnownSpellsByLevel(0, getEntity().getId()));
                }
            }
        }
        


    }

}
