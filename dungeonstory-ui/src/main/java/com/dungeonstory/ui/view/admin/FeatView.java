package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.FeatGrid;
import com.vaadin.data.provider.GridSortOrder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "feats", displayName = "Dons")
public class FeatView extends VerticalLayout implements View {

    private static final long serialVersionUID = -6228189557239311489L;

    @Override
    public void enter(ViewChangeEvent event) {
        FeatGrid grid = new FeatGrid();
        grid.setSortOrder(GridSortOrder.asc(grid.getColumn("name")));
        addComponent(grid);
    }

}
