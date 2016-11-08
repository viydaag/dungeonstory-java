package com.dungeonstory.view.character;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.util.CharacterWizardStep;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.layout.FormLayoutNoSpace;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SummaryStep extends CharacterWizardStep<Character> {

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
        DSClass chosenClass = wizard.getChosenClass();

        FormLayout layout = new FormLayout();
        
        MLabel level = new MLabel("Niveau", character.getLevel().toString());
        MLabel race = new MLabel("Race", character.getRace().getName());

        CollectionToStringConverter classCollectionConverter = new CollectionToStringConverter();
        classCollectionConverter.setDelimiter(" / ");
        MLabel classes = new MLabel("Classe",
                classCollectionConverter.convertToPresentation(character.getClasses(), String.class, null));

        MLabel abilities = new MLabel().withCaption("Caractéristiques").withStyleName(ValoTheme.LABEL_H4);
        FormLayoutNoSpace abilityLayout = new FormLayoutNoSpace();
        MLabel strength = new MLabel("Force", String.valueOf(character.getStrength()));
        MLabel dexterity = new MLabel("Dextérité", String.valueOf(character.getDexterity()));
        MLabel constitution = new MLabel("Constitution", String.valueOf(character.getConstitution()));
        MLabel intellignece = new MLabel("Intellignece", String.valueOf(character.getIntelligence()));
        MLabel wisdom = new MLabel("Sagesse", String.valueOf(character.getWisdom()));
        MLabel charisma = new MLabel("Charisme", String.valueOf(character.getCharisma()));
        abilityLayout.addComponents(strength, dexterity, constitution, intellignece, wisdom, charisma);
        
        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();
        MLabel proficienciesLabel = new MLabel().withCaption("Maitrises").withStyleName(ValoTheme.LABEL_H4);
        MLabel armorProficiencies = new MLabel("Armures",
                collectionConverter.convertToPresentation(character.getArmorProficiencies(), String.class, null));
        MLabel weaponProficiencies = new MLabel("Armes",
                collectionConverter.convertToPresentation(character.getWeaponProficiencies(), String.class, null));
        MLabel savingThrowProficiencies = new MLabel("Jets de sauvegarde", collectionConverter
                .convertToPresentation(character.getSavingThrowProficiencies(), String.class, null));
        MLabel skillProficiencies = new MLabel("Compétences",
                collectionConverter.convertToPresentation(character.getSkillProficiencies(), String.class, null));
        MLabel languages = new MLabel("Langages",
                collectionConverter.convertToPresentation(character.getLanguages(), String.class, null));

        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel() * (cc.getClasse().getLifePointPerLevel()
                    + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        MLabel lifePoints = new MLabel("Points de vie", String.valueOf(nbLifePoints));
        character.setLifePoints(nbLifePoints);

        //Proficiencies

        //Alignment

        //Background

        //Starting gold

        layout.addComponents(level, race, classes, lifePoints, abilities, abilityLayout, languages);
        layout.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies,
                skillProficiencies);

        return layout;
    }

}
