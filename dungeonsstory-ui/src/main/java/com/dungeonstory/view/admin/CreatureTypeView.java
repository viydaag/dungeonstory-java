package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.CreatureTypeService;
import com.dungeonstory.form.CreatureTypeForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.CreatureTypeGrid;
import com.dungeonstory.view.grid.DSGrid;
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
    public DataService<CreatureType, Long> getDataService() {
        return CreatureTypeService.getInstance();
    }
    
    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        grid.sort("name", SortDirection.ASCENDING);
    }

}
