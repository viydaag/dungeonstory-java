package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Monster;

public class MonsterGrid extends DSGrid<Monster> {

    private static final long serialVersionUID = -3681509089262517034L;

    public MonsterGrid() {
        super();
        addColumn(Monster::getChallengeRating).setCaption("DD").setId("challengeRating");
        addColumn(Monster::getName).setCaption("Nom").setId("name");
    }

}
