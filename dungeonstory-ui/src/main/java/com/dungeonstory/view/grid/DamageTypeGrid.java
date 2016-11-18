package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.DamageType;

public class DamageTypeGrid extends DSGrid<DamageType> {

    private static final long serialVersionUID = 1703049496748455895L;

    public DamageTypeGrid() {
        super(DamageType.class);
        setColumnOrder("name", "description");
    }

}
