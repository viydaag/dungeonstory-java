package com.dungeonstory.ui.view.admin.form;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.dungeonstory.backend.data.enums.Alignment;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.MonsterAction;
import com.dungeonstory.backend.data.MonsterSense;
import com.dungeonstory.backend.data.MonsterSkill;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.enums.ChallengeRating;
import com.dungeonstory.backend.data.enums.Condition;
import com.dungeonstory.backend.data.enums.CreatureSize;
import com.dungeonstory.backend.data.enums.DamageType;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.ElementCollectionField;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.layout.MultiColumnFormLayout;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MonsterForm extends DSAbstractForm<Monster> {

    private static final long serialVersionUID = 106965436419932102L;

    private TextField                  name;
    private TextArea                   description;
    private EnumComboBox<CreatureSize> size;
    private ComboBox<CreatureType>     creatureType;
    private TextField                  tag;
    private EnumComboBox<Alignment>    alignment;
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

    private EnumComboBox<ChallengeRating>               challengeRating;
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
        EnumComboBox<Skill> skill = new EnumComboBox<>(Skill.class);
        IntegerField        bonus = new DSIntegerField();
    }

    public static class MonsterSenseRow {
        EnumComboBox<MonsterSense.Sense> sense          = new EnumComboBox<>(MonsterSense.Sense.class);
        IntegerField                     distanceInFeet = new DSIntegerField();
    }

    public static class MonsterAttackRow {
        TextField                name                   = new TextField();
        EnumComboBox<UsageType>  usageType              = new EnumComboBox<>(UsageType.class);
        TextField                damage                 = new TextField();
        EnumComboBox<DamageType> damageType             = new EnumComboBox<>(DamageType.class);
        IntegerField             bonusToHit             = new DSIntegerField();
        EnumComboBox<Condition>  condition              = new EnumComboBox<>(Condition.class);
        EnumComboBox<Ability>    savingThrowToCondition = new EnumComboBox<>(Ability.class);
        TextField                extraDamage            = new TextField();
        EnumComboBox<DamageType> extraDamageType        = new EnumComboBox<>(DamageType.class);
        CheckBox                 multiAttack            = new CheckBox();
        IntegerField             multiAttackRank        = new DSIntegerField();
        IntegerField             nbPerRound             = new DSIntegerField();
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
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(new MarginInfo(true, false));

        Messages messages = Messages.getInstance();

        name = new TextField("Nom");
        description = new FTextArea("Description").withFullWidth().withRows(10);

        size = new EnumComboBox<>(CreatureSize.class, "Ordre de grandeur");
        creatureType = new ComboBox<>("Type", Services.getCreatureTypeService().findAll());
        tag = new TextField("tag");
        alignment = new EnumComboBox<>(Alignment.class, "Alignement");
        armorClass = new DSIntegerField("Classe d'armure");
        hitPoints = new DSIntegerField("Points de vie");
        groundSpeed = new DSIntegerField("Vitesse au sol");
        flySpeed = new DSIntegerField("Vitesse de vol");
        burrowSpeed = new DSIntegerField("Vitesse sous le sol");
        swimSpeed = new DSIntegerField("Vitesse de nage");
        climbSpeed = new DSIntegerField("Vitesse de grimpe");

        strength = new DSIntegerField(Ability.STRENGTH.getName());
        dexterity = new DSIntegerField(Ability.DEXTERITY.getName());
        constitution = new DSIntegerField(Ability.CONSTITUTION.getName());
        intelligence = new DSIntegerField(Ability.INTELLIGENCE.getName());
        wisdom = new DSIntegerField(Ability.WISDOM.getName());
        charisma = new DSIntegerField(Ability.CHARISMA.getName());
        passivePerception = new DSIntegerField("Perception passive");

        challengeRating = new EnumComboBox<>(ChallengeRating.class, "Degré de difficulté");

        damageVulnerabilities = new SubSetSelector<>(DamageType.class);
        damageVulnerabilities.setCaption("Vulnérabilités aux types de dégâts");
        damageVulnerabilities.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageVulnerabilities.getGrid().setColumnOrder("name");
        damageVulnerabilities.setItems(EnumSet.allOf(DamageType.class));
        damageVulnerabilities.setValue(EnumSet.noneOf(DamageType.class)); // nothing selected
        damageVulnerabilities.setWidth("50%");

        damageResistances = new SubSetSelector<>(DamageType.class);
        damageResistances.setCaption("Résistances aux types de dégâts");
        damageResistances.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageResistances.getGrid().setColumnOrder("name");
        damageResistances.setItems(EnumSet.allOf(DamageType.class));
        damageResistances.setValue(EnumSet.noneOf(DamageType.class)); // nothing selected
        damageResistances.setWidth("50%");

        damageImmunities = new SubSetSelector<>(DamageType.class);
        damageImmunities.setCaption("Immunités aux types de dégâts");
        damageImmunities.getGrid().addColumn(DamageType::getName).setCaption("Type de dégât").setId("name");
        damageImmunities.getGrid().setColumnOrder("name");
        damageImmunities.setItems(EnumSet.allOf(DamageType.class));
        damageImmunities.setValue(EnumSet.noneOf(DamageType.class)); // nothing selected
        damageImmunities.setWidth("50%");

        conditionImmunities = new SubSetSelector<>(Condition.class);
        conditionImmunities.setCaption("Immunités aux conditions");
        conditionImmunities.getGrid().addColumn(Condition::getName).setCaption("Condition").setId("name");
        conditionImmunities.getGrid().setColumnOrder("name");
        conditionImmunities.setItems(EnumSet.allOf(Condition.class));
        conditionImmunities.setValue(new HashSet<Condition>()); // nothing selected
        conditionImmunities.setWidth("50%");

        languages = new SubSetSelector<>(Language.class);
        languages.setCaption("Langage");
        languages.getGrid().addColumn(Language::getName).setCaption("Langage").setId("name");
        languages.getGrid().setColumnOrder("name");
        languages.setItems(EnumSet.allOf(Language.class));
        languages.setValue(EnumSet.noneOf(Language.class)); // nothing selected
        languages.setWidth("50%");

        savingThrowProficiencies = new SubSetSelector<>(Ability.class);
        savingThrowProficiencies.setCaption("Maitrises de jet de sauvegarde");
        savingThrowProficiencies.getGrid().addColumn(Ability::getName).setCaption("Charactéristique").setId("name");
        savingThrowProficiencies.getGrid().setColumnOrder("name");
        savingThrowProficiencies.setItems(EnumSet.allOf(Ability.class));
        savingThrowProficiencies.setValue(EnumSet.noneOf(Ability.class)); // nothing selected
        savingThrowProficiencies.setWidth("50%");

        Set<Skill> allSkills = EnumSet.allOf(Skill.class);
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

        attacks = new ElementCollectionGrid<>(MonsterAction.class, MonsterAttackRow.class).withCaption("Actions")
                .withEditorInstantiator(() -> {
                    MonsterAttackRow row = new MonsterAttackRow();
//                    row.damageType.setItems(EnumSet.allOf(DamageType.class));
//                    row.extraDamageType.setItems(EnumSet.allOf(DamageType.class));
//                    row.savingThrowToCondition.setItems(EnumSet.allOf(Ability.class));
                    return row;
                });
        // attacks.setPropertyHeader("sense", "Sens");
        // attacks.setPropertyHeader("distanceInFeet", "Distance en pieds");
        attacks.setWidth("100%");
        getBinder().forMemberField(attacks).withValidator((value, context) -> attacks.isValid());

        FormLayout nameForm = new FormLayout();
        nameForm.addComponents(name, description);

        MultiColumnFormLayout infoForm = new MultiColumnFormLayout(2, "Informations");
        infoForm.addComponents(0, size, creatureType, tag, alignment);
        infoForm.addComponents(1, armorClass, hitPoints, challengeRating, passivePerception);

        MultiColumnFormLayout speedForm = new MultiColumnFormLayout(2, "Vitesse");
        speedForm.addComponents(0, groundSpeed, flySpeed, burrowSpeed);
        speedForm.addComponents(1, swimSpeed, climbSpeed);

        MultiColumnFormLayout abilityForm = new MultiColumnFormLayout(2, "Caractéristiques");
        abilityForm.addComponents(0, strength, dexterity, constitution);
        abilityForm.addComponents(1, intelligence, wisdom, charisma);

        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(damageVulnerabilities, damageResistances, damageImmunities, conditionImmunities);
        formLayout.addComponents(languages, savingThrowProficiencies, skills, senses, attacks);
        formLayout.addComponent(getToolbar());

        layout.addComponents(nameForm, infoForm, abilityForm, speedForm, formLayout);

        return layout;
    }
}
