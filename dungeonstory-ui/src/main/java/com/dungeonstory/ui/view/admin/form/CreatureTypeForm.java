package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CreatureTypeForm extends DSAbstractForm<CreatureType> {

    private static final long serialVersionUID = 320942578847834986L;

    private TextField name;
    private TextArea  description;

    public CreatureTypeForm() {
        super(CreatureType.class);
    }

    @Override
    public String toString() {
        return "Type de créature";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("30%");
        description = new DSTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
