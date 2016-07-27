package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.mock.MockRaceService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.RaceForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.RaceGrid;

@ViewConfig(uri = "races", displayName = "Races")
public class RaceView extends AbstractCrudView<Race> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Race> getForm() {
        return new RaceForm();
    }

    @Override
    public BeanGrid<Race> getGrid() {
        return new RaceGrid();
    }

    @Override
    public DataService<Race, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockRaceService.getInstance();
        }
        return RaceService.getInstance();
    }

}
