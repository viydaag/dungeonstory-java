package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.EquipmentForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.EquipmentGrid;

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
