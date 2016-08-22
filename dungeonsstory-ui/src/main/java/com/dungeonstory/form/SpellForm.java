package com.dungeonstory.form;

import org.vaadin.viritin.fields.ElementCollectionField;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.Spell.AreaOfEffect;
import com.dungeonstory.backend.data.Spell.CastingTime;
import com.dungeonstory.backend.data.Spell.ComponentType;
import com.dungeonstory.backend.data.Spell.DurationType;
import com.dungeonstory.backend.data.Spell.EffectType;
import com.dungeonstory.backend.data.Spell.MagicSchool;
import com.dungeonstory.backend.data.Spell.RangeType;
import com.dungeonstory.backend.data.Spell.Target;
import com.dungeonstory.backend.data.Spell.TimeUnit;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SpellForm extends DSAbstractForm<Spell> {

    private static final long serialVersionUID = 5527570482793876891L;
    
    private TextField name;
	private IntegerField level;
	private TextArea description;
	
	private EnumSelect<MagicSchool> school;
	
	private ElementCollectionField<ComponentType> componentTypes;
	private ElementCollectionField<Equipment> components;
	
	private EnumSelect<CastingTime> castingTime;
	private IntegerField castingTimeValue;
	private EnumSelect<TimeUnit> castingTimeUnit;
	
	private EnumSelect<DurationType> duration;
    private IntegerField durationValue;
    private EnumSelect<TimeUnit> durationTimeUnit;
    
    private EnumSelect<Target> target;
    private EnumSelect<AreaOfEffect> areaOfEffect;
    
    private EnumSelect<RangeType> range;
    private IntegerField rangeValueInFeet;
    
    private TypedSelect<Ability> savingThrowAbility;
    private FormCheckBox attackRoll;
    private FormCheckBox higherLevel;
    
    private EnumSelect<EffectType> effectType;
    
    private TextField damage;
    private TypedSelect<DamageType> damageType;
    
    private DataService<Equipment, Long> equipmentService = null;
    private DataService<DamageType, Long> damageTypeService = null;
    private DataService<Ability, Long> abilityService = null;
    
	public SpellForm() {
	    super();
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
	
	public static class ComponentTypeRow {
        EnumSelect<ComponentType> componentTypes = new EnumSelect<ComponentType>();
    }
	
	public static class ComponentRow {
        TypedSelect<Equipment> component = new TypedSelect<Equipment>();
    }

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		level = new IntegerField("Niveau");
		description = new MTextArea("Description").withFullWidth();
		school = new EnumSelect<>("École de magie");
		
		componentTypes = new ElementCollectionField<>(ComponentType.class, ComponentTypeRow.class)
		        .withCaption("Types de composant")
                .withEditorInstantiator(() -> {
                    ComponentTypeRow row = new ComponentTypeRow();
                    row.componentTypes.setOptions(ComponentType.values());
                    return row;
                }
        );
		components = new ElementCollectionField<>(Equipment.class, ComponentRow.class)
                .withCaption("Composants matériels")
                .withEditorInstantiator(() -> {
                    ComponentRow row = new ComponentRow();
                    row.component.setOptions(equipmentService.findAll());
                    return row;
                }
        );
		
		castingTime = new EnumSelect<CastingTime>("Type de temps d'incantation");
		castingTimeValue = new IntegerField("Valeur de temps");
		castingTimeUnit = new EnumSelect<TimeUnit>("Unité de temps");
		
		duration = new EnumSelect<DurationType>("Type de durée du sort");
		durationValue = new IntegerField("Valeur de durée");
		durationTimeUnit = new EnumSelect<TimeUnit>("Unité de durée");
		
		target = new EnumSelect<Target>("Cible du sort");
		areaOfEffect = new EnumSelect<AreaOfEffect>("Zone d'effet");
		
		range = new EnumSelect<RangeType>("Portée du sort");
		rangeValueInFeet = new IntegerField("Portée (en pieds)");
		
		savingThrowAbility = new TypedSelect<Ability>("Capacité de jet de sauvegarde", abilityService.findAll());
		attackRoll = new FormCheckBox("Nécessite un jet d'attaque");
		higherLevel = new FormCheckBox("Peut être lancé à plus haut niveau");
		
		effectType = new EnumSelect<EffectType>("Type d'effet");
		damage = new MTextField("Dommage");
		damageType = new TypedSelect<DamageType>("Type de dommage", damageTypeService.findAll());
		
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
		layout.addComponent(effectType);
		layout.addComponent(damage);
		layout.addComponent(damageType);
		
		layout.addComponent(getToolbar());

		return layout;
	}
}
