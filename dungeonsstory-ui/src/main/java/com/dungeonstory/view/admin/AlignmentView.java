package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.mock.MockAlignmentService;
import com.dungeonstory.form.AlignmentForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.AlignmentGrid;

@ViewConfig(uri = "alignments", displayName = "Alignements")
public class AlignmentView extends AbstractCrudView<Alignment> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Alignment> getForm() {
        return new AlignmentForm();
    }

    @Override
    public BeanGrid<Alignment> getGrid() {
        return new AlignmentGrid();
    }

    @Override
    public DataService<Alignment, Long> getDataService() {
        return MockAlignmentService.getInstance();
    }

}
