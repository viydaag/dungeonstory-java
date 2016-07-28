package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.form.AbilityForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.AbilityGrid;

@ViewConfig(uri = "abilities", displayName = "Capacités")
public class AbilityView extends AbstractCrudView<Ability> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Ability> getForm() {
        return new AbilityForm();
    }

    @Override
    public BeanGrid<Ability> getGrid() {
        return new AbilityGrid();
    }

    @Override
    public DataService<Ability, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockAbilityService.getInstance();
        }
        return AbilityService.getInstance();
    }

}
