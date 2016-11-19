package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Ability;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AbilityForm extends DSAbstractForm<Ability> {

    private static final long serialVersionUID = -9195608720966852469L;

    private TextField name;
    private TextField abbreviation;
    private TextArea  description;

    public AbilityForm() {
        super();
    }

    @Override
    public String toString() {
        return "Caractéristique";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        abbreviation = new MTextField("Abbréviation");
        description = new MTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(abbreviation);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}