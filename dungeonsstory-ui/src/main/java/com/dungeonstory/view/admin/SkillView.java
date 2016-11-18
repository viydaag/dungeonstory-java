package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.SkillForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.SkillGrid;

@ViewConfig(uri = "skills", displayName = "Compétences")
public class SkillView extends AbstractCrudView<Skill> {

	private static final long serialVersionUID = -7630758272011003929L;

    @Override
    public DSAbstractForm<Skill> getForm() {
        return new SkillForm();
    }

    @Override
    public DSGrid<Skill> getGrid() {
        return new SkillGrid();
    }

    @Override
    public DataService<Skill, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockSkillService.getInstance();
        }
        return SkillService.getInstance();
    }


}
