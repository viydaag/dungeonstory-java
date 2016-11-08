package com.dungeonstory.view.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Terrain;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.CreatureTypeService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.converter.CollectionToStringListConverter.ListType;
import com.dungeonstory.util.converter.CollectionToStringListConverter.UnorderedListType;
import com.dungeonstory.util.converter.DescriptiveEntityCollectionToStringListConverter;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.layout.FormLayoutNoSpace;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ClassChoiceForm extends DSAbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 6382868944768026273L;

    private ClassService        classService        = ClassService.getInstance();
    private SkillService        skillService        = SkillService.getInstance();
    private CreatureTypeService creatureTypeService = CreatureTypeService.getInstance();

    TypedSelect<DSClass>           classe;
    DSSubSetSelector<Skill>        classSkills;
    DSSubSetSelector<CreatureType> favoredEnnemies;
    DSSubSetSelector<Terrain>      favoredTerrains;

    List<CreatureType> backupfavoredEnnemies;
    Set<Terrain>       backupfavoredTerrains;

    private MTextArea classDescription;
    private MLabel    proficienciesLabel;
    private MLabel    armorProficiencies;
    private MLabel    weaponProficiencies;
    private MLabel    savingThrowProficiencies;
    private MLabel    skillProficiencies;
    private MLabel    classFeaturesLabel;
    private MLabel    classFeatures;

    public ClassChoiceForm() {
        super();
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();

        VerticalSpacedLayout classFieldsLayout = new VerticalSpacedLayout();
        classe = new TypedSelect<DSClass>("Choix de classe", classService.findAll())
                .asComboBoxType().withNullSelectionAllowed(false);
        classe.setRequired(true);
        classe.setImmediate(true);
        classe.addValidator(new NullValidator("La classe ets obligatoire", false));
        classe.addMValueChangeListener(event -> {
            getFieldGroup().setBeanModified(true);
            onFieldGroupChange(getFieldGroup());
        });

        classSkills = new DSSubSetSelector<Skill>(Skill.class);
        classSkills.setCaption("Maitrise de compétence");
        classSkills.setVisibleProperties("name");
        classSkills.setColumnHeader("name", "Compétence");
        classSkills.setOptions(skillService.findAll());
        classSkills.setVisible(false);

        favoredEnnemies = new DSSubSetSelector<CreatureType>(CreatureType.class);
        favoredEnnemies.setCaption("Type de créature favori");
        favoredEnnemies.setVisibleProperties("name");
        favoredEnnemies.setColumnHeader("name", "Type de créature");
        favoredEnnemies.setOptions(creatureTypeService.findAll());
        //        favoredEnnemies.setWidth("80%");
        favoredEnnemies.setVisible(false);
        //        favoredEnnemies.setValue(null); //nothing selected

        favoredTerrains = new DSSubSetSelector<Terrain>(Terrain.class);
        favoredTerrains.setCaption("Terrain favori");
        favoredTerrains.setVisibleProperties("name");
        favoredTerrains.setColumnHeader("name", "Terrain");
        favoredTerrains.setOptions(Arrays.asList(Terrain.values()));
        //        favoredTerrains.setWidth("80%");
        favoredTerrains.setVisible(false);

        classFieldsLayout.addComponents(classe, classSkills, favoredEnnemies, favoredTerrains);

        VerticalLayout classDescriptionLayout = new VerticalLayout();
        classDescription = new MTextArea("Description").withRows(10);

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

        classe.addMValueChangeListener(event -> {
            DSClass chosenClass = event.getValue();
            if (chosenClass != null) {

                Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(getEntity(), chosenClass);
                int classLevel = 1;

                //show skills only if its a new class
                if (!assignedClass.isPresent()) {
                    classSkills.setVisible(true);
                    classSkills.setOptions(new ArrayList<Skill>(chosenClass.getBaseSkills()));
                    classSkills.setValue(new ArrayList<>());
                    classSkills.setLimit(chosenClass.getNbChosenSkills());
                } else {
                    classLevel = assignedClass.get().getClassLevel() + 1;
                }

                //show/hide custom class fields
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

                //refresh class info
                classDescription.setValue(chosenClass.getDescription());
                proficienciesLabel.withCaption("Maitrises");
                armorProficiencies.withCaption("Armures :").withContent(collectionConverter
                        .convertToPresentation(chosenClass.getArmorProficiencies(), String.class, null));
                weaponProficiencies.withCaption("Armes :").withContent(collectionConverter
                        .convertToPresentation(chosenClass.getWeaponProficiencies(), String.class, null));
                savingThrowProficiencies.withCaption("Jets de sauvegarde :").withContent(collectionConverter
                        .convertToPresentation(chosenClass.getSavingThrowProficiencies(), String.class, null));
                skillProficiencies.withCaption("Compétences disponibles :").withContent(
                        collectionConverter.convertToPresentation(chosenClass.getBaseSkills(), String.class, null));
                classFeaturesLabel.withCaption("Dons de classe pour ce niveau");

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
        favoredEnnemies.setLimit(getEntity().getFavoredEnnemies().size() + 1);
        favoredTerrains.setLimit(getEntity().getFavoredTerrains().size() + 1);

        backupfavoredEnnemies = new ArrayList<>(getEntity().getFavoredEnnemies());
        backupfavoredTerrains = new HashSet<>(getEntity().getFavoredTerrains());
    }

    public TypedSelect<DSClass> getClasse() {
        return classe;
    }

    @Override
    public void onSave(Character entity) {
        DSClass chosenClass = getClasse().getValue();
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
