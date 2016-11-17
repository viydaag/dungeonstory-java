package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.City;

public class CityGrid extends DSGrid<City> {

    private static final long serialVersionUID = 8197937450154143949L;

    public CityGrid() {
        super(City.class);
        withProperties("name", "shortDescription");
        withHeaderCaption("Nom", "Description courte");
    }

}
