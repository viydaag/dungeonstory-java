package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Level;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

public class LevelGrid extends BeanGrid<Level> {

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
