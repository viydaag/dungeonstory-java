package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Background;

public class BackgroundGrid extends DSGrid<Background> {

    private static final long serialVersionUID = 9205275025444235763L;

    public BackgroundGrid() {
        super();
        addColumn(Background::getName).setCaption("Nom").setId("name");
    }

}
