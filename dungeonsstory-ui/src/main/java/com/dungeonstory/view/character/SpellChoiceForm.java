package com.dungeonstory.view.character;

import java.util.Optional;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.form.DSAbstractForm;
import com.vaadin.ui.Component;

public class SpellChoiceForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private SpellService     spellService     = SpellService.getInstance();
    private CharacterService characterService = CharacterService.getInstance();
    
    private DSClass classe;
    
    private MLabel label;

    public SpellChoiceForm() {
        super();
    }

    public void setClass(DSClass classe) {
        this.classe = classe;
    }

    @Override
    protected Component createContent() {
        
        label = new MLabel();

        return label;
    }

    @Override
    public void afterSetEntity() {

        Optional<CharacterClass> assignedClass = getEntity().getClasses().stream()
                .filter(characterClass -> characterClass.getClass().equals(this.classe)).findFirst();

        if (assignedClass.isPresent()) {

            int level = assignedClass.get().getClassLevel();
            label.setValue("Classe = " + assignedClass.get().toString());
        }

    }

}
