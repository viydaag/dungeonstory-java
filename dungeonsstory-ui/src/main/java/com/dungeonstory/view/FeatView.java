package com.dungeonstory.view;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.form.FeatForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
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
        return MockFeatService.getInstance();
    }

}
