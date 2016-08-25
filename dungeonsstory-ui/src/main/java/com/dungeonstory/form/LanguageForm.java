package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Language;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class LanguageForm extends DSAbstractForm<Language> {

    private static final long serialVersionUID = -1048322975026323293L;
    
    private TextField name;
    private TextField script;

    public LanguageForm() {
        super();
    }

    @Override
    public String toString() {
        return "Langages";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        script = new MTextField("Alphabet");

        layout.addComponent(name);
        layout.addComponent(script);
        layout.addComponent(getToolbar());

        return layout;
    }
}
