package com.dungeonstory.view;

import com.dungeonstory.samples.backend.data.Region;
import com.dungeonstory.samples.crud.BeanGrid;

public class RegionGrid extends BeanGrid<Region> {

	private static final long serialVersionUID = -2219582474895040784L;

	public RegionGrid(Class<Region> beanClass) {
		super(beanClass);
		setColumnOrder("id", "name", "shortDescription", "description");
	}

}
