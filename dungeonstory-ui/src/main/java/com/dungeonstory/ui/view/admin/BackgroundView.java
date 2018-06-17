package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.BackgroundGrid;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "backgrounds", displayName = "Profils")
public class BackgroundView extends VerticalLayout implements View {

    private static final long serialVersionUID = 4239959044896030062L;

    public BackgroundView() {
        addComponent(new BackgroundGrid());
    }

}
