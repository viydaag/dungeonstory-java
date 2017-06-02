package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.ClassSpecialization;

public class ClassSpecializationGrid extends DSGrid<ClassSpecialization> {

    private static final long serialVersionUID = -2219582474895040784L;

    public ClassSpecializationGrid() {
        super();
        addColumn(ClassSpecialization::getName).setCaption("Nom").setId("name");
        addColumn(ClassSpecialization::getParentClass).setCaption("Classe").setId("parentClass");
    }

}
