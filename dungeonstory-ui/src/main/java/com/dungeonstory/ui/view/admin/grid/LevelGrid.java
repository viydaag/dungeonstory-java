package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.LongField;

public class LevelGrid extends DSGrid<Level> {

    private static final long serialVersionUID = -2219582474895040784L;

    public LevelGrid() {
        super();

        getEditor().setEnabled(true);

        addColumn(Level::getId).setCaption("Niveau");
        addColumn(Level::getProficiencyBonus).setCaption("Bonus de maitrise").setEditorComponent(new DSIntegerField(), Level::setProficiencyBonus);
        addColumn(Level::getMaxExperience).setCaption("Plafond d'expérience").setEditorComponent(new LongField(), Level::setMaxExperience);
    }
}
