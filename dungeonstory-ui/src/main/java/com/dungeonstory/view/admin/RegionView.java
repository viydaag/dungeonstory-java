package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.RegionForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.RegionGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;

@ViewConfig(uri = "regions", displayName = "Régions")
public class RegionView extends AbstractCrudView<Region> {

    private static final long serialVersionUID = 5117755861151432771L;

    public RegionView() {
        super();
    }

    @Override
    public DSAbstractForm<Region> getForm() {
        return new RegionForm();
    }

    @Override
    public DSGrid<Region> getGrid() {
        return new RegionGrid();
    }

    @Override
    public DataService<Region, Long> getDataService() {
        return Services.getRegionService();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        grid.sort("name", SortDirection.ASCENDING);
    }

}
