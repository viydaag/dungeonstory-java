package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.LanguageGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "languages", displayName = "Langages")
public class LanguageView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1727374101226087197L;

    public LanguageView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        addComponent(new LanguageGrid());
    }

}
