package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Race;

public class RaceGrid extends DSGrid<Race> {

    private static final long serialVersionUID = -3155610367819192232L;

    public RaceGrid() {
        super(Race.class);
        withProperties("name");
        withColumnHeaders("Nom");
    }

}
