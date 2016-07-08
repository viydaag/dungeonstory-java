package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.samples.crud.BeanGrid;

public class RegionGrid extends BeanGrid<Region> {

	private static final long serialVersionUID = -2219582474895040784L;

	public RegionGrid() {
		super(Region.class);
		setColumnOrder("name", "shortDescription", "description");
	}

}
