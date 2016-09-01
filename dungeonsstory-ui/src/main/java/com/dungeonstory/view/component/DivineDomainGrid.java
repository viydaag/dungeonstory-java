package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.DivineDomain;

public class DivineDomainGrid extends BeanGrid<DivineDomain> {

    private static final long serialVersionUID = 1828782098286866129L;

    public DivineDomainGrid() {
        super(DivineDomain.class);
        withColumns("name");
        withHeaderCaption("Nom");
    }

}
