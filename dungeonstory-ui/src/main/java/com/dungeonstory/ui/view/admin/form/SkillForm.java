package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SkillForm extends DSAbstractForm<Skill> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField         name;
    private MTextField        shortDescription;
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

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte").withFullWidth();
        description = new DSTextArea("Description").withFullWidth();
        keyAbility = new ComboBox<Ability>("Attribut clé", Services.getAbilityService().findAll());

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(keyAbility);
        layout.addComponent(getToolbar());

        return layout;
    }
}
