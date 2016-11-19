package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.DivineDomain;

public class DivineDomainGrid extends DSGrid<DivineDomain> {

    private static final long serialVersionUID = 1828782098286866129L;

    public DivineDomainGrid() {
        super(DivineDomain.class);
        withProperties("name");
        withColumnHeaders("Nom");
    }

}