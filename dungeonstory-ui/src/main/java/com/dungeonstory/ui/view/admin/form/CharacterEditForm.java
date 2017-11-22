package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.field.LongField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class CharacterEditForm
        extends DSAbstractForm<Character> {

    private static final long serialVersionUID = -9195608720966852469L;

    private LongField experience;

    public CharacterEditForm() {
        super(Character.class);
    }

    @Override
    public String toString() {
        return "Caractéristique";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        experience = new LongField("Expérience");

        layout.addComponent(experience);
        layout.addComponent(getToolbar());

        return layout;
    }
}
