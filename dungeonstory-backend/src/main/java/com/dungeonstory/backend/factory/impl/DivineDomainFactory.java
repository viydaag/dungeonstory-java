package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.factory.Factory;

public class DivineDomainFactory implements Factory<DivineDomain> {

    private static final long serialVersionUID = -5273600369030215570L;

    @Override
    public DivineDomain create() {
        return new DivineDomain();
    }

}
