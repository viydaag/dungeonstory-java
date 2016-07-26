package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.samples.crud.BeanGrid;

public class FeatGrid extends BeanGrid<Feat> {

    private static final long serialVersionUID = -6577254670865533975L;

    public FeatGrid() {
		super(Feat.class);
		withColumns("name", "usage");
		withHeaderCaption("Nom", "Usage");
	}

}
