package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.service.CityDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.CityForm;
import com.dungeonstory.ui.view.admin.grid.CityGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "cities", displayName = "Villes")
public class CityView extends AbstractCrudView<City> {

    private static final long serialVersionUID = 5117755861151432771L;

    public CityView() {
        super();
    }

    @Override
    public DSAbstractForm<City> getForm() {
        return new CityForm();
    }

    @Override
    public DSGrid<City> getGrid() {
        return new CityGrid();
    }

    @Override
    public CityDataService getDataService() {
        return Services.getCityService();
    }

}
