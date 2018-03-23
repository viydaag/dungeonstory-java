package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.AbilityGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "abilities", displayName = "Caractéristiques")
public class AbilityView extends VerticalLayout implements View  {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public void enter(ViewChangeEvent event) {
        addComponent(new AbilityGrid());
    }

}
