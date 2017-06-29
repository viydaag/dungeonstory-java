package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.service.TempleDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.TempleForm;
import com.dungeonstory.ui.view.admin.grid.TempleGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "temples", displayName = "Temples")
public class TempleView extends AbstractCrudView<Temple> {

    private static final long serialVersionUID = -3555323664572622132L;

    @Override
    public DSAbstractForm<Temple> getForm() {
        return new TempleForm();
    }

    @Override
    public DSGrid<Temple> getGrid() {
        return new TempleGrid();
    }

    @Override
    public TempleDataService getDataService() {
        return Services.getTempleService();
    }

}
