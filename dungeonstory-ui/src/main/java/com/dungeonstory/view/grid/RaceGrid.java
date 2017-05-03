package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Race;

public class RaceGrid extends DSGrid<Race> {

    private static final long serialVersionUID = -3155610367819192232L;

    public RaceGrid() {
        super();
        addColumn(Race::getName).setCaption("Nom").setId("name");
    }

}
