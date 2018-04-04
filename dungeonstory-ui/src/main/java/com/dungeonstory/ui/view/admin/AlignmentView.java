package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.AlignmentGrid;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "alignments", displayName = "Alignements")
public class AlignmentView extends VerticalLayout implements View {

    private static final long serialVersionUID = 4239959044896030062L;

    public AlignmentView() {
        addComponent(new AlignmentGrid());
    }

}
