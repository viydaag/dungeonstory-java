package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Monster;
import com.vaadin.server.SerializableComparator;

public class MonsterGrid extends DSGrid<Monster> {

    private static final long serialVersionUID = -3681509089262517034L;

    public MonsterGrid() {
        super();
        addColumn(Monster::getChallengeRating).setCaption("DD").setId("challengeRating").setComparator(new MonsterChallengeRatingComparator());
        addColumn(Monster::getName).setCaption("Nom").setId("name");
    }
    
    private class MonsterChallengeRatingComparator implements SerializableComparator<Monster> {

        private static final long serialVersionUID = 8469046599808870693L;

        @Override
        public int compare(Monster m1, Monster m2) {
            return Double.compare(m1.getChallengeRating().getDoubleValue(), m2.getChallengeRating().getDoubleValue());
        }
    }

}
