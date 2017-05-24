package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.RaceDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.RaceForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.RaceGrid;

@ViewConfig(uri = "races", displayName = "Races")
public class RaceView extends AbstractCrudView<Race> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Race> getForm() {
        return new RaceForm();
    }

    @Override
    public DSGrid<Race> getGrid() {
        return new RaceGrid();
    }

    @Override
    public RaceDataService getDataService() {
        return Services.getRaceService();
    }

}
