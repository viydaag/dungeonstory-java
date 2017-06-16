package com.dungeonstory.ui.view.character.wizard.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Terrain;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.DivineDomainDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.ListType;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.UnorderedListType;
import com.dungeonstory.ui.converter.DescriptiveEntityCollectionToStringListConverter;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.layout.FormLayoutNoSpace;
import com.vaadin.data.ValueContext;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ClassChoiceForm extends DSAbstractForm<CharacterClass> implements AbstractForm.SavedHandler<CharacterClass> {

    private static final long serialVersionUID = 6382868944768026273L;

    private DSClass chosenClass = null;
    private Character character;

    private DataService<DSClass, Long>      classService;
    private DataService<Skill, Long>        skillService;
    private DataService<CreatureType, Long> creatureTypeService;
    private DataService<Deity, Long>        deityService;
    private DivineDomainDataService         domainService;

    private ComboBox<DSClass> classe;
    private SubSetSelector<Skill, List<Skill>>               classSkills;
    private SubSetSelector<CreatureType, List<CreatureType>> favoredEnnemies;
    private SubSetSelector<Terrain, Set<Terrain>>            favoredTerrains;
    private ComboBox<Deity>                deity;
    private ComboBox<DivineDomain>         divineDomain;

    private Label deityDescription;
    private Label domainDescription;

    private List<CreatureType> backupfavoredEnnemies;
    private Set<Terrain>       backupfavoredTerrains;
    private Deity              backupDeity;
    private DivineDomain       backupDivineDomain;

    private DSTextArea classDescription;
    private MLabel    proficienciesLabel;
    private MLabel    armorProficiencies;
    private MLabel    weaponProficiencies;
    private MLabel    savingThrowProficiencies;
    private MLabel    skillProficiencies;
    private MLabel    classFeaturesLabel;
    private MLabel    classFeatures;

    public ClassChoiceForm(Character character) {
        super(CharacterClass.class);
        this.character = character;
        setSavedHandler(this);

        classService = Services.getClassService();
        skillService = Services.getSkillService();
        creatureTypeService = Services.getCreatureTypeService();
        deityService = Services.getDeityService();
        domainService = Services.getDivineDomainService();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Component createContent() {

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        HorizontalLayout layout = new HorizontalLayout();

        Messages messages = Messages.getInstance();

        VerticalLayout classFieldsLayout = new VerticalLayout();
        classe = new ComboBox<DSClass>(messages.getMessage("classStep.class.label"), classService.findAll());
        classe.setEmptySelectionAllowed(false);
        classe.setItems(classService.findAll());

        classSkills = new SubSetSelector<>(Skill.class);
        classSkills.getGrid().addColumn(Skill::getName).setCaption(messages.getMessage("classStep.proficientSkills.table.column.name")).setId("name");
        classSkills.setItems(skillService.findAll());
        classSkills.setVisible(false);

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

        deity = new ComboBox<Deity>(messages.getMessage("classStep.deity.label"), deityService.findAllOrderBy("name", "ASC"));
        deity.setVisible(false);
        divineDomain = new ComboBox<DivineDomain>(messages.getMessage("classStep.divineDomain.label"));
        divineDomain.setEmptySelectionAllowed(false);
        divineDomain.setVisible(false);
        deityDescription = new Label();
        domainDescription = new Label();

        deity.addValueChangeListener(event -> {
            Deity value = event.getValue();
            if (value != null) {
                deityDescription.setValue(value.getShortDescription());
                divineDomain.setItems(domainService.findAllByDeity(value));
                ListDataProvider<DivineDomain> domainProvider = (ListDataProvider<DivineDomain>) divineDomain.getDataProvider();
                List<DivineDomain> items = (List<DivineDomain>) domainProvider.getItems();
                if (items.size() >= 1) {
                    divineDomain.setValue(items.get(0));
                    domainDescription.setValue(divineDomain.getValue().getDescription());
                } else {
                    divineDomain.setValue(null);
                    domainDescription.setValue("");
                }
            } else {
                deityDescription.setValue("");
                divineDomain.setValue(null);
                domainDescription.setValue("");
            }
        });

        divineDomain.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                domainDescription.setValue(event.getValue().getDescription());
            } else {
                domainDescription.setValue("");
            }
        });

        classFieldsLayout.addComponents(classe, classSkills, favoredEnnemies, favoredTerrains, deity, deityDescription, divineDomain,
                domainDescription);

        VerticalLayout classDescriptionLayout = new VerticalLayout();
        classDescription = new DSTextArea(messages.getMessage("classStep.class.description")).withFullWidth().withRows(10);

        FormLayout properties = new FormLayout();
        proficienciesLabel = new MLabel().withStyleName(ValoTheme.LABEL_H4);
        armorProficiencies = new MLabel();
        weaponProficiencies = new MLabel();
        savingThrowProficiencies = new MLabel();
        skillProficiencies = new MLabel();

        properties.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies, skillProficiencies);

        FormLayoutNoSpace classFeatureLabelLayout = new FormLayoutNoSpace();
        classFeaturesLabel = new MLabel().withStyleName(ValoTheme.LABEL_H4);
        classFeatureLabelLayout.addComponent(classFeaturesLabel);

        VerticalLayout classFeatureLayout = new VerticalLayout();
        classFeatures = new MLabel().withContentMode(ContentMode.HTML);
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
                    classSkills.setItems(new ArrayList<Skill>(chosenClass.getBaseSkills()));
                    classSkills.setValue(new ArrayList<>());
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
                    if (classLevelBonus.getDeity() != null && classLevelBonus.getDeity() == true) {
                        deity.setVisible(true);
                        divineDomain.setVisible(true);
                    } else {
                        deity.setVisible(false);
                        deity.setValue(backupDeity);
                        divineDomain.setVisible(false);
                        divineDomain.setValue(backupDivineDomain);
                    }
                } else {
                    favoredEnnemies.setVisible(false);
                    favoredEnnemies.setValue(backupfavoredEnnemies);
                    favoredTerrains.setVisible(false);
                    favoredTerrains.setValue(backupfavoredTerrains);
                    deity.setVisible(false);
                    deity.setValue(backupDeity);
                    divineDomain.setVisible(false);
                    divineDomain.setValue(backupDivineDomain);
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
                List<ClassFeature> classFeaturesForLevel = ClassUtil.getClassFeaturesForLevel(chosenClass, classLevel);
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
        deity.setVisible(false);
        divineDomain.setVisible(false);
        deityDescription.setValue("");
        domainDescription.setValue("");

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
        if (character.getDeity() != null) {
            backupDeity = character.getDeity().clone();
        }
        if (character.getDivineDomain() != null) {
            backupDivineDomain = character.getDivineDomain().clone();
        }

        classDescription.setReadOnly(true);
        //        classe.setLimit(classes.getValue().size() + 1);
    }

    public DSClass getChosenClass() {
        return chosenClass;
    }

    @Override
    public void onSave(CharacterClass entity) {
        DSClass chosenClass = getChosenClass();
        CharacterClass chosenCharacterClass = null;

        CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);

        if (assignedClass != null) {
            assignedClass.setClassLevel(assignedClass.getClassLevel() + 1);
            chosenCharacterClass = assignedClass;
        } else {
            CharacterClass classe = getEntity();
            classe.setCharacter(character);
            classe.setClassLevel(1);
            character.getClasses().add(classe);
            chosenCharacterClass = classe;
        }

        character.getArmorProficiencies().addAll(chosenClass.getArmorProficiencies());
        character.getWeaponProficiencies().addAll(chosenClass.getWeaponProficiencies());

        character.getClassFeatures().addAll(ClassUtil.getClassFeaturesForLevel(chosenClass, chosenCharacterClass.getClassLevel()));
        List<ClassFeature> featuresToRemove = new ArrayList<>();
        for (ClassFeature feature : character.getClassFeatures()) {
            if (feature.getReplacement() != null) {
                featuresToRemove.add(feature.getReplacement());
            }
        }
        character.getClassFeatures().removeAll(featuresToRemove);

        if (classSkills.getValue() != null) {
            character.getSkillProficiencies().addAll(classSkills.getValue());
        }

    }

}
