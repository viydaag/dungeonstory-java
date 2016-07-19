package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.samples.crud.BeanGrid;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;

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
		
//		Level level1 = new Level(1000L, 1);
//		ArrayList<Level> data = new ArrayList<Level>();
//		data.add(level1);
//		setData(data);
	}
	
	public void addRow(Level level) {
//		addRow(new Object[] { level.getId(), level.getProficiencyBonus(), level.getMaxExperience() });
		getContainerDataSource().addItem(level);
	}

}
