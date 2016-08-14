package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.CityService;
import com.dungeonstory.backend.service.mock.MockCityService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.CityForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.CityGrid;

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
    public BeanGrid<City> getGrid() {
        return new CityGrid();
    }

    @Override
    public DataService<City, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockCityService.getInstance();
        }
        return CityService.getInstance();
    }

}
