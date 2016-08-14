package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.City;

public class CityGrid extends BeanGrid<City> {

	private static final long serialVersionUID = 8197937450154143949L;

	public CityGrid() {
		super(City.class);
		withColumns("name", "shortDescription");
		withHeaderCaption("Nom", "Description courte");
	}

}
