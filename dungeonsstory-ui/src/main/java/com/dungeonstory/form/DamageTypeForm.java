package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.DamageType;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DamageTypeForm extends DSAbstractForm<DamageType> {

    private static final long serialVersionUID = 1416085344583485158L;

    private TextField name;
    private TextArea  description;

    public DamageTypeForm() {
        super();
    }

    @Override
    public String toString() {
        return "Types de dommage";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
