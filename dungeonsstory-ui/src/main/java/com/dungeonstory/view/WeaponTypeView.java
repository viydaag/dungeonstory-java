package com.dungeonstory.view;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.WeaponTypeForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.WeaponTypeGrid;

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
    public BeanGrid<WeaponType> getGrid() {
        return new WeaponTypeGrid();
    }

    @Override
    public DataService<WeaponType, Long> getDataService() {
        return MockWeaponTypeService.getInstance();
    }

}
