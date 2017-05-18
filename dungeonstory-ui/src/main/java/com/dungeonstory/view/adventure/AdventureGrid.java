package com.dungeonstory.view.adventure;

import java.io.Serializable;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.view.grid.DSGrid;

public class AdventureGrid extends DSGrid<Adventure> implements Serializable {

    private static final long serialVersionUID = 6231837698624838051L;

    public AdventureGrid() {
        super();

        addColumn(Adventure::getChallengeRating).setCaption("DD").setId("challengeRating");
        addColumn(Adventure::getName).setCaption("Titre").setId("name");
    }

}
