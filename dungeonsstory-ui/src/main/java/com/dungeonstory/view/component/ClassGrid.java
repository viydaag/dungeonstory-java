package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.samples.crud.BeanGrid;

public class ClassGrid extends BeanGrid<DSClass> {

	private static final long serialVersionUID = -2219582474895040784L;

	public ClassGrid(Class<DSClass> beanClass) {
		super(beanClass);
		withColumns("name", "shortDescription");
	}

}
