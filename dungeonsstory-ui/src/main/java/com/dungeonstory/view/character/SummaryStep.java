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

        MLabel levelLabel = new MLabel("Niveau", character.getLevel().toString());
        layout.addComponent(levelLabel);
        
        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        if (character.getId() == null) {
            MLabel raceLabel = new MLabel("Race", character.getRace().getName());
            layout.addComponent(raceLabel);
            MLabel languagesLabel = new MLabel("Langages",
                    collectionConverter.convertToPresentation(character.getLanguages(), String.class, null));
            layout.addComponent(languagesLabel);
        }

        CollectionToStringConverter classCollectionConverter = new CollectionToStringConverter();
        classCollectionConverter.setDelimiter(" / ");
        MLabel classesLabel = new MLabel("Classe",
                classCollectionConverter.convertToPresentation(character.getClasses(), String.class, null));
        layout.addComponent(classesLabel);

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
            MLabel lifePointsLabel = new MLabel("Points de vie", String.valueOf(nbLifePoints) + "(+" + difLifePoints + ")");
            layout.addComponent(lifePointsLabel);
        }
        character.setLifePoints(nbLifePoints);

        Set<Feat> featSet = new HashSet<Feat>(character.getFeats());
        featSet.removeAll(original.getFeats());
        if (!featSet.isEmpty()) {
            MLabel featsLabel = new MLabel("Nouveaux dons",
                    collectionConverter.convertToPresentation(featSet, String.class, null));
            layout.addComponent(featsLabel);
        }

        if (character.getId() == null) {
            MLabel abilitiesLabel = new MLabel().withCaption("Caractéristiques").withStyleName(ValoTheme.LABEL_H4);
            FormLayoutNoSpace abilityLayout = new FormLayoutNoSpace();
            MLabel strengthLabel = new MLabel("Force", String.valueOf(character.getStrength()));
            MLabel dexterityLabel = new MLabel("Dextérité", String.valueOf(character.getDexterity()));
            MLabel constitutionLabel = new MLabel("Constitution", String.valueOf(character.getConstitution()));
            MLabel intelligneceLabel = new MLabel("Intellignece", String.valueOf(character.getIntelligence()));
            MLabel wisdomLabel = new MLabel("Sagesse", String.valueOf(character.getWisdom()));
            MLabel charismaLabel = new MLabel("Charisme", String.valueOf(character.getCharisma()));
            abilityLayout.addComponents(strengthLabel, dexterityLabel, constitutionLabel, intelligneceLabel,
                    wisdomLabel, charismaLabel);
            layout.addComponents(abilitiesLabel, abilityLayout);
        } else {
            //check which ability has changed with original
        }

        if (character.getId() == null) {
            MLabel proficienciesLabel = new MLabel().withCaption("Maitrises").withStyleName(ValoTheme.LABEL_H4);
            MLabel armorProficienciesLabel = new MLabel("Armures",
                    collectionConverter.convertToPresentation(character.getArmorProficiencies(), String.class, null));
            MLabel weaponProficienciesLabel = new MLabel("Armes",
                    collectionConverter.convertToPresentation(character.getWeaponProficiencies(), String.class, null));
            MLabel savingThrowProficienciesLabel = new MLabel("Jets de sauvegarde", collectionConverter
                    .convertToPresentation(character.getSavingThrowProficiencies(), String.class, null));
            MLabel skillProficienciesLabel = new MLabel("Compétences",
                    collectionConverter.convertToPresentation(character.getSkillProficiencies(), String.class, null));
            layout.addComponents(proficienciesLabel, armorProficienciesLabel, weaponProficienciesLabel,
                    savingThrowProficienciesLabel, skillProficienciesLabel);
        } else {
            //check if new proficiencies are available
        }

        if (character.getId() == null) {
            MLabel infoLabel = new MLabel().withCaption("Informations").withStyleName(ValoTheme.LABEL_H4);
            MLabel nameLabel = new MLabel("Nom", character.getName());
            MLabel genderLabel = new MLabel("Sexe", character.getGender().toString());
            MLabel ageLabel = new MLabel("Âge", String.valueOf(character.getAge()));
            MLabel weightLabel = new MLabel("Poids", character.getWeight() + " lbs");
            MLabel heightLabel = new MLabel("Taille", character.getHeight());
            MLabel alignmentLabel = new MLabel("Alignement", character.getAlignment().toString());
            MLabel regionLabel = new MLabel("Région d'origine", character.getRegion().toString());
            MLabel backgroundLabel = new MLabel("Background", character.getBackground().getBackground().toString());
            layout.addComponents(infoLabel, nameLabel, genderLabel, ageLabel, weightLabel, heightLabel, alignmentLabel,
                    regionLabel, backgroundLabel);
        }

        //Starting gold

        return layout;
    }

}
