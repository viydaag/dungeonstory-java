package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.service.BackgroundDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.BackgroundForm;
import com.dungeonstory.ui.view.admin.grid.BackgroundGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "backgrounds", displayName = "Backgrounds")
public class BackgroundView extends AbstractCrudView<Background> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Background> getForm() {
        return new BackgroundForm();
    }

    @Override
    public DSGrid<Background> getGrid() {
        return new BackgroundGrid();
    }

    @Override
    public BackgroundDataService getDataService() {
        return Services.getBackgroundService();
    }

}
