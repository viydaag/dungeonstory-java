package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Feat;
import com.vaadin.data.provider.ListDataProvider;

public class FeatGrid extends DSGrid<Feat> {

    private static final long serialVersionUID = -6577254670865533975L;

    public FeatGrid() {
        super();
        addColumn(Feat::getName).setCaption("Nom").setId("name");
        addColumn(Feat::getUsage).setCaption("Usage").setId("usage");
        setDataProvider(new ListDataProvider<>(EnumSet.allOf(Feat.class)));
    }

}
