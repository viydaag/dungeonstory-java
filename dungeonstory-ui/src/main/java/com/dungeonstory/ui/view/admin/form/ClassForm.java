package com.dungeonstory.ui.view.admin.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.ClassEquipment;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.ClassSpecializationDataService;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.LevelDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SkillDataService;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.backend.service.WeaponTypeDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.dungeonstory.ui.field.LevelBonusCollectionField;
import com.dungeonstory.ui.field.LevelBonusCollectionField.ClassLevelBonusRow;
import com.dungeonstory.ui.field.LevelSpellsCollectionField;
import com.dungeonstory.ui.field.LevelSpellsCollectionField.LevelSpellsRow;
import com.dungeonstory.ui.field.SubSetSelector;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class ClassForm extends DSAbstractForm<DSClass> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField                                                                 name;
    private TextField                                                                 shortDescription;
    private DSTextArea                                                                description;
    private IntegerField                                                              lifePointPerLevel;
    private IntegerField                                                              startingGold;
    private FormCheckBox                                                              isSpellCasting;
    private ComboBox<Ability>                                                         spellCastingAbility;
    private RadioButtonGroup<SpellCastingType>                                        spellCastingType;
    private LevelSpellsCollectionField<ClassSpellSlots>                               spellSlots;
    private SubSetSelector<Ability, Set<Ability>>                                     savingThrowProficiencies;
    private SubSetSelector<ArmorType.ProficiencyType, Set<ArmorType.ProficiencyType>> armorProficiencies;
    private SubSetSelector<WeaponType, Set<WeaponType>>                               weaponProficiencies;
    private SubSetSelector<ToolType, Set<ToolType>>                                   toolProficiencies;
    private IntegerField                                                              nbChosenSkills;
    private SubSetSelector<Skill, Set<Skill>>                                         baseSkills;
    private LevelBonusCollectionField                                                 levelBonuses;
    private ElementCollectionGrid<ClassLevelFeature>                                  classFeatures;
    private SubSetSelector<ClassSpecialization, Set<ClassSpecialization>>             classSpecs;
    private SubSetSelector<Spell, Set<Spell>>                                         spells;
    private ElementCollectionGrid<ClassEquipment>                                     startingEquipment;

    private Button addAllSimpleWeapons;
    private Button addAllMartialWeapons;
    private Button clearWeapons;

    private CheckBox           martialArts;
    private CheckBox           sorcery;
    private CheckBox           rage;
    private CheckBox           invocation;
    private CheckBox           hunter;
    private CheckBox           sneak;
    private CheckBox           deity;
    private List<Registration> checkBoxListeners;

    private SkillDataService               skillService        = null;
    private LevelDataService               levelService        = null;
    private ClassFeatureDataService        classFeatureService = null;
    private ClassSpecializationDataService classSpecService    = null;
    private WeaponTypeDataService          weaponTypeService   = null;
    private AbilityDataService             abilityService      = null;
    private SpellDataService               spellService        = null;
    private EquipmentDataService           equipmentService    = null;

    private boolean init = false;

    public static class ClassLevelFeatureRow {
        ComboBox<Level>        level      = new ComboBox<>();
        ComboBox<ClassFeature> feature    = new ComboBox<>();
        IntegerField           nbToChoose = new IntegerField();
    }

    public static class ClassEquipmentRow {
        ComboBox<Equipment> equipment = new ComboBox<>();
        IntegerField        quantity  = new IntegerField();
    }

    public ClassForm() {
        super(DSClass.class);
        checkBoxListeners = new ArrayList<>();
        skillService = Services.getSkillService();
        levelService = Services.getLevelService();
        classFeatureService = Services.getClassFeatureService();
        classSpecService = Services.getClassSpecializationService();
        weaponTypeService = Services.getWeaponTypeService();
        abilityService = Services.getAbilityService();
        spellService = Services.getSpellService();
        equipmentService = Services.getEquipmentService();
    }

    @Override
    public String toString() {
        return "Classes";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte").withWidth("80%");
        description = new DSTextArea("Description").withWidth("80%").withRows(12);
        lifePointPerLevel = new IntegerField("Points de vie par niveau");
        startingGold = new IntegerField("Pièces d'or de départ");
        isSpellCasting = new FormCheckBox("Capacité à lancer des sorts");
        spellCastingAbility = new ComboBox<Ability>("Caractéristique de sort");
        List<Ability> allAbilities = abilityService.findAll();
        spellCastingAbility.setItems(allAbilities);
        spellCastingType = new RadioButtonGroup<SpellCastingType>("Sorts innés ou préparés");
        spellCastingType.setItems(EnumSet.allOf(SpellCastingType.class));

        isSpellCasting.addValueChangeListener(this::isSpellCastingChange);
        spellCastingType.addSelectionListener(this::spellCastingTypeChange);

        savingThrowProficiencies = new SubSetSelector<>(Ability.class);
        savingThrowProficiencies.setCaption("Maitrise applicable au jets de sauvegarde");
        savingThrowProficiencies.getGrid().addColumn(Ability::getName).setCaption("Caractéristique").setId("name");
        savingThrowProficiencies.getGrid().setColumnOrder("name");
        savingThrowProficiencies.setItems(allAbilities);
        savingThrowProficiencies.setValue(new HashSet<Ability>()); // nothing selected
        savingThrowProficiencies.setWidth("50%");

        armorProficiencies = new SubSetSelector<>(ArmorType.ProficiencyType.class);
        armorProficiencies.setCaption("Maitrises d'armure");
        armorProficiencies.getGrid().addColumn(ArmorType.ProficiencyType::getName).setCaption("Maitrise").setId("name");
        armorProficiencies.getGrid().setColumnOrder("name");
        armorProficiencies.setItems(Arrays.asList(ArmorType.ProficiencyType.values()));
        armorProficiencies.setValue(new HashSet<ArmorType.ProficiencyType>()); // nothing selected
        armorProficiencies.setWidth("50%");

        addAllSimpleWeapons = new Button("Armes simples", event -> {
            Collection<WeaponType> weaponTypes = weaponTypeService.findAll();
            Set<WeaponType> allSimple = weaponTypes.stream().filter(type -> type.getProficiencyType() == ProficiencyType.SIMPLE)
                    .collect(Collectors.toSet());
            allSimple.addAll(weaponProficiencies.getValue());
            weaponProficiencies.setValue(allSimple);
        });
        addAllMartialWeapons = new Button("Armes de guerre", event -> {
            Collection<WeaponType> weaponTypes = weaponTypeService.findAll();
            Set<WeaponType> allMartial = weaponTypes.stream().filter(type -> type.getProficiencyType() == ProficiencyType.MARTIAL)
                    .collect(Collectors.toSet());
            allMartial.addAll(weaponProficiencies.getValue());
            weaponProficiencies.setValue(allMartial);
        });
        clearWeapons = new Button("Enlever tout", event -> weaponProficiencies.setValue(new HashSet<WeaponType>()));
        HorizontalLayout buttonLayout = new HorizontalLayout(addAllSimpleWeapons, addAllMartialWeapons, clearWeapons);

        weaponProficiencies = new SubSetSelector<>(WeaponType.class);
        weaponProficiencies.setCaption("Maitrises d'arme");
        weaponProficiencies.getGrid().addColumn(WeaponType::getName).setCaption("Maitrise").setId("name");
        weaponProficiencies.getGrid().setColumnOrder("name");
        weaponProficiencies.setItems(weaponTypeService.findAll());
        weaponProficiencies.setValue(new HashSet<WeaponType>()); // nothing selected
        weaponProficiencies.setWidth("50%");

        toolProficiencies = new SubSetSelector<>(ToolType.class);
        toolProficiencies.setCaption("Maitrise d'outil");
        toolProficiencies.getGrid().addColumn(ToolType::getName).setCaption("Outil").setId("name");
        toolProficiencies.getGrid().setColumnOrder("name");
        toolProficiencies.setItems(Arrays.asList(ToolType.values()));
        toolProficiencies.setWidth("50%");
        toolProficiencies.setValue(new HashSet<ToolType>()); // nothing selected

        nbChosenSkills = new IntegerField("Nb de compétences à choisir");
        baseSkills = new SubSetSelector<>(Skill.class);
        baseSkills.setCaption("Compétences de base");
        baseSkills.getGrid().addColumn(Skill::getName).setCaption("Compétence").setId("name");
        baseSkills.getGrid().addColumn(Skill::getKeyAbility).setCaption("Caractéristique clé").setId("keyAbility.name");
        baseSkills.getGrid().setColumnOrder("name", "keyAbility.name");
        baseSkills.setItems(skillService.findAll());
        baseSkills.setWidth("80%");
        baseSkills.setValue(new HashSet<Skill>()); // nothing selected

        List<Level> allLevels = levelService.findAll();
        levelBonuses = new LevelBonusCollectionField();
        levelBonuses.withCaption("Bonus de classe").withEditorInstantiator(() -> {
            ClassLevelBonusRow row = new ClassLevelBonusRow();
            // The ManyToOne field needs its options to be populated
            row.level.setItems(allLevels);
            return row;
        });

        martialArts = new CheckBox("Arts martiaux");
        sorcery = new CheckBox("Sorcellerie");
        rage = new CheckBox("Rage");
        invocation = new CheckBox("Invocation");
        hunter = new CheckBox("Chasseur");
        sneak = new CheckBox("Attaque furtive");
        deity = new CheckBox("Dieu");
        activateCheckboxListeners();

        HorizontalLayout checkboxLayout = new HorizontalLayout(martialArts, sorcery, rage, invocation, hunter, sneak, deity);

        spellSlots = (LevelSpellsCollectionField<ClassSpellSlots>) new LevelSpellsCollectionField<ClassSpellSlots>(ClassSpellSlots.class)
                .withCaption("Nombre de sorts").withEditorInstantiator(() -> {
                    LevelSpellsRow row = new LevelSpellsRow();
                    row.level.setItems(allLevels);
                    return row;
                });

        List<ClassFeature> allClassFeatures = classFeatureService.findAllClassFeaturesWithoutChildren();
        classFeatures = new ElementCollectionGrid<ClassLevelFeature>(ClassLevelFeature.class, ClassLevelFeatureRow.class)
                .withCaption("Dons de classe").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setItems(allLevels);
                    row.feature.setItems(allClassFeatures);
                    row.feature.setPageLength(20);
                    row.feature.setWidth(100, Unit.PERCENTAGE);
                    row.feature.addSelectionListener(selection -> {
                        if (selection.getValue() == null || selection.getValue().getChildren().isEmpty()) {
                            row.nbToChoose.setVisible(false);
                            row.nbToChoose.setValue(1);
                        } else {
                            row.nbToChoose.setVisible(true);
                        }
                    });
                    return row;
                });
        classFeatures.setPropertyHeader("level", "Niveau");
        classFeatures.setPropertyHeader("feature", "Don");
        classFeatures.setPropertyHeader("nbToChoose", "Nb à choisir");
        classFeatures.setWidth("80%");

        spells = new SubSetSelector<>(Spell.class);
        spells.setCaption("Sorts de classe");
        spells.getGrid().addColumn(Spell::getName).setCaption("Sort").setId("name");
        spells.getGrid().addColumn(Spell::getLevel).setCaption("Niveau du sort").setId("level");
        spells.getGrid().addColumn(Spell::getSchool).setCaption("École").setId("school");
        spells.getGrid().setColumnOrder("name", "level", "school");
        spells.setItems(spellService.findAll());
        spells.setWidth("80%");
        spells.setValue(null); // nothing selected

        classSpecs = new SubSetSelector<>(ClassSpecialization.class);
        classSpecs.setCaption("Spécialisations de classe");
        classSpecs.getGrid().addColumn(ClassSpecialization::getName).setCaption("Compétence").setId("name");
        classSpecs.getGrid().setColumnOrder("name");
        classSpecs.setItems(classSpecService.findAll());
        classSpecs.setWidth("80%");
        classSpecs.setValue(new HashSet<>()); // nothing selected

        List<Equipment> allEquipment = equipmentService.findAll();
        startingEquipment = new ElementCollectionGrid<>(ClassEquipment.class, ClassEquipmentRow.class).withCaption("Équipement de base")
                .withEditorInstantiator(() -> {
                    ClassEquipmentRow row = new ClassEquipmentRow();
                    row.equipment.setItems(allEquipment);
                    return row;
                });
        startingEquipment.setPropertyHeader("equipment", "Équipement");
        startingEquipment.setPropertyHeader("quantity", "Quantité");
        startingEquipment.setWidth("80%");

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(lifePointPerLevel);
        layout.addComponent(startingGold);
        layout.addComponent(savingThrowProficiencies);
        layout.addComponent(armorProficiencies);
        layout.addComponents(weaponProficiencies, buttonLayout);
        layout.addComponent(toolProficiencies);
        layout.addComponents(nbChosenSkills, baseSkills);
        layout.addComponents(levelBonuses, checkboxLayout);
        layout.addComponents(isSpellCasting, spellCastingAbility, spellCastingType, spellSlots);
        layout.addComponent(classFeatures);
        layout.addComponent(classSpecs);
        layout.addComponent(spells);
        layout.addComponent(startingEquipment);
        layout.addComponent(getToolbar());

        init = true;

        return layout;
    }

    private void activateCheckboxListeners() {
        checkBoxListeners.add(martialArts.addValueChangeListener(event -> levelBonuses.setMartialArts(event.getValue())));
        checkBoxListeners.add(sorcery.addValueChangeListener(event -> levelBonuses.setSorcery(event.getValue())));
        checkBoxListeners.add(rage.addValueChangeListener(event -> levelBonuses.setRage(event.getValue())));
        checkBoxListeners.add(invocation.addValueChangeListener(event -> levelBonuses.setInvocation(event.getValue())));
        checkBoxListeners.add(hunter.addValueChangeListener(event -> levelBonuses.setHunter(event.getValue())));
        checkBoxListeners.add(sneak.addValueChangeListener(event -> levelBonuses.setSneak(event.getValue())));
        checkBoxListeners.add(deity.addValueChangeListener(event -> levelBonuses.setDeity(event.getValue())));
    }

    @Override
    public void beforeSetEntity(DSClass entity) {
        super.beforeSetEntity(entity);
        if (levelBonuses != null && entity != null && entity.getId() != null) {
            levelBonuses.clearForExisting();
        }

        if (init == true) {
            deactivateCheckboxListeners();
            hunter.setValue(false);
            martialArts.setValue(false);
            sorcery.setValue(false);
            rage.setValue(false);
            invocation.setValue(false);
            sneak.setValue(false);
            deity.setValue(false);
            activateCheckboxListeners();
        }
    }

    private void deactivateCheckboxListeners() {
        checkBoxListeners.stream().forEach(registration -> registration.remove());
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        isSpellCastingChange(null);
        if (getEntity() == null || getEntity().getId() == null) {
            levelBonuses.clearForNew();
        }
        refreshLevelBonusCheckBoxVisibility();
    }

    public void isSpellCastingChange(ValueChangeEvent<Boolean> event) {
        if (isSpellCasting.getValue() == null || isSpellCasting.getValue() == false) {
            spellCastingAbility.setValue(null);
            spellCastingAbility.setVisible(false);
            spellCastingType.setValue(null);
            spellCastingType.setVisible(false);
            spellSlots.clear();
            spellSlots.setVisible(false);
            spellSlots.setKnownSpells(false);
            spells.clear();
            spells.setVisible(false);
        } else {
            spellCastingAbility.setVisible(true);
            spellCastingType.setVisible(true);
            spellSlots.setVisible(true);
            spellSlots.onElementAdded();
            spells.setVisible(true);
        }
    }

    public void spellCastingTypeChange(SingleSelectionEvent<SpellCastingType> event) {
        // hide the known spell column if spells are prepared (nbSpells = level + ability modifier)
        spellSlots.setKnownSpells(event != null && event.getValue() != null && event.getValue() == SpellCastingType.KNOWN);
    }

    private void refreshLevelBonusCheckBoxVisibility() {

        // check the required checkboxes if entity has custom level bonuses
        if (getEntity() != null) {
            for (ClassLevelBonus levelBonus : getEntity().getLevelBonuses()) {
                if (levelBonus.getFavoredEnemy() != null || levelBonus.getNaturalExplorer() != null) {
                    hunter.setValue(true);
                }
                if (levelBonus.getKiPoints() != null || levelBonus.getMartialArtsDamage() != null || levelBonus.getMovementBonus() != null) {
                    martialArts.setValue(true);
                }
                if (levelBonus.getRagePoints() != null || levelBonus.getRageDamageBonus() != null) {
                    rage.setValue(true);
                }
                if (levelBonus.getInvocationsKnown() != null) {
                    invocation.setValue(true);
                }
                if (levelBonus.getSorceryPoints() != null) {
                    sorcery.setValue(true);
                }
                if (levelBonus.getSneakAttackDamage() != null) {
                    sneak.setValue(true);
                }
                if (levelBonus.getDeity()) {
                    deity.setValue(true);
                }
            }
        }

        // show/hide fields according to checkbox values
        levelBonuses.setHunter(hunter.getValue());
        levelBonuses.setMartialArts(martialArts.getValue());
        levelBonuses.setSorcery(sorcery.getValue());
        levelBonuses.setInvocation(invocation.getValue());
        levelBonuses.setSneak(sneak.getValue());
        levelBonuses.setRage(rage.getValue());
        levelBonuses.setDeity(deity.getValue());
    }

}
