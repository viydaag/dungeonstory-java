package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Alignment;

public class AlignmentGrid extends DSGrid<Alignment> {

    private static final long serialVersionUID = -6577254670865533975L;

    public AlignmentGrid() {
        super();
        addColumn(Alignment::getName).setCaption("Nom").setId("name");
        addColumn(Alignment::getAbbreviation).setCaption("Abbréviation").setId("abbreviation");
        addColumn(Alignment::getShortDescription).setCaption("Description courte").setId("shortDescription");
    }

}
