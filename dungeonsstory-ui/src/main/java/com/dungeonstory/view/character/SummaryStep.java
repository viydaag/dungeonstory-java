package com.dungeonstory.view.character;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.util.CharacterWizardStep;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.layout.FormLayoutNoSpace;
import com.vaadin.shared.ui.MarginInfo;
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
        Character original = wizard.getOriginal();

        FormLayout layout = new FormLayout();
        layout.setMargin(new MarginInfo(true, true));

        MLabel level = new MLabel("Niveau", character.getLevel().toString());
        layout.addComponent(level);
        
        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        if (character.getId() == null) {
            MLabel race = new MLabel("Race", character.getRace().getName());
            layout.addComponent(race);
            MLabel languages = new MLabel("Langages",
                    collectionConverter.convertToPresentation(character.getLanguages(), String.class, null));
            layout.addComponent(languages);
        }

        CollectionToStringConverter classCollectionConverter = new CollectionToStringConverter();
        classCollectionConverter.setDelimiter(" / ");
        MLabel classes = new MLabel("Classe",
                classCollectionConverter.convertToPresentation(character.getClasses(), String.class, null));
        layout.addComponent(classes);

        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel() * (cc.getClasse().getLifePointPerLevel()
                    + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        int difLifePoints = nbLifePoints - original.getLifePoints();

        if (character.getId() == null) {
            MLabel lifePoints = new MLabel("Points de vie", String.valueOf(nbLifePoints));
            layout.addComponent(lifePoints);
        } else {
            MLabel lifePoints = new MLabel("Points de vie", String.valueOf(nbLifePoints) + "(+" + difLifePoints + ")");
            layout.addComponent(lifePoints);
        }
        character.setLifePoints(nbLifePoints);

        Set<Feat> featSet = new HashSet<Feat>(character.getFeats());
        featSet.removeAll(original.getFeats());
        if (!featSet.isEmpty()) {
            MLabel feats = new MLabel("Nouveaux dons",
                    collectionConverter.convertToPresentation(featSet, String.class, null));
            layout.addComponent(feats);
        }

        if (character.getId() == null) {
            MLabel abilities = new MLabel().withCaption("Caractéristiques").withStyleName(ValoTheme.LABEL_H4);
            FormLayoutNoSpace abilityLayout = new FormLayoutNoSpace();
            MLabel strength = new MLabel("Force", String.valueOf(character.getStrength()));
            MLabel dexterity = new MLabel("Dextérité", String.valueOf(character.getDexterity()));
            MLabel constitution = new MLabel("Constitution", String.valueOf(character.getConstitution()));
            MLabel intellignece = new MLabel("Intellignece", String.valueOf(character.getIntelligence()));
            MLabel wisdom = new MLabel("Sagesse", String.valueOf(character.getWisdom()));
            MLabel charisma = new MLabel("Charisme", String.valueOf(character.getCharisma()));
            abilityLayout.addComponents(strength, dexterity, constitution, intellignece, wisdom, charisma);
            layout.addComponents(abilities, abilityLayout);
        } else {
            //check which ability has changed with original
        }

        if (character.getId() == null) {
            MLabel proficienciesLabel = new MLabel().withCaption("Maitrises").withStyleName(ValoTheme.LABEL_H4);
            MLabel armorProficiencies = new MLabel("Armures",
                    collectionConverter.convertToPresentation(character.getArmorProficiencies(), String.class, null));
            MLabel weaponProficiencies = new MLabel("Armes",
                    collectionConverter.convertToPresentation(character.getWeaponProficiencies(), String.class, null));
            MLabel savingThrowProficiencies = new MLabel("Jets de sauvegarde", collectionConverter
                    .convertToPresentation(character.getSavingThrowProficiencies(), String.class, null));
            MLabel skillProficiencies = new MLabel("Compétences",
                    collectionConverter.convertToPresentation(character.getSkillProficiencies(), String.class, null));
            layout.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies,
                    skillProficiencies);
        } else {
            //check if new proficiencies are available
        }

        if (character.getId() == null) {
            MLabel infoLabel = new MLabel().withCaption("Informations").withStyleName(ValoTheme.LABEL_H4);
            MLabel name = new MLabel("Nom", character.getName());
            MLabel gender = new MLabel("Sexe", character.getGender().toString());
            MLabel age = new MLabel("Âge", String.valueOf(character.getAge()));
            MLabel weight = new MLabel("Poids", character.getWeight() + " lbs");
            MLabel height = new MLabel("Taille", character.getHeight());
            MLabel alignment = new MLabel("Alignement", character.getAlignment().toString());
            MLabel region = new MLabel("Région d'origine", character.getRegion().toString());
            MLabel background = new MLabel("Background", character.getBackground().getBackground().toString());
            layout.addComponents(infoLabel, name, gender, age, weight, height, alignment, region, background);
        }

        //Starting gold

        return layout;
    }

}
