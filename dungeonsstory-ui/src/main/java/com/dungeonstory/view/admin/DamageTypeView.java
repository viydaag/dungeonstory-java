package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.DamageTypeForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.DamageTypeGrid;

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
    public BeanGrid<DamageType> getGrid() {
        return new DamageTypeGrid();
    }

    @Override
    public DataService<DamageType, Long> getDataService() {
        return DamageTypeService.getInstance();
    }

}
