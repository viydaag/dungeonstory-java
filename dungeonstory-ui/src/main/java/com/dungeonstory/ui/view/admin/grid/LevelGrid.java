package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.LongField;
import com.vaadin.data.BeanValidationBinder;

public class LevelGrid extends DSGrid<Level> {

    private static final long serialVersionUID = -2219582474895040784L;

    public LevelGrid() {
        super(Level.class);

        getEditor().setEnabled(true);
        getEditor().setBinder(new BeanValidationBinder<>(Level.class));

        removeAllColumns();

        addColumn(Level::getId).setCaption("Niveau");
        addColumn("proficiencyBonus").setCaption("Bonus de maitrise").setEditorComponent(new DSIntegerField());
        addColumn("maxExperience").setCaption("Plafond d'expérience").setEditorComponent(new LongField());
    }
}
