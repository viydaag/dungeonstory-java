package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Alignment;
import com.vaadin.data.provider.ListDataProvider;

public class AlignmentGrid extends ReadOnlyGrid<Alignment> {

    private static final long serialVersionUID = -6577254670865533975L;

    public AlignmentGrid() {
        super();
        addColumn(Alignment::getName).setCaption("Nom").setId("name");
        addColumn(Alignment::getAbbreviation).setCaption("Abbréviation").setId("abbreviation");
        addColumn(Alignment::getDescription).setCaption("Description").setId("description");
        
        setDataProvider(new ListDataProvider<>(EnumSet.allOf(Alignment.class)));
    }

}
