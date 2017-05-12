package com.dungeonstory.view.adventure;

import java.io.Serializable;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.data.Item;
import com.vaadin.data.util.PropertyValueGenerator;

public class AdventureGrid extends DSGrid<Adventure> implements Serializable {

    private static final long serialVersionUID = 6231837698624838051L;

    public AdventureGrid() {
        super(Adventure.class);

        withProperties("challengeRating", "name");
        withColumnHeaders("DD", "Titre");

        withGeneratedColumn("join", new PropertyValueGenerator<String>() {

            private static final long serialVersionUID = 3144410945479362965L;

            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Joindre";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });
    }

}
