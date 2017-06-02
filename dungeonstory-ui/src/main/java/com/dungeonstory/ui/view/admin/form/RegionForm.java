package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class RegionForm extends DSAbstractForm<Region> {

    private static final long serialVersionUID = 1416085344583485158L;

    private TextField name;
    private DSTextArea description;

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

        name = new MTextField("Nom");
        description = new DSTextArea("Description").withFullWidth().withRows(10);

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
