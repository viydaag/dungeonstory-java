package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class LanguageForm extends DSAbstractForm<Language> {

    private static final long serialVersionUID = -1048322975026323293L;
    
    private TextField name;
    private TextField script;
    private FormCheckBox playable;

    public LanguageForm() {
        super(Language.class);
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
        playable = new FormCheckBox("Jouable par un personnage");

        layout.addComponents(name, script, playable);
        layout.addComponent(getToolbar());

        return layout;
    }
}
