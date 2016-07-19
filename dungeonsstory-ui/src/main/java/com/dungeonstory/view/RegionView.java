package com.dungeonstory.view;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockRegionService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.RegionForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.RegionGrid;

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
    public BeanGrid<Region> getGrid() {
        return new RegionGrid();
    }

    @Override
    public DataService<Region, Long> getDataService() {
        return MockRegionService.getInstance();
    }

}
