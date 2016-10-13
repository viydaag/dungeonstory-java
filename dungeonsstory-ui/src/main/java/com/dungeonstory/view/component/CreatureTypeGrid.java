package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.util.DSTheme;

public class CreatureTypeGrid extends BeanGrid<CreatureType> {

    private static final long serialVersionUID = 7979077646352869311L;

    public CreatureTypeGrid() {
        super(CreatureType.class);
        withColumns("name");
        withHeaderCaption("Nom");

        setCellStyleGenerator(cell -> {
            if (cell.getPropertyId().equals("name")) {
                return DSTheme.TEXT_CENTER_ALIGNED;
            } else {
                return DSTheme.TEXT_LEFT_ALIGNED;
            }
        });
    }

}
