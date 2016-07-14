package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.samples.crud.BeanGrid;

public class DamageTypeGrid extends BeanGrid<DamageType> {

    private static final long serialVersionUID = 1703049496748455895L;

    public DamageTypeGrid() {
		super(DamageType.class);
		setColumnOrder("name", "description");
	}

}