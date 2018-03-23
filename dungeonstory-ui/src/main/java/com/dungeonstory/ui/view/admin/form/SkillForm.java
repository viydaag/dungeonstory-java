package com.dungeonstory.ui.view.admin.form;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SkillForm extends DSAbstractForm<Skill> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField         name;
    private FTextField        shortDescription;
    private TextArea          description;
    private ComboBox<Ability> keyAbility;

    public SkillForm() {
        super(Skill.class);
    }

    @Override
    public String toString() {
        return "Compétence";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        shortDescription = new FTextField("Description courte").withFullWidth();
        description = new FTextArea("Description").withFullWidth();
        keyAbility = new ComboBox<Ability>("Attribut clé", EnumSet.allOf(Ability.class));

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(keyAbility);
        layout.addComponent(getToolbar());

        return layout;
    }
}
