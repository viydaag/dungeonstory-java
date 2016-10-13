package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.factory.Factory;

public class AlignmentFactory implements Factory<Alignment> {

    private static final long serialVersionUID = 3734867761271087846L;

    @Override
    public Alignment create() {
        return new Alignment();
    }

}
