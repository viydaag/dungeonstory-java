package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Level;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

public class LevelGrid extends DSGrid<Level> {

    private static final long serialVersionUID = -2219582474895040784L;

    public LevelGrid() {
        super(Level.class);

        setEditorEnabled(true);
        setEditorFieldGroup(new BeanFieldGroup<Level>(Level.class));

        addColumn("id");
        Column levelColumn = getColumn("id");
        levelColumn.setHeaderCaption("Niveau");
        setColumnOrder("id", "proficiencyBonus", "maxExperience");
    }

    public void addRow(Level level) {
        getContainerDataSource().addItem(level);
    }

}
