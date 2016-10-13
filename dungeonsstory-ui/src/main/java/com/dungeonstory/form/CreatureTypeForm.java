package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.CreatureType;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CreatureTypeForm extends DSAbstractForm<CreatureType> {

    private static final long serialVersionUID = 320942578847834986L;

    private TextField name;
    private TextArea  description;

    public CreatureTypeForm() {
        super();
    }

    @Override
    public String toString() {
        return "Type de créature";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("30%");
        description = new MTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
