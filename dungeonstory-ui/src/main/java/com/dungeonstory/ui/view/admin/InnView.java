package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.service.InnDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.InnForm;
import com.dungeonstory.ui.view.admin.grid.InnGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "inns", displayName = "Auberges")
public class InnView extends AbstractCrudView<Inn> {

    private static final long serialVersionUID = 7103665917856246182L;

    @Override
    public DSAbstractForm<Inn> getForm() {
        return new InnForm();
    }

    @Override
    public DSGrid<Inn> getGrid() {
        return new InnGrid();
    }

    @Override
    public InnDataService getDataService() {
        return Services.getInnService();
    }

}
