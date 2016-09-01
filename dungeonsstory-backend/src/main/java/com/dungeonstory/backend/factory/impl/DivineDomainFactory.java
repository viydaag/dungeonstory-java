package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.factory.Factory;

public class DivineDomainFactory implements Factory<DivineDomain> {

    @Override
    public DivineDomain create() {
        return new DivineDomain();
    }

}
