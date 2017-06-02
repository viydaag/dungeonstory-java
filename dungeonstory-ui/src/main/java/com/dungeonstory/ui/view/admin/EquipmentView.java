package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.EquipmentForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.EquipmentGrid;

@ViewConfig(uri = "equipments", displayName = "Équipements")
public class EquipmentView extends AbstractCrudView<Equipment> {

    private static final long serialVersionUID = 6456489454745469489L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public DSAbstractForm<Equipment> getForm() {
        return new EquipmentForm();
    }

    @Override
    public DSGrid<Equipment> getGrid() {
        return new EquipmentGrid();
    }

    @Override
    public EquipmentDataService getDataService() {
        return Services.getEquipmentService();
    }

}
