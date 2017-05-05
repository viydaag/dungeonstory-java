package com.dungeonstory.view.character;

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
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Terrain;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.DivineDomainDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.converter.CollectionToStringListConverter.ListType;
import com.dungeonstory.util.converter.CollectionToStringListConverter.UnorderedListType;
import com.dungeonstory.util.converter.DescriptiveEntityCollectionToStringListConverter;
import com.dungeonstory.util.field.DSSubSetSelector2;
import com.dungeonstory.util.layout.FormLayoutNoSpace;
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

public class ClassChoiceForm extends DSAbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 6382868944768026273L;

    private DSClass chosenClass = null;

    private DataService<DSClass, Long>      classService;
    private DataService<Skill, Long>        skillService;
    private DataService<CreatureType, Long> creatureTypeService;
    private DataService<Deity, Long>        deityService;
    private DivineDomainDataService         domainService;

    //    private ComboBox<DSClass>              classe;
    private DSSubSetSelector2<DSClass, List<DSClass>> classes;
    private DSSubSetSelector2<Skill, List<Skill>>               classSkills;
    private DSSubSetSelector2<CreatureType, List<CreatureType>> favoredEnnemies;
    private DSSubSetSelector2<Terrain, Set<Terrain>>            favoredTerrains;
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

    public ClassChoiceForm() {
        super(Character.class);
        setSavedHandler(this);

        classService = Services.getClassService();
        skillService = Services.getSkillService();
        creatureTypeService = Services.getCreatureTypeService();
        deityService = Services.getDeityService();
        domainService = Services.getDivineDomainService();
    }

    @Override
    protected Component createContent() {

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        HorizontalLayout layout = new HorizontalLayout();

        Messages messages = Messages.getInstance();

         VerticalLayout classFieldsLayout = new VerticalLayout();
//        classe = new ComboBox<DSClass>(messages.getMessage("classStep.class.label"), classService.findAll());
//        classe.setEmptySelectionAllowed(false);
//        classe.setRequiredIndicatorVisible(true);
//        classe.addValidator(new NullValidator(messages.getMessage("classStep.class.validator"), false));
//        classe.addValueChangeListener(event -> {
//            getFieldGroup().setBeanModified(true);
//            onFieldGroupChange(getFieldGroup());
//        });
        classes = new DSSubSetSelector2<>(DSClass.class);
        classes.setCaption(messages.getMessage("classStep.class.label"));
        //        classes.setVisibleProperties("name");
        //        classes.setColumnHeader("name", "");
        classes.getGrid().addColumn(DSClass::getName).setCaption("").setId("name");
        classes.setItems(classService.findAll());

        classSkills = new DSSubSetSelector2<>(Skill.class);
        classSkills.setCaption(messages.getMessage("classStep.proficientSkills.label"));
        //        classSkills.setVisibleProperties("name");
        //        classSkills.setColumnHeader("name", messages.getMessage("classStep.proficientSkills.table.column.name"));
        classSkills.getGrid().addColumn(Skill::getName).setCaption(messages.getMessage("classStep.proficientSkills.table.column.name")).setId("name");
        classSkills.setItems(skillService.findAll());
        classSkills.setVisible(false);

        favoredEnnemies = new DSSubSetSelector2<>(CreatureType.class);
        favoredEnnemies.setCaption(messages.getMessage("classStep.favoredEnnemy.label"));
        //        favoredEnnemies.setVisibleProperties("name");
        //        favoredEnnemies.setColumnHeader("name", messages.getMessage("classStep.favoredEnnemy.table.column.name"));
        favoredEnnemies.getGrid().addColumn(CreatureType::getName).setCaption(messages.getMessage("classStep.favoredEnnemy.table.column.name"))
                .setId("name");
        favoredEnnemies.setItems(creatureTypeService.findAll());
        favoredEnnemies.setVisible(false);
        //        favoredEnnemies.setValue(null); //nothing selected

        favoredTerrains = new DSSubSetSelector2<>(Terrain.class);
        favoredTerrains.setCaption(messages.getMessage("classStep.favoredTerrain.label"));
        //        favoredTerrains.setVisibleProperties("name");
        //        favoredTerrains.setColumnHeader("name", messages.getMessage("classStep.favoredTerrain.table.column.name"));
        favoredTerrains.getGrid().addColumn(Terrain::getName).setCaption(messages.getMessage("classStep.favoredTerrain.table.column.name"))
                .setId("name");
        favoredTerrains.setItems(Arrays.asList(Terrain.values()));
        favoredTerrains.setVisible(false);

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

        classFieldsLayout.addComponents(classes, classSkills, favoredEnnemies, favoredTerrains, deity, deityDescription, divineDomain,
                domainDescription);

        VerticalLayout classDescriptionLayout = new VerticalLayout();
        classDescription = new DSTextArea(messages.getMessage("classStep.class.description")).withFullWidth().withRows(10);

        FormLayout properties = new FormLayout();
        proficienciesLabel = new MLabel().withStyleName(ValoTheme.LABEL_H4);
        armorProficiencies = new MLabel();
        weaponProficiencies = new MLabel();
        savingThrowProficiencies = new MLabel();
        skillProficiencies = new MLabel();

        properties.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies,
                skillProficiencies);

        FormLayoutNoSpace classFeatureLabelLayout = new FormLayoutNoSpace();
        classFeaturesLabel = new MLabel().withStyleName(ValoTheme.LABEL_H4);
        classFeatureLabelLayout.addComponent(classFeaturesLabel);

        VerticalLayout classFeatureLayout = new VerticalLayout();
        classFeatures = new MLabel().withContentMode(ContentMode.HTML);
        classFeatureLayout.addComponents(classFeatures);

        classes.getComboBox().addValueChangeListener(event -> {
            chosenClass = event.getValue();
            if (chosenClass != null) {

                Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(getEntity(), chosenClass);
                int classLevel = 1;

                // show skills only if its a new class
                if (!assignedClass.isPresent()) {
                    classSkills.setVisible(true);
                    classSkills.setItems(new ArrayList<Skill>(chosenClass.getBaseSkills()));
                    classSkills.setValue(new ArrayList<>());
                    classSkills.setLimit(chosenClass.getNbChosenSkills());
                } else {
                    classLevel = assignedClass.get().getClassLevel() + 1;
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
                classDescription.setReadOnly(false);
                classDescription.setValue(chosenClass.getDescription());
                classDescription.setReadOnly(true);
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

                DescriptiveEntityCollectionToStringListConverter listConverter = new DescriptiveEntityCollectionToStringListConverter();
                listConverter.setListType(ListType.UNORDERED);
                listConverter.setUnorderedBullet(UnorderedListType.CIRCLE);
                List<Feat> classFeaturesForLevel = ClassUtil.getClassFeaturesForLevel(chosenClass, classLevel);
                classFeatures
                        .withContent(listConverter.convertToPresentation(classFeaturesForLevel, String.class, null));

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

        classDescription.setReadOnly(false);
        classDescription.clear();
        classDescription.setReadOnly(true);
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
        favoredEnnemies.setLimit(getEntity().getFavoredEnnemies().size() + 1);
        favoredTerrains.setLimit(getEntity().getFavoredTerrains().size() + 1);

        backupfavoredEnnemies = new ArrayList<>(getEntity().getFavoredEnnemies());
        backupfavoredTerrains = new HashSet<>(getEntity().getFavoredTerrains());
        if (getEntity().getDeity() != null) {
            backupDeity = getEntity().getDeity().clone();
        }
        if (getEntity().getDivineDomain() != null) {
            backupDivineDomain = getEntity().getDivineDomain().clone();
        }

        classDescription.setReadOnly(true);
    }

    public DSClass getChosenClass() {
        return chosenClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSave(Character entity) {
        DSClass chosenClass = getChosenClass();
        CharacterClass chosenCharacterClass = null;

        Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(entity, chosenClass);

        if (assignedClass.isPresent()) {
            assignedClass.get().setClassLevel(assignedClass.get().getClassLevel() + 1);
            chosenCharacterClass = assignedClass.get();
        } else {
            CharacterClass classe = new CharacterClass();
            classe.setCharacter(entity);
            classe.setClasse(chosenClass);
            classe.setClassLevel(1);
            entity.getClasses().add(classe);
            chosenCharacterClass = classe;
        }

        entity.getArmorProficiencies().addAll(chosenClass.getArmorProficiencies());
        entity.getWeaponProficiencies().addAll(chosenClass.getWeaponProficiencies());
        entity.getFeats().addAll(ClassUtil.getClassFeaturesForLevel(chosenClass, chosenCharacterClass.getClassLevel()));

        if (classSkills.getValue() != null) {
            entity.getSkillProficiencies().addAll(classSkills.getValue());
        }

    }

}
