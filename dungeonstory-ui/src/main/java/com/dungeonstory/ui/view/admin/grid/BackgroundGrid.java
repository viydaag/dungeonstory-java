package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Background;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.vaadin.data.ValueContext;
import com.vaadin.data.provider.ListDataProvider;

public class BackgroundGrid extends ReadOnlyGrid<Background> {

    private static final long serialVersionUID = 9205275025444235763L;

    public BackgroundGrid() {
        super();

        CollectionToStringConverter stringConverter = new CollectionToStringConverter();

        addColumn(Background::getName).setCaption("Nom").setId("name");
        addColumn(b -> stringConverter.convertToPresentation(b.getSkillProficiencies(), new ValueContext()))
                .setCaption("Maitrises de compétence")
                .setId("skillProficiencies");
        addColumn(b -> stringConverter.convertToPresentation(b.getToolProficiencies(), new ValueContext()))
                .setCaption("Maitrises d'outil")
                .setId("toolProficiencies");

        setDataProvider(new ListDataProvider<>(EnumSet.allOf(Background.class)));
    }

}
