package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Race;

public class RaceGrid extends BeanGrid<Race> {

    private static final long serialVersionUID = -3155610367819192232L;

    public RaceGrid() {
        super(Race.class);
        withColumns("name", "shortDescription");
        withHeaderCaption("Nom", "Description courte");
    }

}
