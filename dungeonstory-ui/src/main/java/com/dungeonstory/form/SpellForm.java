package com.dungeonstory.form;

import java.util.Arrays;
import java.util.HashSet;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.v7.fields.ElementCollectionField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
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
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.util.converter.CollectionListConverter;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.field.DSSubSetSelector2;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
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

    private DSSubSetSelector<ComponentType> componentTypes;
    private DSSubSetSelector2<Equipment>    components;

    private EnumComboBox<CastingTime> castingTime;
    private IntegerField            castingTimeValue;
    private EnumComboBox<TimeUnit>    castingTimeUnit;

    private EnumComboBox<DurationType> duration;
    private IntegerField             durationValue;
    private EnumComboBox<TimeUnit>     durationTimeUnit;

    private EnumComboBox<Target>       target;
    private EnumComboBox<AreaOfEffect> areaOfEffect;

    private EnumComboBox<RangeType> range;
    private IntegerField          rangeValueInFeet;

    private ComboBox<Ability> savingThrowAbility;
    private FormCheckBox         attackRoll;
    private FormCheckBox         higherLevel;

    private ElementCollectionField<SpellEffect> effects;

    private DataService<Equipment, Long>  equipmentService  = null;
    private DataService<DamageType, Long> damageTypeService = null;
    private DataService<Ability, Long>    abilityService    = null;

    public SpellForm() {
        super(Spell.class);
        if (Configuration.getInstance().isMock()) {
            equipmentService = MockEquipmentService.getInstance();
            damageTypeService = MockDamageTypeService.getInstance();
            abilityService = MockAbilityService.getInstance();
        } else {
            equipmentService = EquipmentService.getInstance();
            damageTypeService = DamageTypeService.getInstance();
            abilityService = AbilityService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Sort";
    }

    public static class SpellEffectRow {
        EnumComboBox<EffectType> effectType = new EnumComboBox<EffectType>(EffectType.class);
        MTextField              damage     = new MTextField().withWidth("100px");
        ComboBox<DamageType>     damageType = new ComboBox<DamageType>();
        IntegerField            armorClass = new IntegerField().withWidth("100px");
        EnumComboBox<Condition>  condition  = new EnumComboBox<Condition>(Condition.class);
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        level = new IntegerField("Niveau");
        description = new DSTextArea("Description").withFullWidth();
        school = new EnumComboBox<>(MagicSchool.class, "École de magie");

        componentTypes = new DSSubSetSelector<ComponentType>(ComponentType.class);
        componentTypes.setCaption("Types de composant");
        componentTypes.setVisibleProperties("name");
        componentTypes.setColumnHeader("name", "Type de composant");
        componentTypes.setOptions(Arrays.asList(ComponentType.values()));
        componentTypes.setValue(new HashSet<ComponentType>()); // nothing selected
        componentTypes.setWidth("50%");
        componentTypes.addValueChangeListener(event -> showComponents());

        //        components = new DSSubSetSelector<Equipment>(Equipment.class) {
        //
        //            private static final long serialVersionUID = 1329224024667579287L;
        //
        //            @Override
        //            protected Equipment instantiateOption(String stringInput) {
        //                SpellComponent c = new SpellComponent();
        //                if(stringInput != null) {
        //                    c.setName(stringInput);
        //                    c.setType(EquipmentType.COMPONENT);
        //                }
        //                return c;
        //            }
        //        };
        components = new DSSubSetSelector2<Equipment>(Equipment.class);
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
        //        components.setVisibleProperties("name");
        //        components.setColumnHeader("name", "Composant");
        components.setItems(equipmentService.findAll());
        components.setValue(new HashSet<Equipment>()); // nothing selected
        components.setWidth("50%");
        getBinder().forField(components).withConverter(new CollectionListConverter<>()).bind("components");

        castingTime = new EnumComboBox<CastingTime>(CastingTime.class, "Type de temps d'incantation");
        castingTimeValue = new IntegerField("Valeur de temps");
        castingTimeUnit = new EnumComboBox<TimeUnit>(TimeUnit.class, "Unité de temps");
        castingTime.addSelectionListener(event -> showCastingTime());

        duration = new EnumComboBox<DurationType>(DurationType.class, "Type de durée du sort");
        durationValue = new IntegerField("Valeur de durée");
        durationTimeUnit = new EnumComboBox<TimeUnit>(TimeUnit.class, "Unité de durée");
        duration.addSelectionListener(event -> showDuration());

        target = new EnumComboBox<Target>(Target.class, "Cible du sort");
        areaOfEffect = new EnumComboBox<AreaOfEffect>(AreaOfEffect.class, "Zone d'effet");
        target.addSelectionListener(event -> showAreaOfEffect());

        range = new EnumComboBox<RangeType>(RangeType.class, "Portée du sort");
        rangeValueInFeet = new IntegerField("Portée (en pieds)");
        range.addSelectionListener(event -> showRange());

        savingThrowAbility = new ComboBox<Ability>("Caractéristique de jet de sauvegarde", abilityService.findAll());
        attackRoll = new FormCheckBox("Nécessite un jet d'attaque");
        higherLevel = new FormCheckBox("Peut être lancé à plus haut niveau");

        effects = new ElementCollectionField<SpellEffect>(SpellEffect.class, SpellEffectRow.class).withCaption("Effets")
                .withEditorInstantiator(() -> {
                    SpellEffectRow row = new SpellEffectRow();
                    row.effectType.setItems(Arrays.asList(EffectType.values()));
                    row.condition.setItems(Arrays.asList(Condition.values()));
                    row.damageType.setItems(damageTypeService.findAll());
                    
                    row.effectType.addSelectionListener(new SingleSelectionListener<EffectType>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public void selectionChange(SingleSelectionEvent<EffectType> event) {
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
        components.setVisible(componentTypes.getTable().containsId(ComponentType.M));
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
