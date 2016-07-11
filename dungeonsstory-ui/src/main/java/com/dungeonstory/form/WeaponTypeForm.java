package com.dungeonstory.form;


import org.vaadin.viritin.fields.CaptionGenerator;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MCheckBox;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class WeaponTypeForm extends DSAbstractForm<WeaponType> {

	private static final long serialVersionUID = 7328613287024515502L;
	
	private TextField name;
	private TextArea description;
	private EnumSelect<ProficiencyType> proficiencyType;
	private EnumSelect<HandleType> handleType;
	private EnumSelect<UsageType> usageType;
	private EnumSelect<RangeType> rangeType;
	private TextField baseDamage;
	private TypedSelect<DamageType> damageType;
	private FormCheckBox isReach;
	private TextField baseWeight;

	@Override
	public String toString() {
		return "Types d'arme";
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		description = new MTextArea("Description").withFullWidth();
		proficiencyType = new EnumSelect<ProficiencyType>("Type de compétence");
		handleType = new EnumSelect<HandleType>("Type");
		usageType = new EnumSelect<UsageType>("Type d'usage");
		rangeType = new EnumSelect<RangeType>("Type de portée");
		baseDamage = new MTextField("Dommage de base");
		isReach = new FormCheckBox("Portée longue");
		baseWeight = new MTextField("Poids de base (lbs)");
		
		damageType = new TypedSelect<DamageType>("Type de dommage", DataService.get().getAllDamageTypes());
		damageType.setCaptionGenerator(new CaptionGenerator<DamageType>() {
            
            private static final long serialVersionUID = 9011176307449121578L;

            @Override
            public String getCaption(DamageType option) {
                return option.getName();
            }
        });
		
		layout.addComponent(name);
		layout.addComponent(description);
		layout.addComponent(proficiencyType);
		layout.addComponent(handleType);
		layout.addComponent(usageType);
		layout.addComponent(rangeType);
		layout.addComponent(baseDamage);
		layout.addComponent(damageType);
		layout.addComponent(isReach);
		layout.addComponent(baseWeight);
		layout.addComponent(getToolbar());

		return layout;
	}

}
