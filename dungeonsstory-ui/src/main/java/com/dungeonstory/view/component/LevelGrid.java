package com.dungeonstory.view.component;

import java.util.ArrayList;

import com.dungeonstory.samples.backend.data.Level;
import com.dungeonstory.samples.crud.BeanGrid;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

public class LevelGrid extends BeanGrid<Level> {

	private static final long serialVersionUID = -2219582474895040784L;

	public LevelGrid(Class<Level> beanClass) {
		super(beanClass);
		
		setEditorEnabled(true);
		setEditorFieldGroup(new BeanFieldGroup<Level>(Level.class));
		
		addColumn("id");
		Column levelColumn = getColumn("id");
		levelColumn.setHeaderCaption("Niveau");
//		removeColumn("version");
		setColumnOrder("id", "proficiencyBonus", "maxExperience");
		
		Level level1 = new Level(1000L, 1);
		ArrayList<Level> data = new ArrayList<Level>();
		data.add(level1);
		setData(data);
	}
	
	public void addRow(Level level) {
//		addRow(new Object[] { level.getId(), level.getProficiencyBonus(), level.getMaxExperience() });
		getContainerDataSource().addItem(level);
	}

}
