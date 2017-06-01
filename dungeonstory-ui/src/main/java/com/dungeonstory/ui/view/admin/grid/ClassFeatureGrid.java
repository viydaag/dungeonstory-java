package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.ClassFeature;

public class ClassFeatureGrid extends DSGrid<ClassFeature> {

    private static final long serialVersionUID = -6577254670865533975L;

    public ClassFeatureGrid() {
        super();
        addColumn(ClassFeature::getName).setCaption("Nom").setId("name");
        addColumn(ClassFeature::getUsage).setCaption("Usage").setId("usage");
    }

}
