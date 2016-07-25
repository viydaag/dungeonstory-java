package com.dungeonstory.view;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.SkillForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.SkillGrid;

@ViewConfig(uri = "skills", displayName = "Talents")
public class SkillView extends AbstractCrudView<Skill> {

	private static final long serialVersionUID = -7630758272011003929L;

    @Override
    public DSAbstractForm<Skill> getForm() {
        return new SkillForm();
    }

    @Override
    public BeanGrid<Skill> getGrid() {
        return new SkillGrid();
    }

    @Override
    public DataService<Skill, Long> getDataService() {
        return MockSkillService.getInstance();
    }


}
