package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.BackgroundService;
import com.dungeonstory.form.BackgroundForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BackgroundGrid;
import com.dungeonstory.view.component.BeanGrid;

@ViewConfig(uri = "backgrounds", displayName = "Backgrounds")
public class BackgroundView extends AbstractCrudView<Background> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Background> getForm() {
        return new BackgroundForm();
    }

    @Override
    public BeanGrid<Background> getGrid() {
        return new BackgroundGrid();
    }

    @Override
    public DataService<Background, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            //TODO
            //return MockBackgroundService.getInstance();
        }
        return BackgroundService.getInstance();
    }

}
