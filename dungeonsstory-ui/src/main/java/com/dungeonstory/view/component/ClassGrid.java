package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.DSClass;

public class ClassGrid extends BeanGrid<DSClass> {

	private static final long serialVersionUID = -2219582474895040784L;

	public ClassGrid() {
		super(DSClass.class);
		withColumns("name", "shortDescription");
	}

}
