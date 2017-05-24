package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.FeatForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.FeatGrid;
import com.vaadin.data.provider.GridSortOrder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@ViewConfig(uri = "feats", displayName = "Dons")
public class FeatView extends AbstractCrudView<Feat> {

    private static final long serialVersionUID = -6228189557239311489L;

    @Override
    public DSAbstractForm<Feat> getForm() {
        return new FeatForm();
    }

    @Override
    public DSGrid<Feat> getGrid() {
        return new FeatGrid();
    }

    @Override
    public FeatDataService getDataService() {
        return Services.getFeatService();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        //        grid.sort(Sort.by("isClassFeature", SortDirection.ASCENDING).then("name", SortDirection.ASCENDING));
        grid.setSortOrder(GridSortOrder.asc(grid.getColumn("isClassFeature")).thenDesc(grid.getColumn("name")));
    }

}
