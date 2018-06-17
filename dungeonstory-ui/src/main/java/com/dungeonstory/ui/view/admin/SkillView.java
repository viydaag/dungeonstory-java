package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.SkillGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "skills", displayName = "Compétences")
public class SkillView extends VerticalLayout implements View {

    private static final long serialVersionUID = -7630758272011003929L;

    @Override
    public void enter(ViewChangeEvent event) {
        addComponent(new SkillGrid());
    }

}
