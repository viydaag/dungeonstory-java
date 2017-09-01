package com.dungeonstory.ui.view.admin.form;


import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AlignmentForm extends DSAbstractForm<Alignment> {

    private static final long serialVersionUID = -9195608720966852469L;

    private TextField name;
    private TextField abbreviation;
    private TextField shortDescription;
    private TextArea  description;
    private FormCheckBox playable;

    public AlignmentForm() {
        super(Alignment.class);
    }

    @Override
    public String toString() {
        return "Alignements";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        abbreviation = new FTextField("Abbréviation");
        playable = new FormCheckBox("Jouable par un personnage");
        shortDescription = new FTextField("Description courte").withFullWidth();
        description = new FTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(abbreviation);
        layout.addComponent(playable);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
