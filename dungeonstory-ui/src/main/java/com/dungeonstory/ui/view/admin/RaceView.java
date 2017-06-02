package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.RaceDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.RaceForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.RaceGrid;

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
