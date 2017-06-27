package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SkillDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.SkillForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.SkillGrid;

@ViewConfig(uri = "skills", displayName = "Compétences")
public class SkillView extends AbstractCrudView<Skill> {

    private static final long serialVersionUID = -7630758272011003929L;

    public SkillView() {
        setSortInMemory(true);
    }

    @Override
    public DSAbstractForm<Skill> getForm() {
        return new SkillForm();
    }

    @Override
    public DSGrid<Skill> getGrid() {
        return new SkillGrid();
    }

    @Override
    public SkillDataService getDataService() {
        return Services.getSkillService();
    }

}
