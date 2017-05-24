package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.service.ArmorTypeDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.ArmorTypeForm;
import com.dungeonstory.ui.view.admin.grid.ArmorTypeGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "armorTypes", displayName = "Types d'armure")
public class ArmorTypeView extends AbstractCrudView<ArmorType> {

    private static final long serialVersionUID = 4239959044896030062L;
    
    @Override
    public DSAbstractForm<ArmorType> getForm() {
        return new ArmorTypeForm();
    }

    @Override
    public DSGrid<ArmorType> getGrid() {
        return new ArmorTypeGrid();
    }

    @Override
    public ArmorTypeDataService getDataService() {
        return Services.getArmorTypeService();
    }

}
