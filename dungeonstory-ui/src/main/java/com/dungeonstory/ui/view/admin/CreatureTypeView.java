package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.service.CreatureTypeDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.CreatureTypeForm;
import com.dungeonstory.ui.view.admin.grid.CreatureTypeGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;

@ViewConfig(uri = "creatureTypes", displayName = "Types de créature")
public class CreatureTypeView extends AbstractCrudView<CreatureType> {


    private static final long serialVersionUID = -2610965909546215542L;

    public CreatureTypeView() {
        super();
    }

    @Override
    public DSAbstractForm<CreatureType> getForm() {
        return new CreatureTypeForm();
    }

    @Override
    public DSGrid<CreatureType> getGrid() {
        return new CreatureTypeGrid();
    }

    @Override
    public CreatureTypeDataService getDataService() {
        return Services.getCreatureTypeService();
    }
    
    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        grid.sort("name", SortDirection.ASCENDING);
    }

}
