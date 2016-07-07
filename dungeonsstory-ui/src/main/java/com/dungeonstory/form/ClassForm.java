package com.dungeonstory.form;

import java.util.List;

import org.vaadin.viritin.fields.ElementCollectionField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.view.component.DSSubSetSelector;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassForm extends AbstractForm<DSClass> {

    private static final long serialVersionUID = -4123881637907722632L;
    
    private TextField name;
    private TextField shortDescription;
	private TextArea description;
//	private ComboBox keyAbility;
	ElementCollectionField<ClassLevelBonus> levelBonuses;
	DSSubSetSelector<Skill> baseSkills;
	
    public static class ClassLevelBonusRow {
//        TypedSelect<Level> level = new TypedSelect<Level>("Niveau");
//        CheckBox hasAbilityScoreImprovement = new CheckBox("Amélioration attribut");
        TypedSelect<Level> level = new TypedSelect<Level>();
        CheckBox hasAbilityScoreImprovement = new CheckBox();
    }

	public ClassForm() {
	    super();
	}

	@Override
	public String toString() {
		return "Classes";
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		shortDescription = new MTextField("Description courte");
		description = new MTextArea("Description").withFullWidth();
		
		baseSkills = new DSSubSetSelector<Skill>(Skill.class);
		baseSkills.setCaption("Talents de base");
		baseSkills.setVisibleProperties("name", "keyAbility.name");
		baseSkills.setColumnHeader("name", "Nom");
		baseSkills.setColumnHeader("keyAbility.name", "Attribut clé");
		baseSkills.setOptions((List<Skill>)DataService.get().getAllSkills());
//		baseSkills.setNewItemsAllowed(false);
//		baseSkills.setValue(new HashSet<Skill>()); //nothing selected
		
		levelBonuses = new ElementCollectionField<ClassLevelBonus>(ClassLevelBonus.class, ClassLevelBonusRow.class)
		        .withCaption("Bonus de classe")
		        .withEditorInstantiator(() -> {
        		    ClassLevelBonusRow row = new ClassLevelBonusRow();
                    // The ManyToOne field needs its options to be populated
                    // Note, if there is lots of rows, it would be better to share
                    // the list of addresstypes
        		    row.level.setOptions(DataService.get().getAllLevels());
                    return row;
		        }
		);
		levelBonuses.setPropertyHeader("level", "Niveau");
		levelBonuses.setPropertyHeader("hasAbilityScoreImprovement", "Amélioration attribut");
		
		    
//		keyAbility = new ComboBox("Attribut clé", DataService.get().getAllAbilities());
//		keyAbility = new ElementCollectionField<Ability>(Ability.class, Ability.class).withCaption("Attribut clé");
//		keyAbility = new TypedSelect<Ability>("Attribut clé", DataService.get().getAllAbilities());
//		keyAbility.setCaptionGenerator(new CaptionGenerator<Ability>() {
//            
//            @Override
//            public String getCaption(Ability option) {
//                return option.getName();
//            }
//        });
		
		layout.addComponent(name);
		layout.addComponent(description);
		layout.addComponent(shortDescription);
		layout.addComponent(baseSkills);
		layout.addComponent(levelBonuses);
		layout.addComponent(getToolbar());

		return layout;
	}
}
