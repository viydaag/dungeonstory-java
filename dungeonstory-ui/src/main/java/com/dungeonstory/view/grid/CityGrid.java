package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.City;

public class CityGrid extends DSGrid<City> {

    private static final long serialVersionUID = 8197937450154143949L;

    public CityGrid() {
        super();
        addColumn(City::getName).setCaption("Nom").setId("name");
        addColumn(City::getShortDescription).setCaption("Description courte").setId("shortDescription");
    }

}
