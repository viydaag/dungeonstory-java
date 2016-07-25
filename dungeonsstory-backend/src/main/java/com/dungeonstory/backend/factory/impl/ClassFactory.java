package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.factory.Factory;

public class ClassFactory implements Factory<DSClass> {

    public ClassFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public DSClass create() {
        return new DSClass();
    }

}
