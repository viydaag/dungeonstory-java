package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Background;

public class BackgroundGrid extends BeanGrid<Background> {

    private static final long serialVersionUID = 9205275025444235763L;

    public BackgroundGrid() {
        super(Background.class);
        withColumns("name");
        withHeaderCaption("Nom");
    }

}
