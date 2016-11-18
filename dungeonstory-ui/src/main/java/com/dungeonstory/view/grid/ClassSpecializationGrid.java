package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.ClassSpecialization;

public class ClassSpecializationGrid extends DSGrid<ClassSpecialization> {

    private static final long serialVersionUID = -2219582474895040784L;

    public ClassSpecializationGrid() {
        super(ClassSpecialization.class);
        //        getContainer().addNestedContainerBean("parentClass");
        withProperties("name", "parentClass.name");
        withColumnHeaders("Nom", "Classe");
    }

}
