package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Alignment;

public class AlignmentGrid extends DSGrid<Alignment> {

    private static final long serialVersionUID = -6577254670865533975L;

    public AlignmentGrid() {
        super(Alignment.class);
        withProperties("name", "abbreviation", "shortDescription");
        withHeaderCaption("Nom", "Abbréviation", "Description courte");
    }

}
