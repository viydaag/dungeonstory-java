package com.dungeonstory.ui.view.character.wizard.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Terrain;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.ListType;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.UnorderedListType;
import com.dungeonstory.ui.converter.DescriptiveEntityCollectionToStringListConverter;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.layout.FormLayoutNoSpace;
import com.vaadin.data.ValueContext;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ClassChoiceForm extends CharacterWizardStepForm<CharacterClass> implements AbstractForm.SavedHandler<CharacterClass> {

    private static final long serialVersionUID = 6382868944768026273L;

    private DSClass chosenClass = null;
    private Character character;

    private DataService<DSClass, Long>      classService;
    private DataService<CreatureType, Long> creatureTypeService;

    private ComboBox<DSClass>                                classe;
    private SubSetSelector<Skill, Set<Skill>>                classSkills;
    private SubSetSelector<CreatureType, List<CreatureType>> favoredEnnemies;
    private SubSetSelector<Terrain, Set<Terrain>>            favoredTerrains;

    private List<CreatureType> backupfavoredEnnemies;
    private Set<Terrain>       backupfavoredTerrains;

    private FTextArea classDescription;
    private DSLabel    proficienciesLabel;
    private DSLabel    armorProficiencies;
    private DSLabel    weaponProficiencies;
    private DSLabel    savingThrowProficiencies;
    private DSLabel    skillProficiencies;
    private DSLabel    classFeaturesLabel;
    private DSLabel    classFeatures;

    public ClassChoiceForm(Character character) {
        super(CharacterClass.class);
        this.character = character;
        setSavedHandler(this);

        classService = Services.getClassService();
        creatureTypeService = Services.getCreatureTypeService();
    }

    @Override
    protected Component createContent() {

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        HorizontalLayout layout = new HorizontalLayout();

        Messages messages = Messages.getInstance();

        VerticalLayout classFieldsLayout = new VerticalLayout();
        classe = new ComboBox<DSClass>(messages.getMessage("classStep.class.label"), classService.findAll());
        classe.setEmptySelectionAllowed(false);
        classe.setItems(classService.findAll());
        classFieldsLayout.addComponents(classe);

        Set<CharacterClass> assignedClasses = character.getClasses();
        if (!assignedClasses.isEmpty()) {
            DSLabel currentClasses = new DSLabel("Classe(s) courante(s)");
            classFieldsLayout.addComponents(currentClasses);
            for (CharacterClass aClass : assignedClasses) {
                DSLabel classLabel = new DSLabel(aClass.toString());
                classFieldsLayout.addComponents(classLabel);
            }
        }

        classSkills = new SubSetSelector<>(Skill.class);
        classSkills.getGrid().addColumn(Skill::getName).setCaption(messages.getMessage("classStep.proficientSkills.table.column.name")).setId("name");
        classSkills.setItems(EnumSet.allOf(Skill.class));
        classSkills.setVisible(false);
        classSkills.addValueChangeListener(event -> adjustButtons());
        classFieldsLayout.addComponents(classSkills);

        favoredEnnemies = new SubSetSelector<>(CreatureType.class);
        favoredEnnemies.setCaption(messages.getMessage("classStep.favoredEnnemy.label"));
        favoredEnnemies.getGrid().addColumn(CreatureType::getName).setCaption(messages.getMessage("classStep.favoredEnnemy.table.column.name"))
                .setId("name");
        favoredEnnemies.setItems(creatureTypeService.findAll());
        favoredEnnemies.setVisible(false);
        favoredEnnemies.setValue(new ArrayList<>()); //nothing selected

        favoredTerrains = new SubSetSelector<>(Terrain.class);
        favoredTerrains.setCaption(messages.getMessage("classStep.favoredTerrain.label"));
        favoredTerrains.getGrid().addColumn(Terrain::getName).setCaption(messages.getMessage("classStep.favoredTerrain.table.column.name"))
                .setId("name");
        favoredTerrains.setItems(Arrays.asList(Terrain.values()));
        favoredTerrains.setVisible(false);
        favoredTerrains.setValue(new HashSet<>()); //nothing selected


        classFieldsLayout.addComponents(favoredEnnemies, favoredTerrains);

        VerticalLayout classDescriptionLayout = new VerticalLayout();
        classDescription = new FTextArea(messages.getMessage("classStep.class.description")).withFullWidth().withRows(10);

        FormLayout properties = new FormLayout();
        proficienciesLabel = new DSLabel().withStyleName(ValoTheme.LABEL_H4);
        armorProficiencies = new DSLabel();
        weaponProficiencies = new DSLabel();
        savingThrowProficiencies = new DSLabel();
        skillProficiencies = new DSLabel();

        properties.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies, skillProficiencies);

        FormLayoutNoSpace classFeatureLabelLayout = new FormLayoutNoSpace();
        classFeaturesLabel = new DSLabel().withStyleName(ValoTheme.LABEL_H4);
        classFeatureLabelLayout.addComponent(classFeaturesLabel);

        VerticalLayout classFeatureLayout = new VerticalLayout();
        classFeatures = new DSLabel("", ContentMode.HTML);
        classFeatureLayout.addComponents(classFeatures);

        classe.addValueChangeListener(event -> {
            chosenClass = event.getValue();
            if (chosenClass != null) {

                CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);
                int classLevel = 1;

                // show skills only if its a new class
                if (assignedClass == null) {
                    classSkills.setVisible(true);
                    classSkills.setCaption(messages.getMessage("classStep.proficientSkills.label") + " (" + chosenClass.getNbChosenSkills() + ")");
                    classSkills.setItems(ClassUtil.getUnassignedClassSkills(character, chosenClass));
                    classSkills.setValue(EnumSet.noneOf(Skill.class));
                    classSkills.setLimit(chosenClass.getNbChosenSkills());
                } else {
                    classLevel = assignedClass.getClassLevel() + 1;
                }

                // show/hide custom class fields
                Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(chosenClass, classLevel);
                if (classLevelBonusOpt.isPresent()) {
                    ClassLevelBonus classLevelBonus = classLevelBonusOpt.get();
                    if (classLevelBonus.getFavoredEnemy() != null && classLevelBonus.getFavoredEnemy() == true) {
                        favoredEnnemies.setVisible(true);
                    } else {
                        favoredEnnemies.setVisible(false);
                        favoredEnnemies.setValue(backupfavoredEnnemies);
                    }
                    if (classLevelBonus.getNaturalExplorer() != null && classLevelBonus.getNaturalExplorer() == true) {
                        favoredTerrains.setVisible(true);
                    } else {
                        favoredTerrains.setVisible(false);
                        favoredTerrains.setValue(backupfavoredTerrains);
                    }
                } else {
                    favoredEnnemies.setVisible(false);
                    favoredEnnemies.setValue(backupfavoredEnnemies);
                    favoredTerrains.setVisible(false);
                    favoredTerrains.setValue(backupfavoredTerrains);
                }

                // refresh class info
                classDescription.setValue(chosenClass.getDescription());
                proficienciesLabel.withCaption(messages.getMessage("classStep.proficiencies.label"));
                armorProficiencies.withCaption(messages.getMessage("classStep.proficiencies.armor.label"))
                        .withContent(collectionConverter.convertToPresentation(chosenClass.getArmorProficiencies(), new ValueContext()));
                weaponProficiencies.withCaption(messages.getMessage("classStep.proficiencies.weapon.label"))
                        .withContent(collectionConverter.convertToPresentation(chosenClass.getWeaponProficiencies(), new ValueContext()));
                savingThrowProficiencies.withCaption(messages.getMessage("classStep.proficiencies.savingThrow.label"))
                        .withContent(collectionConverter.convertToPresentation(chosenClass.getSavingThrowProficiencies(), new ValueContext()));
                skillProficiencies.withCaption(messages.getMessage("classStep.proficiencies.skill.label"))
                        .withContent(collectionConverter.convertToPresentation(chosenClass.getBaseSkills(), new ValueContext()));
                classFeaturesLabel.withCaption(messages.getMessage("classStep.classFeatures.label"));

                DescriptiveEntityCollectionToStringListConverter<List<?>> listConverter = new DescriptiveEntityCollectionToStringListConverter<List<?>>();
                listConverter.setListType(ListType.UNORDERED);
                listConverter.setUnorderedBullet(UnorderedListType.CIRCLE);
                List<ClassFeature> classFeaturesForLevel = ClassUtil.getClassFeaturesForLevel(chosenClass, classLevel)
                        .filter(cf -> cf.getParent() == null).collect(Collectors.toList());
                classFeatures.withContent(listConverter.convertToPresentation(classFeaturesForLevel, new ValueContext()));

            } else {
                hideClassFields();
            }
        });

        classDescriptionLayout.addComponents(classDescription, properties, classFeatureLabelLayout, classFeatureLayout);

        layout.setSizeFull();
        layout.addComponents(classFieldsLayout, classDescriptionLayout);
        layout.setExpandRatio(classFieldsLayout, 1);
        layout.setExpandRatio(classDescriptionLayout, 2);

        return layout;
    }

    private void hideClassFields() {
        classSkills.setVisible(false);
        favoredEnnemies.setVisible(false);
        favoredTerrains.setVisible(false);

        classDescription.clear();
        proficienciesLabel.setCaption("");
        armorProficiencies.withCaption("").withContent("");
        weaponProficiencies.withCaption("").withContent("");
        savingThrowProficiencies.withCaption("").withContent("");
        skillProficiencies.withCaption("").withContent("");
        classFeaturesLabel.withCaption("").withContent("");
        classFeatures.withCaption("").withContent("");
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        favoredEnnemies.setLimit(character.getFavoredEnnemies().size() + 1);
        favoredTerrains.setLimit(character.getFavoredTerrains().size() + 1);

        backupfavoredEnnemies = new ArrayList<>(character.getFavoredEnnemies());
        backupfavoredTerrains = new HashSet<>(character.getFavoredTerrains());

        classDescription.setReadOnly(true);
    }

    public DSClass getChosenClass() {
        return chosenClass;
    }

    @Override
    public void onSave(CharacterClass entity) {
        DSClass chosenClass = getChosenClass();
        CharacterClass chosenCharacterClass = null;

        // Check if the chosen class is already assigned to the character
        CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);

        if (assignedClass != null) {
            assignedClass.setClassLevel(assignedClass.getClassLevel() + 1);
            chosenCharacterClass = assignedClass;
        } else {
            CharacterClass classe = entity;
            classe.setCharacter(character);
            classe.setClassLevel(1);
            character.getClasses().add(classe);
            chosenCharacterClass = classe;
        }

        // Only if it's a new class, the proficiencies are added
        if (assignedClass == null) {
            character.getArmorProficiencies().addAll(chosenClass.getArmorProficiencies());
            character.getWeaponProficiencies().addAll(chosenClass.getWeaponProficiencies());
            character.getSavingThrowProficiencies().addAll(chosenClass.getSavingThrowProficiencies());
            //TODO : check rules for multi-classing
        }

        // Add class features for that level
        chosenCharacterClass.getClassFeatures().addAll(ClassUtil.getClassFeaturesForLevel(chosenClass, chosenCharacterClass.getClassLevel())
                .filter(cf -> cf.getParent() == null).collect(Collectors.toList()));

        // Remove class features that are replaced with new ones
        List<ClassFeature> featuresToRemove = new ArrayList<>();
        for (ClassFeature feature : chosenCharacterClass.getClassFeatures()) {
            if (feature.getReplacement() != null) {
                featuresToRemove.add(feature.getReplacement());
            }
        }
        chosenCharacterClass.getClassFeatures().removeAll(featuresToRemove);

        // Add skill proficiencies if new class
        if (classSkills.getValue() != null && assignedClass == null) {
            character.getSkillProficiencies().addAll(classSkills.getValue());
        }

        // On character creation, give starting gold
        if (character.getId() == null) {
            character.setGold(chosenClass.getStartingGold());
        }

    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = getBinder().isValid();
            boolean requiredFieldsFilled = true;
            if (chosenClass == null || (classSkills.isVisible() && classSkills.getValue().size() < chosenClass.getNbChosenSkills())) {
                requiredFieldsFilled = false;
            }
            getSaveButton().setEnabled(requiredFieldsFilled && valid);
        }
    }

}
