package com.dungeonstory.ui.view.character.wizard;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.enums.ArmorType;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.rules.Rules;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.captionGenerator.ClassLevelCaptionGenerator;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.converter.CollectionGenericToStringConverter;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.util.DSConstant;
import com.vaadin.data.ValueContext;
import com.vaadin.fluent.ui.FFormLayout;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SummaryStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -8516216761141601232L;

    private Layout layout;

    public SummaryStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("summaryStep.caption");
    }

    @Override
    public Component getContent() {

        layout = new VerticalLayout();

        showLevelInfo();

        showFeatures();

        showAbilities();

        showProficiencies();

        showCharacterInfo();

        return layout;
    }

    private void showFeatures() {

        Messages messages = Messages.getInstance();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        FormLayout featureLayout = new FFormLayout().withMargin(true);
        Panel featurePanel = new Panel(messages.getMessage("summaryStep.newFeat.label"), featureLayout);

        //Feats
        Set<Feat> featSet = new HashSet<Feat>(character.getFeats());
        featSet.removeAll(original.getFeats());
        if (!featSet.isEmpty()) {
            DSLabel featsLabel = new DSLabel(messages.getMessage("summaryStep.feat.label"),
                    collectionConverter.convertToPresentation(featSet, new ValueContext()));
            featureLayout.addComponent(featsLabel);
        }

        //Class features
        Set<ClassFeature> assignedClassFeatures = ClassUtil.getAllCharacterClassFeatures(original);
        Set<ClassFeature> newClassFeatures = ClassUtil
                .getClassFeaturesForLevel(wizard.getChosenClass(),
                        ClassUtil.getCharacterClass(character, wizard.getChosenClass()).getClassLevel())
                .collect(Collectors.toSet());
        newClassFeatures.removeAll(assignedClassFeatures);
        if (!newClassFeatures.isEmpty()) {
            DSLabel classFeatureLabel = new DSLabel(messages.getMessage("summaryStep.classFeature.label"),
                    collectionConverter.convertToPresentation(newClassFeatures, new ValueContext()));
            featureLayout.addComponent(classFeatureLabel);
        }

        //Class specialization features
        CharacterClass cc = ClassUtil.getCharacterClass(character, wizard.getChosenClass());
        if (cc.getClassSpecialization() != null) {
            List<ClassFeature> newClassSpecFeatures = ClassUtil.getClassFeaturesForLevel(cc.getClassSpecialization(),
                    cc.getClassLevel());
            if (!newClassSpecFeatures.isEmpty()) {
                DSLabel classSpecFeatureLabel = new DSLabel(messages.getMessage("summaryStep.classSpecFeature.label"),
                        collectionConverter.convertToPresentation(newClassSpecFeatures, new ValueContext()));
                featureLayout.addComponent(classSpecFeatureLabel);
            }
        }

        if (featureLayout.getComponentCount() > 0) {
            layout.addComponent(featurePanel);
        }

    }

    private void showCharacterInfo() {

        Messages messages = Messages.getInstance();
        Character character = wizard.getCharacter();

        if (character.getId() == null) {
            FormLayout infoLayout = new FFormLayout().withMargin(true);
            Panel infoPanel = new Panel(messages.getMessage("summaryStep.info.label"), infoLayout);
            layout.addComponent(infoPanel);

            File imageFile = new File(DSConstant.getImageDir() + character.getImage());
            FileResource resource = new FileResource(imageFile);
            Image image = new Image("Image", resource);
            DSLabel nameLabel = new DSLabel(messages.getMessage("summaryStep.name.label"), character.getName());
            DSLabel genderLabel = new DSLabel(messages.getMessage("summaryStep.sex.label"),
                    character.getGender().toString());
            DSLabel ageLabel = new DSLabel(messages.getMessage("summaryStep.age.label"),
                    String.valueOf(character.getAge()));
            DSLabel weightLabel = new DSLabel(messages.getMessage("summaryStep.weight.label"),
                    character.getWeight() + " lbs");
            DSLabel heightLabel = new DSLabel(messages.getMessage("summaryStep.height.label"), character.getHeight());
            DSLabel alignmentLabel = new DSLabel(messages.getMessage("summaryStep.alignment.label"),
                    character.getAlignment().toString());
            DSLabel regionLabel = new DSLabel(messages.getMessage("summaryStep.region.label"),
                    character.getRegion().toString());
            DSLabel backgroundLabel = new DSLabel(messages.getMessage("summaryStep.background.label"),
                    character.getBackground().getBackground().toString());
            DSLabel goldLabel = new DSLabel(messages.getMessage("summaryStep.startingGold.label"), character.getGold());
            infoLayout.addComponents(image, nameLabel, genderLabel, ageLabel, weightLabel, heightLabel, alignmentLabel,
                    regionLabel, backgroundLabel, goldLabel);
        }
    }

    private void showLevelInfo() {

        Messages messages = Messages.getInstance();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        FormLayout levelLayout = new FFormLayout().withMargin(true);
        Panel levelPanel = new Panel(messages.getMessage("summaryStep.level.label"), levelLayout);
        layout.addComponent(levelPanel);

        Level levelUp = null;
        if (character.getId() == null) {
            levelUp = character.getLevel();
        } else {
            levelUp = Services.getLevelService().getNextLevel(character.getLevel());
        }
        DSLabel levelLabel = new DSLabel(messages.getMessage("summaryStep.level.label"), levelUp.toString());
        levelLayout.addComponent(levelLabel);

        CollectionGenericToStringConverter<Language> languageConverter = new CollectionGenericToStringConverter<>();

        if (character.getId() == null) {
            DSLabel raceLabel = new DSLabel(messages.getMessage("summaryStep.race.label"),
                    character.getRace().getName());
            levelLayout.addComponent(raceLabel);
            DSLabel languagesLabel = new DSLabel(messages.getMessage("summaryStep.language.label"),
                    languageConverter.convertToPresentation(character.getLanguages(), new ValueContext()));
            levelLayout.addComponent(languagesLabel);
        }

        ClassLevelCaptionGenerator classCollectionConverter = new ClassLevelCaptionGenerator();
        DSLabel classesLabel = new DSLabel(messages.getMessage("summaryStep.class.label"),
                classCollectionConverter.apply(character.getClasses()));
        levelLayout.addComponent(classesLabel);

        int nbLifePoints = Rules.calculateCharacterLifePoints(character, levelUp.getId().intValue());
        int difLifePoints = nbLifePoints - original.getLifePoints();

        if (character.getId() == null) {
            DSLabel lifePoints = new DSLabel(messages.getMessage("summaryStep.lifePoint.label"),
                    String.valueOf(nbLifePoints));
            levelLayout.addComponent(lifePoints);
        } else {
            DSLabel lifePointsLabel = new DSLabel(messages.getMessage("summaryStep.lifePoint.label"),
                    String.valueOf(nbLifePoints) + " (+" + difLifePoints + ")");
            levelLayout.addComponent(lifePointsLabel);
        }
        character.setLifePoints(nbLifePoints);
    }

    private void showAbilities() {

        Messages messages = Messages.getInstance();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        FormLayout abilityLayout = new FFormLayout().withMargin(true);
        Panel abilityPanel = new Panel(messages.getMessage("summaryStep.ability.label"), abilityLayout);
        if (character.getId() == null) {
            DSLabel strengthLabel = new DSLabel(Ability.STRENGTH.getName(), String.valueOf(character.getStrength()));
            DSLabel dexterityLabel = new DSLabel(Ability.DEXTERITY.getName(), String.valueOf(character.getDexterity()));
            DSLabel constitutionLabel = new DSLabel(Ability.CONSTITUTION.getName(), String.valueOf(character.getConstitution()));
            DSLabel intelligneceLabel = new DSLabel(Ability.INTELLIGENCE.getName(), String.valueOf(character.getIntelligence()));
            DSLabel wisdoDSLabel = new DSLabel(Ability.WISDOM.getName(), String.valueOf(character.getWisdom()));
            DSLabel charismaLabel = new DSLabel(Ability.CHARISMA.getName(), String.valueOf(character.getCharisma()));
            abilityLayout.addComponents(strengthLabel, dexterityLabel, constitutionLabel, intelligneceLabel,
                    wisdoDSLabel, charismaLabel);
            layout.addComponent(abilityPanel);
        } else {
            if (original.getStrength() != character.getStrength()) {
                DSLabel strengthLabel = new DSLabel(Ability.STRENGTH.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getStrength(), original.getStrength()));
                abilityLayout.addComponents(strengthLabel);
            }
            if (original.getDexterity() != character.getDexterity()) {
                DSLabel dexterityLabel = new DSLabel(Ability.DEXTERITY.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getDexterity(), original.getDexterity()));
                abilityLayout.addComponents(dexterityLabel);
            }
            if (original.getConstitution() != character.getConstitution()) {
                DSLabel constitutionLabel = new DSLabel(Ability.CONSTITUTION.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getConstitution(), original.getConstitution()));
                abilityLayout.addComponents(constitutionLabel);
            }
            if (original.getIntelligence() != character.getIntelligence()) {
                DSLabel intelligenceLabel = new DSLabel(Ability.INTELLIGENCE.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getIntelligence(), original.getIntelligence()));
                abilityLayout.addComponents(intelligenceLabel);
            }
            if (original.getWisdom() != character.getWisdom()) {
                DSLabel wisdomLabel = new DSLabel(Ability.WISDOM.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getWisdom(), original.getWisdom()));
                abilityLayout.addComponents(wisdomLabel);
            }
            if (original.getCharisma() != character.getCharisma()) {
                DSLabel charismaLabel = new DSLabel(Ability.CHARISMA.getName(),
                        ModifierUtil.getScoreDifferenceString(character.getCharisma(), original.getCharisma()));
                abilityLayout.addComponents(charismaLabel);
            }
            if (abilityLayout.getComponentCount() > 0) {
                layout.addComponents(abilityPanel);
            }
        }
    }

    private void showProficiencies() {

        Messages messages = Messages.getInstance();
        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        FormLayout proficiencyLayout = new FFormLayout().withMargin(true);
        Panel proficiencyPanel = new Panel(messages.getMessage("summaryStep.proficiency.label"), proficiencyLayout);
        if (character.getId() == null) {

            DSLabel armorProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.armor.label"),
                    collectionConverter.convertToPresentation(character.getArmorProficiencies(), new ValueContext()));
            DSLabel weaponProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.weapon.label"),
                    collectionConverter.convertToPresentation(character.getWeaponProficiencies(), new ValueContext()));
            DSLabel savingThrowProficienciesLabel = new DSLabel(
                    messages.getMessage("summaryStep.proficiency.savingThrow.label"), collectionConverter
                            .convertToPresentation(character.getSavingThrowProficiencies(), new ValueContext()));
            DSLabel skillProficienciesLabel = new DSLabel(messages.getMessage("summaryStep.proficiency.skill.label"),
                    collectionConverter.convertToPresentation(character.getSkillProficiencies(), new ValueContext()));
            proficiencyLayout.addComponents(armorProficienciesLabel, weaponProficienciesLabel,
                    savingThrowProficienciesLabel, skillProficienciesLabel);
            layout.addComponent(proficiencyPanel);

        } else {
            Set<ArmorType.ProficiencyType> gainedArmorProficiencies = character.getArmorProficiencies()
                    .stream()
                    .filter(ap -> !original.getArmorProficiencies().contains(ap))
                    .collect(Collectors.toSet());
            Set<WeaponType> gainedWeaponProficiencies = character.getWeaponProficiencies()
                    .stream()
                    .filter(wp -> !original.getWeaponProficiencies().contains(wp))
                    .collect(Collectors.toSet());
            Set<Ability> gainedSavingThrowProficiencies = character
                    .getSavingThrowProficiencies()
                    .stream()
                    .filter(stp -> !original.getSavingThrowProficiencies().contains(stp))
                    .collect(Collectors.toSet());
            Set<Skill> gainedSkillProficiencies = character.getSkillProficiencies()
                    .stream()
                    .filter(sp -> !original.getSkillProficiencies().contains(sp))
                    .collect(Collectors.toSet());
            if (!gainedArmorProficiencies.isEmpty()) {
                DSLabel armorProficienciesLabel = new DSLabel(
                        messages.getMessage("summaryStep.proficiency.armor.label"),
                        collectionConverter.convertToPresentation(gainedArmorProficiencies, new ValueContext()));
                proficiencyLayout.addComponent(armorProficienciesLabel);
            }
            if (!gainedWeaponProficiencies.isEmpty()) {
                DSLabel weaponProficienciesLabel = new DSLabel(
                        messages.getMessage("summaryStep.proficiency.weapon.label"),
                        collectionConverter.convertToPresentation(gainedWeaponProficiencies, new ValueContext()));
                proficiencyLayout.addComponent(weaponProficienciesLabel);
            }
            if (!gainedSavingThrowProficiencies.isEmpty()) {
                DSLabel savingThrowProficienciesLabel = new DSLabel(
                        messages.getMessage("summaryStep.proficiency.savingThrow.label"),
                        collectionConverter.convertToPresentation(gainedSavingThrowProficiencies, new ValueContext()));
                proficiencyLayout.addComponent(savingThrowProficienciesLabel);
            }
            if (!gainedSkillProficiencies.isEmpty()) {
                DSLabel skillProficienciesLabel = new DSLabel(
                        messages.getMessage("summaryStep.proficiency.skill.label"),
                        collectionConverter.convertToPresentation(gainedSkillProficiencies, new ValueContext()));
                proficiencyLayout.addComponent(skillProficienciesLabel);
            }

            if (proficiencyLayout.getComponentCount() > 0) {
                layout.addComponent(proficiencyPanel);
            }
        }
    }

}
