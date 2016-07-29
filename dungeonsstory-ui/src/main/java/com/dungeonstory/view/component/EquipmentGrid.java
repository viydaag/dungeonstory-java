package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Equipment;

public class EquipmentGrid extends BeanGrid<Equipment> {

    private static final long serialVersionUID = 7341795764195713231L;

    public EquipmentGrid() {
		super(Equipment.class);
		withColumns("name", "description");
		withHeaderCaption("Nom", "Description");
	}

}
