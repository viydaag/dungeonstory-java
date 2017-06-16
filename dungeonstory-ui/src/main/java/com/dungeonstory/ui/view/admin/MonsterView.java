package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.service.MonsterDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.MonsterForm;
import com.dungeonstory.ui.view.admin.grid.MonsterGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "monsters", displayName = "Monstres")
public class MonsterView extends AbstractCrudView<Monster> {

    private static final long serialVersionUID = -4080616229674156134L;

    @Override
    public DSAbstractForm<Monster> getForm() {
        return new MonsterForm();
    }

    @Override
    public DSGrid<Monster> getGrid() {
        return new MonsterGrid();
    }

    @Override
    public MonsterDataService getDataService() {
        return Services.getMonsterService();
    }

}
