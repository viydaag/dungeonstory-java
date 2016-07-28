package com.dungeonstory.form;

import java.util.List;

import org.vaadin.viritin.fields.ElementCollectionField;
import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelBonusFeat;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.view.component.DSSubSetSelector;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassForm extends DSAbstractForm<DSClass> {

    private static final long serialVersionUID = -4123881637907722632L;
    
    private TextField name;
    private TextField shortDescription;
	private TextArea description;
	private DSSubSetSelector<Skill> baseSkills;
	private ElementCollectionField<ClassLevelBonus> levelBonuses;
	private ElementCollectionTable<ClassLevelBonusFeat> featBonuses;
	
	
	DataService<Skill, Long> skillService = SkillService.getInstance();
	DataService<Level, Long> levelService = LevelService.getInstance();
	DataService<Feat, Long> featService = FeatService.getInstance();
	
    public static class ClassLevelBonusRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        CheckBox hasAbilityScoreImprovement = new CheckBox();
    }
    
    public static class ClassLevelBonusFeatRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Feat> feat = new TypedSelect<Feat>();
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
		baseSkills.setOptions((List<Skill>) skillService.findAll());
//		baseSkills.setNewItemsAllowed(false);
//		baseSkills.setValue(new HashSet<Skill>()); //nothing selected
		
		levelBonuses = new ElementCollectionField<ClassLevelBonus>(ClassLevelBonus.class, ClassLevelBonusRow.class)
		        .withCaption("Bonus de classe")
		        .withEditorInstantiator(() -> {
        		    ClassLevelBonusRow row = new ClassLevelBonusRow();
                    // The ManyToOne field needs its options to be populated
                    // Note, if there is lots of rows, it would be better to share
                    // the list of addresstypes
        		    row.level.setOptions(levelService.findAll());
                    return row;
		        }
		);
		levelBonuses.setPropertyHeader("level", "Niveau");
		levelBonuses.setPropertyHeader("hasAbilityScoreImprovement", "Amélioration attribut");
		
		featBonuses = new ElementCollectionTable<ClassLevelBonusFeat>(ClassLevelBonusFeat.class, ClassLevelBonusFeatRow.class)
                .withCaption("Bonus de classe")
                .withEditorInstantiator(() -> {
                    ClassLevelBonusFeatRow row = new ClassLevelBonusFeatRow();
                    row.level.setOptions(levelService.findAll());
                    row.feat.setOptions(featService.findAll());
                    return row;
                }
        );
		featBonuses.setPropertyHeader("level", "Niveau");
		featBonuses.setPropertyHeader("feat", "Don");
		
		layout.addComponent(name);
		layout.addComponent(description);
		layout.addComponent(shortDescription);
		layout.addComponent(baseSkills);
		layout.addComponent(levelBonuses);
		layout.addComponent(featBonuses);
		layout.addComponent(getToolbar());

		return layout;
	}
}
