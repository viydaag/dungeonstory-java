package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Inn;

public class InnGrid extends DSGrid<Inn> {

    private static final long serialVersionUID = -2457665452813086836L;

    public InnGrid() {
        super();
        addColumn(Inn::getName).setCaption("Nom de l'auberge").setId("name");
        addColumn(Inn::getCity).setCaption("Ville").setId("city");
    }

}
