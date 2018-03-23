package com.dungeonstory.ui.view.admin.form;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Condition;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.Spell.AreaOfEffect;
import com.dungeonstory.backend.data.Spell.CastingTime;
import com.dungeonstory.backend.data.Spell.ComponentType;
import com.dungeonstory.backend.data.Spell.DurationType;
import com.dungeonstory.backend.data.Spell.MagicSchool;
import com.dungeonstory.backend.data.Spell.RangeType;
import com.dungeonstory.backend.data.Spell.Target;
import com.dungeonstory.backend.data.Spell.TimeUnit;
import com.dungeonstory.backend.data.SpellComponent;
import com.dungeonstory.backend.data.SpellEffect;
import com.dungeonstory.backend.data.SpellEffect.EffectType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.ElementCollectionField;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.field.SubSetSelector;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SpellForm extends DSAbstractForm<Spell> {

    private static final long serialVersionUID = 5527570482793876891L;

    private TextField    name;
    private IntegerField level;
    private TextArea     description;

    private EnumComboBox<MagicSchool> school;

    private SubSetSelector<ComponentType, Set<ComponentType>> componentTypes;
    private SubSetSelector<Equipment, List<Equipment>>        components;

    private EnumComboBox<CastingTime> castingTime;
    private IntegerField              castingTimeValue;
    private EnumComboBox<TimeUnit>    castingTimeUnit;

    private EnumComboBox<DurationType> duration;
    private IntegerField               durationValue;
    private EnumComboBox<TimeUnit>     durationTimeUnit;

    private EnumComboBox<Target>       target;
    private EnumComboBox<AreaOfEffect> areaOfEffect;

    private EnumComboBox<RangeType> range;
    private IntegerField            rangeValueInFeet;

    private ComboBox<Ability> savingThrowAbility;
    private FormCheckBox      attackRoll;
    private FormCheckBox      higherLevel;

    private ElementCollectionField<SpellEffect> effects;

    private DataService<Equipment, Long>  equipmentService  = null;
    private DataService<DamageType, Long> damageTypeService = null;
    private DataService<Ability, Long>    abilityService    = null;

    public SpellForm() {
        super(Spell.class);
        equipmentService = Services.getEquipmentService();
        damageTypeService = Services.getDamageTypeService();
    }

    @Override
    public String toString() {
        return "Sort";
    }

    public static class SpellEffectRow {
        EnumComboBox<EffectType> effectType = new EnumComboBox<>(EffectType.class);
        TextField                damage     = new TextField();
        ComboBox<DamageType>     damageType = new ComboBox<DamageType>();
        IntegerField             armorClass = new DSIntegerField().withWidth("100px");
        EnumComboBox<Condition>  condition  = new EnumComboBox<>(Condition.class);
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        level = new DSIntegerField("Niveau");
        description = new FTextArea("Description").withFullWidth();
        school = new EnumComboBox<>(MagicSchool.class, "École de magie");

        componentTypes = new SubSetSelector<>(ComponentType.class);
        componentTypes.setCaption("Types de composant");
        componentTypes.getGrid().addColumn(ComponentType::getName).setCaption("Type de composant").setId("name");
        componentTypes.getGrid().setColumnOrder("name");
        componentTypes.setItems(Arrays.asList(ComponentType.values()));
        componentTypes.setValue(new HashSet<ComponentType>()); // nothing
                                                               // selected
        componentTypes.setWidth("50%");
        componentTypes.addValueChangeListener(event -> showComponents());

        components = new SubSetSelector<>(Equipment.class);
        components.setNewItemsAllowed(true, (stringInput) -> {
            SpellComponent c = new SpellComponent();
            if (stringInput != null) {
                c.setName(stringInput);
                c.setType(EquipmentType.COMPONENT);
            }
            return c;
        });
        components.setCaption("Composants matériels");
        components.getGrid().addColumn(Equipment::getName).setCaption("Composant");
        components.setItems(equipmentService.findAll());
        components.setValue(null); // nothing selected
        components.setWidth("50%");
        // getBinder().forField(components).withConverter(new
        // CollectionListConverter<>()).bind("components");

        castingTime = new EnumComboBox<CastingTime>(CastingTime.class, "Type de temps d'incantation");
        castingTimeValue = new DSIntegerField("Valeur de temps");
        castingTimeUnit = new EnumComboBox<TimeUnit>(TimeUnit.class, "Unité de temps");
        castingTime.addSelectionListener(event -> showCastingTime());

        duration = new EnumComboBox<DurationType>(DurationType.class, "Type de durée du sort");
        durationValue = new DSIntegerField("Valeur de durée");
        durationTimeUnit = new EnumComboBox<TimeUnit>(TimeUnit.class, "Unité de durée");
        duration.addSelectionListener(event -> showDuration());

        target = new EnumComboBox<Target>(Target.class, "Cible du sort");
        areaOfEffect = new EnumComboBox<AreaOfEffect>(AreaOfEffect.class, "Zone d'effet");
        target.addSelectionListener(event -> showAreaOfEffect());

        range = new EnumComboBox<RangeType>(RangeType.class, "Portée du sort");
        rangeValueInFeet = new DSIntegerField("Portée (en pieds)");
        range.addSelectionListener(event -> showRange());

        savingThrowAbility = new ComboBox<Ability>("Caractéristique de jet de sauvegarde", abilityService.findAll());
        attackRoll = new FormCheckBox("Nécessite un jet d'attaque");
        higherLevel = new FormCheckBox("Peut être lancé à plus haut niveau");

        List<DamageType> allDamageTypes = damageTypeService.findAll();
        effects = new ElementCollectionField<SpellEffect>(SpellEffect.class, SpellEffectRow.class).withCaption("Effets")
                .withEditorInstantiator(() -> {
                    SpellEffectRow row = new SpellEffectRow();
                    row.damageType.setItems(allDamageTypes);

                    row.effectType.addValueChangeListener(new ValueChangeListener<EffectType>() {

                        private static final long serialVersionUID = 5150637627258948217L;

                        @Override
                        public void valueChange(ValueChangeEvent<EffectType> event) {

                            if (event != null && event.getValue() != null) {
                                switch (event.getValue()) {
                                    case DAMAGE:
                                        row.damage.setVisible(true);
                                        row.damageType.setVisible(true);
                                        row.armorClass.setVisible(false);
                                        row.condition.setVisible(false);
                                        break;
                                    case CURE:
                                        row.damage.setVisible(true);
                                        row.damageType.setVisible(false);
                                        row.armorClass.setVisible(false);
                                        row.condition.setVisible(false);
                                        break;
                                    case ADD_CONDITION:
                                    case REMOVE_CONDITION:
                                        row.damage.setVisible(false);
                                        row.damageType.setVisible(false);
                                        row.armorClass.setVisible(false);
                                        row.condition.setVisible(true);
                                        break;
                                    case PROTECTION:
                                        row.damage.setVisible(false);
                                        row.damageType.setVisible(false);
                                        row.armorClass.setVisible(true);
                                        row.condition.setVisible(false);
                                        break;
                                    case RESISTANCE:
                                        row.damage.setVisible(false);
                                        row.damageType.setVisible(true);
                                        row.armorClass.setVisible(false);
                                        row.condition.setVisible(false);
                                        break;
                                    case SUMMON:
                                    case OTHER:
                                        row.damage.setVisible(false);
                                        row.damageType.setVisible(false);
                                        row.armorClass.setVisible(false);
                                        row.condition.setVisible(false);
                                        break;
                                }
                            } else {
                                row.damage.setVisible(false);
                                row.damageType.setVisible(false);
                                row.armorClass.setVisible(false);
                                row.condition.setVisible(false);
                            }
                        }
                    });
                    return row;
                });

        effects.setPropertyHeader("effectType", "Effet");
        effects.setPropertyHeader("damage", "Dommage");
        effects.setPropertyHeader("damageType", "Type dommage");
        effects.setPropertyHeader("armorClass", "Classe d'armure");
        effects.setPropertyHeader("condition", "Condition");

        layout.addComponent(name);
        layout.addComponent(level);
        layout.addComponent(description);
        layout.addComponent(school);

        layout.addComponent(componentTypes);
        layout.addComponent(components);
        layout.addComponent(castingTime);
        layout.addComponent(castingTimeValue);
        layout.addComponent(castingTimeUnit);
        layout.addComponent(duration);
        layout.addComponent(durationValue);
        layout.addComponent(durationTimeUnit);
        layout.addComponent(target);
        layout.addComponent(areaOfEffect);
        layout.addComponent(range);
        layout.addComponent(rangeValueInFeet);
        layout.addComponent(savingThrowAbility);
        layout.addComponent(attackRoll);
        layout.addComponent(higherLevel);
        layout.addComponent(effects);

        layout.addComponent(getToolbar());

        // init fields visibility
        showAreaOfEffect();
        showCastingTime();
        showComponents();
        showDuration();
        showRange();

        return layout;
    }

    private void showComponents() {
        components.setVisible(componentTypes.getValue().contains(ComponentType.M));
    }

    private void showCastingTime() {
        castingTimeValue.setVisible(castingTime.getValue() == CastingTime.TIME);
        castingTimeUnit.setVisible(castingTime.getValue() == CastingTime.TIME);
    }

    private void showDuration() {
        durationValue.setVisible(duration.getValue() == DurationType.TIME);
        durationTimeUnit.setVisible(duration.getValue() == DurationType.TIME);
    }

    private void showAreaOfEffect() {
        areaOfEffect.setVisible(target.getValue() == Target.POINT);
    }

    private void showRange() {
        rangeValueInFeet.setVisible(range.getValue() == RangeType.DISTANCE);
    }

}
