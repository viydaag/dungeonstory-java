package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.service.DeityDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.DeityForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.DeityGrid;

@ViewConfig(uri = "deities", displayName = "Dieux")
public class DeityView extends AbstractCrudView<Deity> {

    private static final long serialVersionUID = -7676716186647252053L;

    public DeityView() {
        super();
    }

    @Override
    public DSAbstractForm<Deity> getForm() {
        return new DeityForm();
    }

    @Override
    public DSGrid<Deity> getGrid() {
        return new DeityGrid();
    }

    @Override
    public DeityDataService getDataService() {
        return Services.getDeityService();
    }

}
