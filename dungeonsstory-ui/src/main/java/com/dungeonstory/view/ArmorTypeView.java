package com.dungeonstory.view;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockArmorTypeService;
import com.dungeonstory.form.ArmorTypeForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.ArmorTypeGrid;

@ViewConfig(uri = "armorTypes", displayName = "Types d'armure")
public class ArmorTypeView extends AbstractCrudView<ArmorType> {

    private static final long serialVersionUID = 4239959044896030062L;
    
    @Override
    public DSAbstractForm<ArmorType> getForm() {
        return new ArmorTypeForm();
    }

    @Override
    public BeanGrid<ArmorType> getGrid() {
        return new ArmorTypeGrid();
    }

    @Override
    public DataService<ArmorType, Long> getDataService() {
        return MockArmorTypeService.getInstance();
    }

}
