package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.util.DSTheme;

public class CreatureTypeGrid extends DSGrid<CreatureType> {

    private static final long serialVersionUID = 7979077646352869311L;

    public CreatureTypeGrid() {
        super();
        addColumn(CreatureType::getName).setCaption("Nom").setId("name").setStyleGenerator(item -> DSTheme.TEXT_CENTER_ALIGNED);
    }

}
