package com.dungeonstory.form;

import org.vaadin.viritin.fields.CaptionGenerator;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Skill;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SkillForm extends DSAbstractForm<Skill> {

    private static final long serialVersionUID = -4123881637907722632L;
    
    private TextField name;
	private TextArea description;
//	private ComboBox keyAbility;
//	ElementCollectionField<Ability> keyAbility;
	private TypedSelect<Ability> keyAbility;

	public SkillForm() {
	    super();
	}

	@Override
	public String toString() {
		return "Talents";
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		description = new MTextArea("Description").withFullWidth();
//		keyAbility = new ComboBox("Attribut clé", DataService.get().getAllAbilities());
//		keyAbility = new ElementCollectionField<Ability>(Ability.class, Ability.class).withCaption("Attribut clé");
		keyAbility = new TypedSelect<Ability>("Attribut clé", DataService.get().getAllAbilities());
		keyAbility.setCaptionGenerator(new CaptionGenerator<Ability>() {
            
            @Override
            public String getCaption(Ability option) {
                return option.getName();
            }
        });
		
		layout.addComponent(name);
		layout.addComponent(description);
		layout.addComponent(keyAbility);
		layout.addComponent(getToolbar());

		return layout;
	}
}