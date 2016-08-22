package com.dungeonstory.form;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.vaadin.viritin.fields.ElementCollectionField;
import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelBonusFeat;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassForm extends DSAbstractForm<DSClass> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField                                   name;
    private TextField                                   shortDescription;
    private TextArea                                    description;
    private DSSubSetSelector<Ability>                   savingThrowProficiencies;
    private DSSubSetSelector<ArmorType.ProficiencyType> armorProficiencies;
    private DSSubSetSelector<WeaponType>                weaponProficiencies;
    private DSSubSetSelector<Skill>                     baseSkills;
    private ElementCollectionField<ClassLevelBonus>     levelBonuses;
    private ElementCollectionTable<ClassLevelBonusFeat> featBonuses;

    private DataService<Skill, Long>      skillService      = null;
    private DataService<Level, Long>      levelService      = null;
    private DataService<Feat, Long>       featService       = null;
    private DataService<WeaponType, Long> weaponTypeService = null;
    private DataService<Ability, Long>    abilityService    = null;

    public static class ClassLevelBonusRow {
        TypedSelect<Level> level                      = new TypedSelect<Level>();
        CheckBox           hasAbilityScoreImprovement = new CheckBox();
        IntegerField       spellPerDay0               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay1               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay2               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay3               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay4               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay5               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay6               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay7               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay8               = new IntegerField().withWidth("50px");
        IntegerField       spellPerDay9               = new IntegerField().withWidth("50px");
    }

    public static class ClassLevelBonusFeatRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Feat>  feat  = new TypedSelect<Feat>();
    }

    public ClassForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            skillService = MockSkillService.getInstance();
            levelService = MockLevelService.getInstance();
            featService = MockFeatService.getInstance();
            weaponTypeService = MockWeaponTypeService.getInstance();
            abilityService = MockAbilityService.getInstance();
        } else {
            skillService = SkillService.getInstance();
            levelService = LevelService.getInstance();
            featService = FeatService.getInstance();
            weaponTypeService = WeaponTypeService.getInstance();
            abilityService = AbilityService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Classes";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte").withFullWidth();
        description = new MTextArea("Description").withFullWidth();

        savingThrowProficiencies = new DSSubSetSelector<Ability>(Ability.class);
        savingThrowProficiencies.setCaption("Compétence applicable au jet de sauvegarde");
        savingThrowProficiencies.setVisibleProperties("name");
        savingThrowProficiencies.setColumnHeader("name", "Capacité");
        savingThrowProficiencies.setOptions((List<Ability>) abilityService.findAll());
        savingThrowProficiencies.setValue(new HashSet<Ability>()); //nothing selected
        savingThrowProficiencies.setWidth("50%");

        armorProficiencies = new DSSubSetSelector<ArmorType.ProficiencyType>(ArmorType.ProficiencyType.class);
        armorProficiencies.setCaption("Compétences d'armure");
        armorProficiencies.setVisibleProperties("name");
        armorProficiencies.setColumnHeader("name", "Compétences d'armure");
        armorProficiencies.setOptions(Arrays.asList(ArmorType.ProficiencyType.values()));
        armorProficiencies.setValue(new HashSet<ArmorType.ProficiencyType>()); //nothing selected
        armorProficiencies.setWidth("50%");

        weaponProficiencies = new DSSubSetSelector<WeaponType>(WeaponType.class);
        weaponProficiencies.setCaption("Compétences d'arme");
        weaponProficiencies.setVisibleProperties("name");
        weaponProficiencies.setColumnHeader("name", "Compétences d'arme");
        weaponProficiencies.setOptions((List<WeaponType>) weaponTypeService.findAll());
        weaponProficiencies.setValue(new HashSet<WeaponType>()); //nothing selected
        weaponProficiencies.setWidth("50%");

        baseSkills = new DSSubSetSelector<Skill>(Skill.class);
        baseSkills.setCaption("Talents de base");
        baseSkills.setVisibleProperties("name", "keyAbility.name");
        baseSkills.setColumnHeader("name", "Talent");
        baseSkills.setColumnHeader("keyAbility.name", "Attribut clé");
        baseSkills.setOptions((List<Skill>) skillService.findAll());
        baseSkills.setWidth("80%");
        //		baseSkills.setNewItemsAllowed(false);
        baseSkills.setValue(new HashSet<Skill>()); //nothing selected

        levelBonuses = new ElementCollectionField<ClassLevelBonus>(ClassLevelBonus.class, ClassLevelBonusRow.class)
                .withCaption("Bonus de classe").withEditorInstantiator(() -> {
                    ClassLevelBonusRow row = new ClassLevelBonusRow();
                    // The ManyToOne field needs its options to be populated
                    row.level.setOptions(levelService.findAll());
                    return row;
                });
        levelBonuses.setPropertyHeader("level", "Niveau");
        levelBonuses.setPropertyHeader("hasAbilityScoreImprovement", "+ attribut/don");
        levelBonuses.setPropertyHeader("spellPerDay0", "Sorts 0");
        levelBonuses.setPropertyHeader("spellPerDay1", "Sorts 1");
        levelBonuses.setPropertyHeader("spellPerDay2", "Sorts 2");
        levelBonuses.setPropertyHeader("spellPerDay3", "Sorts 3");
        levelBonuses.setPropertyHeader("spellPerDay4", "Sorts 4");
        levelBonuses.setPropertyHeader("spellPerDay5", "Sorts 5");
        levelBonuses.setPropertyHeader("spellPerDay6", "Sorts 6");
        levelBonuses.setPropertyHeader("spellPerDay7", "Sorts 7");
        levelBonuses.setPropertyHeader("spellPerDay8", "Sorts 8");
        levelBonuses.setPropertyHeader("spellPerDay9", "Sorts 9");

        featBonuses = new ElementCollectionTable<ClassLevelBonusFeat>(ClassLevelBonusFeat.class,
                ClassLevelBonusFeatRow.class).withCaption("Dons de classe").withEditorInstantiator(() -> {
                    ClassLevelBonusFeatRow row = new ClassLevelBonusFeatRow();
                    row.level.setOptions(levelService.findAll());
                    row.feat.setOptions(featService.findAll());
                    return row;
                });
        featBonuses.setPropertyHeader("level", "Niveau");
        featBonuses.setPropertyHeader("feat", "Don");
        featBonuses.setWidth("80%");

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(savingThrowProficiencies);
        layout.addComponent(armorProficiencies);
        layout.addComponent(weaponProficiencies);
        layout.addComponent(baseSkills);
        layout.addComponent(levelBonuses);
        layout.addComponent(featBonuses);
        layout.addComponent(getToolbar());

        return layout;
    }
}
