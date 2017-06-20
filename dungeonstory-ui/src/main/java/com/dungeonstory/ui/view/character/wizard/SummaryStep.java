package com.dungeonstory.ui.view.character.wizard;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.layout.FormLayoutNoSpace;
import com.dungeonstory.ui.util.DSConstant;
import com.vaadin.data.ValueContext;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.themes.ValoTheme;

public class SummaryStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -8516216761141601232L;

    public SummaryStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("summaryStep.caption");
    }

    @Override
    public Component getContent() {

        Messages messages = Messages.getInstance();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        FormLayout layout = new FormLayout();
        layout.setMargin(new MarginInfo(true, true));

        DSLabel levelLabel = new DSLabel(messages.getMessage("summaryStep.level.label"), character.getLevel().toString());
        layout.addComponent(levelLabel);

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        if (character.getId() == null) {
            DSLabel raceLabel = new DSLabel(messages.getMessage("summaryStep.race.label"), character.getRace().getName());
            layout.addComponent(raceLabel);
            DSLabel languagesLabel = new DSLabel(messages.getMessage("summaryStep.language.label"),
                    collectionConverter.convertToPresentation(character.getLanguages(), new ValueContext()));
            layout.addComponent(languagesLabel);
        }

        CollectionToStringConverter classCollectionConverter = new CollectionToStringConverter();
        classCollectionConverter.setDelimiter(" / ");
        DSLabel classesLabel = new DSLabel(messages.getMessage("summaryStep.class.label"),
                classCollectionConverter.convertToPresentation(character.getClasses(), new ValueContext()));
        layout.addComponent(classesLabel);

        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel()
                    * (cc.getClasse().getLifePointPerLevel() + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        int difLifePoints = nbLifePoints - original.getLifePoints();

        if (character.getId() == null) {
            DSLabel lifePoints = new DSLabel(messages.getMessage("summaryStep.lifePoint.label"), String.valueOf(nbLifePoints));
            layout.addComponent(lifePoints);
        } else {
            DSLabel lifePointsLabel = new DSLabel(messages.getMessage("summaryStep.lifePoint.label"),
                    String.valueOf(nbLifePoints) + "(+" + difLifePoints + ")");
            layout.addComponent(lifePointsLabel);
        }
        character.setLifePoints(nbLifePoints);

        Set<Feat> featSet = new HashSet<Feat>(character.getFeats());
        featSet.removeAll(original.getFeats());
        if (!featSet.isEmpty()) {
            DSLabel featsLabel = new DSLabel(messages.getMessage("summaryStep.newFeat.label"),
                    collectionConverter.convertToPresentation(featSet, new ValueContext()));
            layout.addComponent(featsLabel);
        }

        if (character.getId() == null) {
            DSLabel abilitiesLabel = new DSLabel().withCaption(messages.getMessage("summaryStep.ability.label")).withStyleName(ValoTheme.LABEL_H4);
            FormLayoutNoSpace abilityLayout = new FormLayoutNoSpace();
            DSLabel strengthLabel = new DSLabel(messages.getMessage("ability.str.caption"), String.valueOf(character.getStrength()));
            DSLabel dexterityLabel = new DSLabel(messages.getMessage("ability.dex.caption"), String.valueOf(character.getDexterity()));
            DSLabel constitutionLabel = new DSLabel(messages.getMessage("ability.con.caption"), String.valueOf(character.getConstitution()));
            DSLabel intelligneceLabel = new DSLabel(messages.getMessage("ability.int.caption"), String.valueOf(character.getIntelligence()));
            DSLabel wisdoDSLabel = new DSLabel(messages.getMessage("ability.wis.caption"), String.valueOf(character.getWisdom()));
            DSLabel charismaLabel = new DSLabel(messages.getMessage("ability.cha.caption"), String.valueOf(character.getCharisma()));
            abilityLayout.addComponents(strengthLabel, dexterityLabel, constitutionLabel, intelligneceLabel, wisdoDSLabel, charismaLabel);
            layout.addComponents(abilitiesLabel, abilityLayout);
        } else {
            //check which ability has changed with original
        }

        if (character.getId() == null) {
            DSLabel proficienciesLabel = new DSLabel().withCaption(messages.getMessage("summaryStep.proficiency.label"))
                    .withStyleName(ValoTheme.LABEL_H4);
            DSLabel armorProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.armor.label"),
                    collectionConverter.convertToPresentation(character.getArmorProficiencies(), new ValueContext()));
            DSLabel weaponProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.weapon.label"),
                    collectionConverter.convertToPresentation(character.getWeaponProficiencies(), new ValueContext()));
            DSLabel savingThrowProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.savingThrow.label"),
                    collectionConverter.convertToPresentation(character.getSavingThrowProficiencies(), new ValueContext()));
            DSLabel skillProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.skill.label"),
                    collectionConverter.convertToPresentation(character.getSkillProficiencies(), new ValueContext()));
            layout.addComponents(proficienciesLabel, armorProficienciesLabel, weaponProficienciesLabel, savingThrowProficienciesLabel,
                    skillProficienciesLabel);
        } else {
            //check if new proficiencies are available
        }

        if (character.getId() == null) {
            DSLabel infoLabel = new DSLabel().withCaption(messages.getMessage("summaryStep.info.label")).withStyleName(ValoTheme.LABEL_H4);
            File imageFile = new File(DSConstant.getImageDir() + character.getImage());
            FileResource resource = new FileResource(imageFile);
            Image image = new Image("Image", resource);
            layout.addComponent(image);
            DSLabel nameLabel = new DSLabel(messages.getMessage("summaryStep.name.label"), character.getName());
            DSLabel genderLabel = new DSLabel(messages.getMessage("summaryStep.sex.label"), character.getGender().toString());
            DSLabel ageLabel = new DSLabel(messages.getMessage("summaryStep.age.label"), String.valueOf(character.getAge()));
            DSLabel weightLabel = new DSLabel(messages.getMessage("summaryStep.weight.label"), character.getWeight() + " lbs");
            DSLabel heightLabel = new DSLabel(messages.getMessage("summaryStep.height.label"), character.getHeight());
            DSLabel alignmentLabel = new DSLabel(messages.getMessage("summaryStep.alignment.label"), character.getAlignment().toString());
            DSLabel regionLabel = new DSLabel(messages.getMessage("summaryStep.region.label"), character.getRegion().toString());
            DSLabel backgroundLabel = new DSLabel(messages.getMessage("summaryStep.background.label"),
                    character.getBackground().getBackground().toString());
            layout.addComponents(infoLabel, image, nameLabel, genderLabel, ageLabel, weightLabel, heightLabel, alignmentLabel, regionLabel,
                    backgroundLabel);
        }

        //Starting gold

        return layout;
    }

}
