package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.ClassFeatureForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.ClassFeatureGrid;
import com.vaadin.data.provider.GridSortOrder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@ViewConfig(uri = "classFeatures", displayName = "Dons de classe")
public class ClassFeatureView extends AbstractCrudView<ClassFeature> {

    private static final long serialVersionUID = -6704453741360095129L;

    @Override
    public DSAbstractForm<ClassFeature> getForm() {
        return new ClassFeatureForm();
    }

    @Override
    public DSGrid<ClassFeature> getGrid() {
        return new ClassFeatureGrid();
    }

    @Override
    public ClassFeatureDataService getDataService() {
        return Services.getClassFeatureService();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        //        grid.sort(Sort.by("isClassClassFeatureure", SortDirection.ASCENDING).then("name", SortDirection.ASCENDING));
        grid.setSortOrder(GridSortOrder.asc(grid.getColumn("name")));
    }

}
