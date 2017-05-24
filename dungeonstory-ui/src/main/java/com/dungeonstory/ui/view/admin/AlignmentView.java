package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.AlignmentForm;
import com.dungeonstory.ui.view.admin.grid.AlignmentGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "alignments", displayName = "Alignements")
public class AlignmentView extends AbstractCrudView<Alignment> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Alignment> getForm() {
        return new AlignmentForm();
    }

    @Override
    public DSGrid<Alignment> getGrid() {
        return new AlignmentGrid();
    }

    @Override
    public AlignmentDataService getDataService() {
        return Services.getAlignmentService();
    }

}
