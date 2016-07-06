package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.samples.crud.BeanGrid;

public class SkillGrid extends BeanGrid<Skill> {

	private static final long serialVersionUID = 5108019895920968099L;

	public SkillGrid(Class<Skill> beanClass) {
		super(beanClass);
		
		getContainer().addNestedContainerBean("keyAbility");
		withColumns("name", "keyAbility.name");
		
		getColumn("name").setHeaderCaption("Nom");
		getColumn("keyAbility.name").setHeaderCaption("Attribut clé");
	}

}
