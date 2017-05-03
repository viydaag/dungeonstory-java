package com.dungeonstory.view.grid;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.util.field.LongField;

public class LevelGrid extends DSGrid<Level> {

    private static final long serialVersionUID = -2219582474895040784L;

    public LevelGrid() {
        super();

        getEditor().setEnabled(true);

        addColumn(Level::getId).setCaption("Niveau");
        addColumn(Level::getProficiencyBonus).setCaption("Bonus de maitrise").setEditorComponent(new IntegerField(), Level::setProficiencyBonus);
        addColumn(Level::getMaxExperience).setCaption("Plafond d'expérience").setEditorComponent(new LongField(), Level::setMaxExperience);
    }
}
