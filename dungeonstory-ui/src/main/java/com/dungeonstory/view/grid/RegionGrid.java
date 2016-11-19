package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Region;

public class RegionGrid extends DSGrid<Region> {

    private static final long serialVersionUID = -2219582474895040784L;

    public RegionGrid() {
        super(Region.class);
        withProperties("name");
        withColumnHeaders("Nom");
    }

}