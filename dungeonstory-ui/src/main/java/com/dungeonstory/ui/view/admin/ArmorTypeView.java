package com.dungeonstory.ui.view.admin;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.ArmorTypeGrid;
import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "armorTypes", displayName = "Types d'armure")
public class ArmorTypeView extends Composite implements View {

    private static final long serialVersionUID = 4239959044896030062L;
    
    public ArmorTypeView() {
        VerticalLayout layout = new VerticalLayout(new ArmorTypeGrid());
        setCompositionRoot(layout);
    }

}
