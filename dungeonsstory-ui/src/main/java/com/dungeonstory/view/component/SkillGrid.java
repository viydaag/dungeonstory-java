package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Skill;

public class SkillGrid extends BeanGrid<Skill> {

	private static final long serialVersionUID = 5108019895920968099L;

	public SkillGrid() {
		super(Skill.class);
		
		getContainer().addNestedContainerBean("keyAbility");
		withColumns("name", "keyAbility.name");
		
		getColumn("name").setHeaderCaption("Nom");
		getColumn("keyAbility.name").setHeaderCaption("Attribut clé");
	}

}
