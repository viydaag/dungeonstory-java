package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.samples.crud.BeanGrid;

public class WeaponTypeGrid extends BeanGrid<WeaponType> {

	private static final long serialVersionUID = -425928960446143041L;

	public WeaponTypeGrid() {
		super(WeaponType.class);
		withColumns("name", "description", "proficiencyType");
		withHeaderCaption("Nom", "Description", "Type de compétence");
	}

}
