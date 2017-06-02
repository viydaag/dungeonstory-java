package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.service.DamageTypeDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.DamageTypeForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.DamageTypeGrid;

@ViewConfig(uri = "damageTypes", displayName = "Types de dommages")
public class DamageTypeView extends AbstractCrudView<DamageType> {

    private static final long serialVersionUID = 2197369530490492330L;

    public DamageTypeView() {
        super();
    }

    @Override
    public DSAbstractForm<DamageType> getForm() {
        return new DamageTypeForm();
    }

    @Override
    public DSGrid<DamageType> getGrid() {
        return new DamageTypeGrid();
    }

    @Override
    public DamageTypeDataService getDataService() {
        return Services.getDamageTypeService();
    }

}
