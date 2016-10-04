package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.ClassSpecialization;

public class ClassSpecializationGrid extends BeanGrid<ClassSpecialization> {

    private static final long serialVersionUID = -2219582474895040784L;

    public ClassSpecializationGrid() {
        super(ClassSpecialization.class);
        getContainer().addNestedContainerBean("parentClass");
        withColumns("name", "parentClass.name");
        withHeaderCaption("Nom", "Classe");
    }

}
