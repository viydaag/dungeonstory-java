package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DamageTypeForm extends DSAbstractForm<DamageType> {

    private static final long serialVersionUID = 1416085344583485158L;

    private TextField name;
    private TextArea  description;

    public DamageTypeForm() {
        super(DamageType.class);
    }

    @Override
    public String toString() {
        return "Types de dommage";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        description = new FTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}