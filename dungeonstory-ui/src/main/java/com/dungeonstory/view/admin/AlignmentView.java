package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.AlignmentForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.AlignmentGrid;
import com.dungeonstory.view.grid.DSGrid;

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
