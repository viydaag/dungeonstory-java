package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Deity;

public class DeityGrid extends BeanGrid<Deity> {

    private static final long serialVersionUID = 2771142614538863505L;

    public DeityGrid() {
        super(Deity.class);
        withColumns("name", "alignment", "symbol");
        withHeaderCaption("Nom", "Alignement", "Symbole");
    }

}
