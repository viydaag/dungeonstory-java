package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.DamageTypeGrid;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "damageTypes", displayName = "Types de dommages")
public class DamageTypeView extends VerticalLayout implements View {

    private static final long serialVersionUID = 2197369530490492330L;

    public DamageTypeView() {
        super();
        addComponent(new DamageTypeGrid());
    }

}
