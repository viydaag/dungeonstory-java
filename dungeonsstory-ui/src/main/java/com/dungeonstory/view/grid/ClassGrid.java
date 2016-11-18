package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.DSClass;

public class ClassGrid extends DSGrid<DSClass> {

    private static final long serialVersionUID = -2219582474895040784L;

    public ClassGrid() {
        super(DSClass.class);
        withProperties("name", "shortDescription");
    }

}