package com.dungeonstory.view.character;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.util.CharacterWizardStep;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class SummaryStep extends CharacterWizardStep {

    private static final long serialVersionUID = -8516216761141601232L;

    public SummaryStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return "Résumé";
    }

    @Override
    public Component getContent() {
        
        Character character = wizard.getCharacter();
        FormLayout layout = new FormLayout();
        CollectionToStringConverter classCllectionConverter = new CollectionToStringConverter();
        classCllectionConverter.setDelimiter(" / ");
        
        MLabel level = new MLabel("Niveau", character.getLevel().toString());
        MLabel race = new MLabel("Race", character.getRace().getName());
        MLabel classe = new MLabel("Classe",
                classCllectionConverter.convertToPresentation(character.getClasses(), String.class, null));

        MLabel abilities = new MLabel().withCaption("Caractéristiques");
        FormLayout abilityLayout = new FormLayout();
        MLabel strength = new MLabel("Force", String.valueOf(character.getStrength()));
        MLabel dexterity = new MLabel("Dextérité", String.valueOf(character.getDexterity()));
        MLabel constitution = new MLabel("Constitution", String.valueOf(character.getConstitution()));
        MLabel intellignece = new MLabel("Intellignece", String.valueOf(character.getIntelligence()));
        MLabel wisdom = new MLabel("Sagesse", String.valueOf(character.getWisdom()));
        MLabel charisma = new MLabel("Charisme", String.valueOf(character.getCharisma()));
        abilityLayout.setSpacing(false);
        abilityLayout.setMargin(false);
        abilityLayout.addComponents(strength, dexterity, constitution, intellignece, wisdom, charisma);
        
        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel() * (cc.getClasse().getLifePointPerLevel()
                    + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        MLabel lifePoints = new MLabel("Points de vie", String.valueOf(nbLifePoints));

        //Proficiencies

        //Alignment

        //Background

        //Starting gold

        layout.addComponents(level, race, classe, lifePoints, abilities, abilityLayout);

        return layout;
    }

    @Override
    public void afterActivateStep() {

    }

}
