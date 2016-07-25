package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.factory.Factory;

public class AlignmentFactory implements Factory<Alignment> {

    public AlignmentFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Alignment create() {
        return new Alignment();
    }

}
