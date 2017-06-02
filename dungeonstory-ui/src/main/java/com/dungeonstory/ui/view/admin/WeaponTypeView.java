package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.WeaponTypeDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.WeaponTypeForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.WeaponTypeGrid;

@ViewConfig(uri = "weaponTypes", displayName = "Types d'arme")
public class WeaponTypeView extends AbstractCrudView<WeaponType> {

    private static final long serialVersionUID = 4239959044896030062L;

    public WeaponTypeView() {
        super();
    }

    @Override
    public DSAbstractForm<WeaponType> getForm() {
        return new WeaponTypeForm();
    }

    @Override
    public DSGrid<WeaponType> getGrid() {
        return new WeaponTypeGrid();
    }

    @Override
    public WeaponTypeDataService getDataService() {
        return Services.getWeaponTypeService();
    }

}
