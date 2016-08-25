package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Region;

public class RegionGrid extends BeanGrid<Region> {

    private static final long serialVersionUID = -2219582474895040784L;

    public RegionGrid() {
        super(Region.class);
        withColumns("name", "shortDescription");
        withHeaderCaption("Nom", "Description courte");
    }

}
