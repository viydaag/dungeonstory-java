package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.service.DeityDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.DeityForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.CrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.DeityGrid;

@ViewConfig(uri = "deities", displayName = "Dieux")
public class DeityView extends AbstractCrudView<Deity> implements CrudView<Deity> {

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
