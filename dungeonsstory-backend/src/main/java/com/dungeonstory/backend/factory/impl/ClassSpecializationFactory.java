package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.factory.Factory;

public class ClassSpecializationFactory implements Factory<ClassSpecialization> {

    private static final long serialVersionUID = -5887091975689889885L;

    @Override
    public ClassSpecialization create() {
        return new ClassSpecialization();
    }

}
