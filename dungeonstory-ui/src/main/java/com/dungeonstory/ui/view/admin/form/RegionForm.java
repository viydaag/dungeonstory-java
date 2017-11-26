package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class RegionForm extends DSAbstractForm<Region> {

    private static final long serialVersionUID = 1416085344583485158L;

    private TextField name;
    private FTextArea description;

    public RegionForm() {
        super(Region.class);
    }

    @Override
    public String toString() {
        return "Regions";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        description = new FTextArea("Description").withFullWidth().withRows(10);

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
