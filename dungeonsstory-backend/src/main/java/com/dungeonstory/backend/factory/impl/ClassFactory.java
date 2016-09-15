package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.factory.Factory;

public class ClassFactory implements Factory<DSClass> {

    private static final long serialVersionUID = -8517198478577469444L;

    @Override
    public DSClass create() {
        return new DSClass();
    }

}
