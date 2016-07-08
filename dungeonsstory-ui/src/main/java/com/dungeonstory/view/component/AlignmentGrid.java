package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.samples.crud.BeanGrid;

public class AlignmentGrid extends BeanGrid<Alignment> {

    private static final long serialVersionUID = -6577254670865533975L;

    public AlignmentGrid() {
		super(Alignment.class);
		withColumns("name", "shortDescription", "description");
		withHeaderCaption("Nom", "Description courte", "Description");
	}

}
