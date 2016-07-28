package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.FeatForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.FeatGrid;

@ViewConfig(uri = "feats", displayName = "Dons")
public class FeatView extends AbstractCrudView<Feat> {

    private static final long serialVersionUID = -6228189557239311489L;

    @Override
    public DSAbstractForm<Feat> getForm() {
        return new FeatForm();
    }

    @Override
    public BeanGrid<Feat> getGrid() {
        return new FeatGrid();
    }

    @Override
    public DataService<Feat, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockFeatService.getInstance();
        }
        return FeatService.getInstance();
    }

}
