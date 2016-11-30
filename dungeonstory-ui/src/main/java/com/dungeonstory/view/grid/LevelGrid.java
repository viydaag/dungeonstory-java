package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Level;

public class LevelGrid extends DSGrid<Level> {

    private static final long serialVersionUID = -2219582474895040784L;

    public LevelGrid() {
        super(Level.class);

        setEditorEnabled(true);

        withProperties("id", "proficiencyBonus", "maxExperience");
        withColumnHeaders("Niveau", "Bonus de maitrise", "Plafond d'expérience");
    }

    public void addRow(Level level) {
        getContainerDataSource().addItem(level);
    }

}
