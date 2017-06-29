package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Temple;

public class TempleGrid extends DSGrid<Temple> {

    private static final long serialVersionUID = -817220687152288041L;

    public TempleGrid() {
        super();
        addColumn(Temple::getName).setCaption("Nom du temple").setId("name");
        addColumn(Temple::getDeity).setCaption("Dieu").setId("deity");
        addColumn(Temple::getCity).setCaption("Ville").setId("city");
    }

}
