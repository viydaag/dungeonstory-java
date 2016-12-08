package com.dungeonstory.form;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MCheckBox;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.fields.config.ComboBoxConfig;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.ClassEquipment;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.field.LevelBonusCollectionField;
import com.dungeonstory.util.field.LevelBonusCollectionField.ClassLevelBonusRow;
import com.dungeonstory.util.field.LevelSpellsCollectionField;
import com.dungeonstory.util.field.LevelSpellsCollectionField.LevelSpellsRow;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassForm extends DSAbstractForm<DSClass> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField                                   name;
    private TextField                                   shortDescription;
    private TextArea                                    description;
    private IntegerField                                lifePointPerLevel;
    private IntegerField                                startingGold;
    private FormCheckBox                                isSpellCasting;
    private TypedSelect<Ability>                        spellCastingAbility;
    private EnumSelect<SpellCastingType>                spellCastingType;
    private LevelSpellsCollectionField<ClassSpellSlots> spellSlots;
    private DSSubSetSelector<Ability>                   savingThrowProficiencies;
    private DSSubSetSelector<ArmorType.ProficiencyType> armorProficiencies;
    private DSSubSetSelector<WeaponType>                weaponProficiencies;
    private DSSubSetSelector<ToolType>                  toolProficiencies;
    private IntegerField                                nbChosenSkills;
    private DSSubSetSelector<Skill>                     baseSkills;
    private LevelBonusCollectionField                   levelBonuses;
    private ElementCollectionTable<ClassLevelFeature>   classFeatures;
    private DSSubSetSelector<Spell>                     spells;
    private ElementCollectionTable<ClassEquipment>      startingEquipment;

    private Button addAllSimpleWeapons;
    private Button addAllMartialWeapons;
    private Button clearWeapons;

    private CheckBox martialArts;
    private CheckBox sorcery;
    private CheckBox rage;
    private CheckBox invocation;
    private CheckBox hunter;
    private CheckBox sneak;
    private CheckBox deity;

    private DataService<Skill, Long>      skillService      = null;
    private DataService<Level, Long>      levelService      = null;
    private FeatDataService               featService       = null;
    private DataService<WeaponType, Long> weaponTypeService = null;
    private DataService<Ability, Long>    abilityService    = null;
    private DataService<Spell, Long>      spellService      = null;
    private EquipmentDataService          equipmentService  = null;

    private boolean init = false;

    public static class ClassLevelFeatureRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Feat>  feat  = new TypedSelect<Feat>().asComboBoxType(new ComboBoxConfig().withPageLength(20))
                .withFullWidth();
    }

    public static class ClassEquipmentRow {
        TypedSelect<Equipment> equipment = new TypedSelect<Equipment>(Equipment.class);
        IntegerField           quantity  = new IntegerField();
    }

    public ClassForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            skillService = MockSkillService.getInstance();
            levelService = MockLevelService.getInstance();
            featService = MockFeatService.getInstance();
            weaponTypeService = MockWeaponTypeService.getInstance();
            abilityService = MockAbilityService.getInstance();
            spellService = MockSpellService.getInstance();
            equipmentService = MockEquipmentService.getInstance();
        } else {
            skillService = SkillService.getInstance();
            levelService = LevelService.getInstance();
            featService = FeatService.getInstance();
            weaponTypeService = WeaponTypeService.getInstance();
            abilityService = AbilityService.getInstance();
            spellService = SpellService.getInstance();
            equipmentService = EquipmentService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Classes";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte").withWidth("80%");
        description = new MTextArea("Description").withWidth("80%").withRows(12);
        lifePointPerLevel = new IntegerField("Points de vie par niveau");
        startingGold = new IntegerField("Pièces d'or de départ");
        isSpellCasting = new FormCheckBox("Capacité à lancer des sorts");
        spellCastingAbility = new TypedSelect<Ability>("Caractéristique de sort").asComboBoxType();
        spellCastingAbility.setOptions(abilityService.findAll());
        spellCastingType = new EnumSelect<SpellCastingType>("Sorts innés ou préparés?")
                .withSelectType(OptionGroup.class);

        isSpellCasting.addValueChangeListener(this::isSpellCastingChange);
        spellCastingType.addMValueChangeListener(this::spellCastingTypeChange);

        savingThrowProficiencies = new DSSubSetSelector<Ability>(Ability.class);
        savingThrowProficiencies.setCaption("Maitrise applicable au jets de sauvegarde");
        savingThrowProficiencies.setVisibleProperties("name");
        savingThrowProficiencies.setColumnHeader("name", "Caractéristique");
        savingThrowProficiencies.setOptions(abilityService.findAll());
        savingThrowProficiencies.setValue(new HashSet<Ability>()); //nothing selected
        savingThrowProficiencies.setWidth("50%");

        armorProficiencies = new DSSubSetSelector<ArmorType.ProficiencyType>(ArmorType.ProficiencyType.class);
        armorProficiencies.setCaption("Maitrises d'armure");
        armorProficiencies.setVisibleProperties("name");
        armorProficiencies.setColumnHeader("name", "Maitrise");
        armorProficiencies.setOptions(Arrays.asList(ArmorType.ProficiencyType.values()));
        armorProficiencies.setValue(new HashSet<ArmorType.ProficiencyType>()); //nothing selected
        armorProficiencies.setWidth("50%");

        addAllSimpleWeapons = new Button("Armes simples", event -> {
            Collection<WeaponType> weaponTypes = weaponTypeService.findAll();
            Set<WeaponType> allSimple = weaponTypes.stream()
                    .filter(type -> type.getProficiencyType() == ProficiencyType.SIMPLE).collect(Collectors.toSet());
            allSimple.addAll(weaponProficiencies.getValue());
            weaponProficiencies.setValue(allSimple);
        });
        addAllMartialWeapons = new Button("Armes de guerre", event -> {
            Collection<WeaponType> weaponTypes = weaponTypeService.findAll();
            Set<WeaponType> allMartial = weaponTypes.stream()
                    .filter(type -> type.getProficiencyType() == ProficiencyType.MARTIAL).collect(Collectors.toSet());
            allMartial.addAll(weaponProficiencies.getValue());
            weaponProficiencies.setValue(allMartial);
        });
        clearWeapons = new Button("Enlever tout", event -> weaponProficiencies.setValue(new HashSet<WeaponType>()));
        HorizontalSpacedLayout buttonLayout = new HorizontalSpacedLayout(addAllSimpleWeapons, addAllMartialWeapons,
                clearWeapons);

        weaponProficiencies = new DSSubSetSelector<WeaponType>(WeaponType.class);
        weaponProficiencies.setCaption("Maitrises d'arme");
        weaponProficiencies.setVisibleProperties("name");
        weaponProficiencies.setColumnHeader("name", "Maitrise");
        weaponProficiencies.setOptions(weaponTypeService.findAll());
        weaponProficiencies.setValue(new HashSet<WeaponType>()); //nothing selected
        weaponProficiencies.setWidth("50%");

        toolProficiencies = new DSSubSetSelector<ToolType>(ToolType.class);
        toolProficiencies.setCaption("Maitrise d'outil");
        toolProficiencies.setVisibleProperties("name");
        toolProficiencies.setColumnHeader("name", "Outil");
        toolProficiencies.setOptions(Arrays.asList(ToolType.values()));
        toolProficiencies.setWidth("80%");
        toolProficiencies.setValue(new HashSet<ToolType>()); //nothing selected

        nbChosenSkills = new IntegerField("Nb de compétences à choisir");
        baseSkills = new DSSubSetSelector<Skill>(Skill.class);
        baseSkills.setCaption("Compétences de base");
        baseSkills.setVisibleProperties("name", "keyAbility.name");
        baseSkills.setColumnHeader("name", "Compétence");
        baseSkills.setColumnHeader("keyAbility.name", "Carctéristique clé");
        baseSkills.setOptions(skillService.findAll());
        baseSkills.setWidth("80%");
        baseSkills.setValue(new HashSet<Skill>()); //nothing selected

        levelBonuses = new LevelBonusCollectionField();
        levelBonuses.withCaption("Bonus de classe").withEditorInstantiator(() -> {
            ClassLevelBonusRow row = new ClassLevelBonusRow();
            // The ManyToOne field needs its options to be populated
            row.level.setOptions(levelService.findAll());
            return row;
        });

        martialArts = new MCheckBox("Arts martiaux");
        sorcery = new MCheckBox("Sorcellerie");
        rage = new MCheckBox("Rage");
        invocation = new MCheckBox("Invocation");
        hunter = new MCheckBox("Chasseur");
        sneak = new MCheckBox("Attaque furtive");
        deity = new MCheckBox("Dieu");
        activateCheckboxListeners();

        HorizontalSpacedLayout checkboxLayout = new HorizontalSpacedLayout(martialArts, sorcery, rage, invocation,
                hunter, sneak, deity);

        spellSlots = (LevelSpellsCollectionField<ClassSpellSlots>) new LevelSpellsCollectionField<ClassSpellSlots>(
                ClassSpellSlots.class).withCaption("Nombre de sorts").withEditorInstantiator(() -> {
                    LevelSpellsRow row = new LevelSpellsRow();
                    row.level.setOptions(levelService.findAll());
                    return row;
                });

        classFeatures = new ElementCollectionTable<ClassLevelFeature>(ClassLevelFeature.class,
                ClassLevelFeatureRow.class).withCaption("Dons de classe").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setOptions(levelService.findAll());
                    row.feat.setOptions(featService.findAllClassFeatures());
                    return row;
                });
        classFeatures.setPropertyHeader("level", "Niveau");
        classFeatures.setPropertyHeader("feat", "Don");
        classFeatures.setWidth("80%");

        spells = new DSSubSetSelector<Spell>(Spell.class);
        spells.setCaption("Sorts de classe");
        spells.setVisibleProperties("level", "name", "school");
        spells.setColumnHeader("name", "Sort");
        spells.setColumnHeader("level", "Niveau du sort");
        spells.setColumnHeader("school", "École");
        spells.setOptions(spellService.findAll());
        spells.setWidth("80%");
        spells.setValue(null); //nothing selected

        startingEquipment = new ElementCollectionTable<ClassEquipment>(ClassEquipment.class, ClassEquipmentRow.class)
                .withCaption("Équipement de base").withEditorInstantiator(() -> {
                    ClassEquipmentRow row = new ClassEquipmentRow();
                    row.equipment.setOptions(equipmentService.findAll());
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
        layout.addComponent(spells);
        layout.addComponent(startingEquipment);
        layout.addComponent(getToolbar());

        init = true;

        return layout;
    }

    private void activateCheckboxListeners() {
        martialArts
                .addValueChangeListener(event -> levelBonuses.setMartialArts((boolean) event.getProperty().getValue()));
        sorcery.addValueChangeListener(event -> levelBonuses.setSorcery((boolean) event.getProperty().getValue()));
        rage.addValueChangeListener(event -> levelBonuses.setRage((boolean) event.getProperty().getValue()));
        invocation
                .addValueChangeListener(event -> levelBonuses.setInvocation((boolean) event.getProperty().getValue()));
        hunter.addValueChangeListener(event -> levelBonuses.setHunter((boolean) event.getProperty().getValue()));
        sneak.addValueChangeListener(event -> levelBonuses.setSneak((boolean) event.getProperty().getValue()));
        deity.addValueChangeListener(event -> levelBonuses.setDeity((boolean) event.getProperty().getValue()));
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
        hunter.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> hunter.removeListener(Field.ValueChangeEvent.class, listener));
        rage.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> rage.removeListener(Field.ValueChangeEvent.class, listener));
        invocation.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> invocation.removeListener(Field.ValueChangeEvent.class, listener));
        martialArts.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> martialArts.removeListener(Field.ValueChangeEvent.class, listener));
        sneak.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> sneak.removeListener(Field.ValueChangeEvent.class, listener));
        sorcery.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> sorcery.removeListener(Field.ValueChangeEvent.class, listener));
        deity.getListeners(Field.ValueChangeEvent.class)
                .forEach(listener -> deity.removeListener(Field.ValueChangeEvent.class, listener));
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        isSpellCastingChange(null);
        if (getEntity() == null || getEntity().getId() == null) {
            levelBonuses.clearForNew();
        }
        if (getEntity() != null) {
            classFeatures.getTable().setPageLength(classFeatures.getTable().size());
            classFeatures.setHeight("700px");
        }
        refreshLevelBonusCheckBoxVisibility();
    }

    public void isSpellCastingChange(ValueChangeEvent event) {
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

    public void spellCastingTypeChange(MValueChangeEvent<SpellCastingType> event) {
        //hide the known spell column if spells are prepared (nbSpells = level + ability modifier)
        spellSlots.setKnownSpells(
                event != null && event.getValue() != null && event.getValue() == SpellCastingType.KNOWN);
    }

    private void refreshLevelBonusCheckBoxVisibility() {

        // check the required checkboxes if entity has custom level bonuses
        if (getEntity() != null) {
            for (ClassLevelBonus levelBonus : getEntity().getLevelBonuses()) {
                if (levelBonus.getFavoredEnemy() != null || levelBonus.getNaturalExplorer() != null) {
                    hunter.setValue(true);
                }
                if (levelBonus.getKiPoints() != null || levelBonus.getMartialArtsDamage() != null
                        || levelBonus.getMovementBonus() != null) {
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
                if (levelBonus.getDeity() != null) {
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
