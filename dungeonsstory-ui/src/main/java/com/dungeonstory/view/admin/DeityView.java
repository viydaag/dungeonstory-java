package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.DeityService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.DeityForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.CrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.DeityGrid;

@ViewConfig(uri = "deities", displayName = "Dieux")
public class DeityView extends AbstractCrudView<Deity> implements CrudView<Deity> {

    private static final long serialVersionUID = -7676716186647252053L;

    public DeityView() {
        super();
    }

    @Override
    public DSAbstractForm<Deity> getForm() {
        return new DeityForm();
    }

    @Override
    public BeanGrid<Deity> getGrid() {
        return new DeityGrid();
    }

    @Override
    public DataService<Deity, Long> getDataService() {
        return DeityService.getInstance();
    }

}
