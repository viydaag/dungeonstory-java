package com.dungeonstory.ui.view.admin.form;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.ChallengeRating;
import com.dungeonstory.backend.data.Condition;
import com.dungeonstory.backend.data.CreatureSize;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.MonsterAction;
import com.dungeonstory.backend.data.MonsterSense;
import com.dungeonstory.backend.data.MonsterSkill;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.ElementCollectionField;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class MonsterForm extends DSAbstractForm<Monster> {

    private static final long serialVersionUID = 106965436419932102L;

    private TextField                  name;
    private TextArea                   description;
    private EnumComboBox<CreatureSize> size;
    private ComboBox<CreatureType>     creatureType;
    private TextField                  tag;
    private ComboBox<Alignment>        alignment;
    private IntegerField               armorClass;
    private IntegerField               hitPoints;
    private IntegerField               groundSpeed;
    private IntegerField               flySpeed;
    private IntegerField               burrowSpeed;
    private IntegerField               swimSpeed;
    private IntegerField               climbSpeed;

    private IntegerField strength;
    private IntegerField dexterity;
    private IntegerField constitution;
    private IntegerField intelligence;
    private IntegerField wisdom;
    private IntegerField charisma;
    private IntegerField passivePerception;

    private EnumComboBox<ChallengeRating>              challengeRating;
    private SubSetSelector<DamageType, Set<DamageType>> damageVulnerabilities;
    private SubSetSelector<DamageType, Set<DamageType>> damageResistances;
    private SubSetSelector<DamageType, Set<DamageType>> damageImmunities;
    private SubSetSelector<Condition, Set<Condition>>   conditionImmunities;

    private SubSetSelector<Language, Set<Language>> languages;
    private SubSetSelector<Ability, Set<Ability>>   savingThrowProficiencies;
    private ElementCollectionField<MonsterSkill>    skills;
    private ElementCollectionField<MonsterSense>    senses;
    private ElementCollectionGrid<MonsterAction>    attacks;

    public static class MonsterSkillRow {
        ComboBox<Skill> skill = new ComboBox<>();
        IntegerField    bonus = new IntegerField();
    }

    public static class MonsterSenseRow {
        EnumComboBox<MonsterSense.Sense> sense          = new EnumComboBox<>(MonsterSense.Sense.class);
        IntegerField                     distanceInFeet = new IntegerField();
    }

    public static class MonsterAttackRow {
        TextField               name                   = new TextField();
        EnumComboBox<UsageType> usageType              = new EnumComboBox<>(UsageType.class);
        TextField               damage                 = new TextField();
        ComboBox<DamageType>    damageType             = new ComboBox<>();
        IntegerField            bonusToHit             = new IntegerField();
        EnumComboBox<Condition> condition              = new EnumComboBox<>(Condition.class);
        ComboBox<Ability>       savingThrowToCondition = new ComboBox<>();
        TextField               extraDamage            = new TextField();
        ComboBox<DamageType>    extraDamageType        = new ComboBox<>();
        CheckBox                multiAttack            = new CheckBox();
        IntegerField            multiAttackRank        = new IntegerField();
        IntegerField            nbPerRound             = new IntegerField();
    }

    public MonsterForm() {
        super(Monster.class);
    }

    @Override
    public String toString() {
        return "Monstres";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();
        Messages messages = Messages.getInstance();

        name = new TextField("Nom");
        description = new DSTextArea("Description").withFullWidth();

        size = new EnumComboBox<>(CreatureSize.class, "Ordre de grandeur");
        creatureType = new ComboBox<>("Type", Services.getCreatureTypeService().findAll());
        tag = new TextField("tag");
        alignment = new ComboBox<>("Alignement", Services.getAlignmentService().findAll());
        armorClass = new IntegerField("Classe d'armure");
        hitPoints = new IntegerField("Points de vie");
        groundSpeed = new IntegerField("Vitesse au sol");
        flySpeed = new IntegerField("Vitesse de vol");
        burrowSpeed = new IntegerField("Vitesse sous le sol");
        swimSpeed = new IntegerField("Vitesse de nage");
        climbSpeed = new IntegerField("Vitesse de grimpe");

        strength = new IntegerField(messages.getMessage("ability.str.caption"));
        dexterity = new IntegerField(messages.getMessage("ability.dex.caption"));
        constitution = new IntegerField(messages.getMessage("ability.con.caption"));
        intelligence = new IntegerField(messages.getMessage("ability.int.caption"));
        wisdom = new IntegerField(messages.getMessage("ability.wis.caption"));
        charisma = new IntegerField(messages.getMessage("ability.cha.caption"));
        passivePerception = new IntegerField("Perception passive");

        challengeRating = new EnumComboBox<>(ChallengeRating.class, "Degré de difficulté");

        damageVulnerabilities = new SubSetSelector<>(DamageType.class);
        damageVulnerabilities.setCaption("Vulnérabilités aux types de dégâts");
        damageVulnerabilities.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageVulnerabilities.getGrid().setColumnOrder("name");
        damageVulnerabilities.setItems(Services.getDamageTypeService().findAll());
        damageVulnerabilities.setValue(new HashSet<DamageType>()); // nothing
                                                                   // selected
        damageVulnerabilities.setWidth("50%");

        damageResistances = new SubSetSelector<>(DamageType.class);
        damageResistances.setCaption("Résistances aux types de dégâts");
        damageResistances.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageResistances.getGrid().setColumnOrder("name");
        damageResistances.setItems(Services.getDamageTypeService().findAll());
        damageResistances.setValue(new HashSet<DamageType>()); // nothing
                                                               // selected
        damageResistances.setWidth("50%");

        damageImmunities = new SubSetSelector<>(DamageType.class);
        damageImmunities.setCaption("Immunités aux types de dégâts");
        damageImmunities.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageImmunities.getGrid().setColumnOrder("name");
        damageImmunities.setItems(Services.getDamageTypeService().findAll());
        damageImmunities.setValue(new HashSet<DamageType>()); // nothing
                                                              // selected
        damageImmunities.setWidth("50%");

        conditionImmunities = new SubSetSelector<>(Condition.class);
        conditionImmunities.setCaption("Immunités aux conditions");
        conditionImmunities.getGrid().addColumn(Condition::getName).setCaption("Condition").setId("name");
        conditionImmunities.getGrid().setColumnOrder("name");
        conditionImmunities.setItems(EnumSet.allOf(Condition.class));
        conditionImmunities.setValue(new HashSet<Condition>()); // nothing
                                                                // selected
        conditionImmunities.setWidth("50%");

        languages = new SubSetSelector<>(Language.class);
        languages.setCaption("Langage");
        languages.getGrid().addColumn(Language::getName).setCaption("Langage").setId("name");
        languages.getGrid().setColumnOrder("name");
        languages.setItems(Services.getLanguageService().findAll());
        languages.setValue(new HashSet<Language>()); // nothing selected
        languages.setWidth("50%");

        savingThrowProficiencies = new SubSetSelector<>(Ability.class);
        savingThrowProficiencies.setCaption("Maitrises de jet de sauvegarde");
        savingThrowProficiencies.getGrid().addColumn(Ability::getName).setCaption("Charactéristique").setId("name");
        savingThrowProficiencies.getGrid().setColumnOrder("name");
        savingThrowProficiencies.setItems(Services.getAbilityService().findAll());
        savingThrowProficiencies.setValue(new HashSet<Ability>()); // nothing
                                                                   // selected
        savingThrowProficiencies.setWidth("50%");

        List<Skill> allSkills = Services.getSkillService().findAll();
        skills = new ElementCollectionField<>(MonsterSkill.class, MonsterSkillRow.class)
                .withCaption("Maitrises de compétence").withEditorInstantiator(() -> {
                    MonsterSkillRow row = new MonsterSkillRow();
                    row.skill.setItems(allSkills);
                    return row;
                });
        skills.setPropertyHeader("skill", "Compétence");
        skills.setPropertyHeader("bonus", "Bonus de maitrise");
        skills.setWidth("80%");

        senses = new ElementCollectionField<>(MonsterSense.class, MonsterSenseRow.class).withCaption("Sens")
                .withEditorInstantiator(() -> {
                    MonsterSenseRow row = new MonsterSenseRow();
                    return row;
                });
        senses.setPropertyHeader("sense", "Sens");
        senses.setPropertyHeader("distanceInFeet", "Distance en pieds");
        senses.setWidth("80%");

        List<Ability> allAbilities = Services.getAbilityService().findAll();
        List<DamageType> allDamageTypes = Services.getDamageTypeService().findAll();
        attacks = new ElementCollectionGrid<>(MonsterAction.class, MonsterAttackRow.class).withCaption("Actions")
                .withEditorInstantiator(() -> {
                    MonsterAttackRow row = new MonsterAttackRow();
                    row.damageType.setItems(allDamageTypes);
                    row.extraDamageType.setItems(allDamageTypes);
                    row.savingThrowToCondition.setItems(allAbilities);
                    return row;
                });
        // attacks.setPropertyHeader("sense", "Sens");
        // attacks.setPropertyHeader("distanceInFeet", "Distance en pieds");
        attacks.setWidth("100%");

        layout.addComponents(name, description);
        layout.addComponents(size, creatureType, tag, alignment, armorClass, hitPoints, groundSpeed, flySpeed,
                burrowSpeed, swimSpeed, climbSpeed);
        layout.addComponents(strength, dexterity, constitution, intelligence, wisdom, charisma, passivePerception);
        layout.addComponents(challengeRating, damageVulnerabilities, damageResistances, damageImmunities,
                conditionImmunities);
        layout.addComponents(languages, savingThrowProficiencies, skills, senses, attacks);
        layout.addComponent(getToolbar());

        return layout;
    }
}
