package com.dungeonstory.form;

import org.vaadin.viritin.fields.CaptionGenerator;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SkillForm extends DSAbstractForm<Skill> {

    private static final long serialVersionUID = -4123881637907722632L;

    private TextField            name;
    private TextField            shortDescription;
    private TextArea             description;
    private TypedSelect<Ability> keyAbility;

    private DataService<Ability, Long> abilityService = AbilityService.getInstance();

    public SkillForm() {
        super();
    }

    @Override
    public String toString() {
        return "Talents";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte").withFullWidth();
        description = new MTextArea("Description").withFullWidth();
        keyAbility = new TypedSelect<Ability>("Attribut clé", abilityService.findAll());
        keyAbility.setCaptionGenerator(new CaptionGenerator<Ability>() {

            private static final long serialVersionUID = -3188362153311215227L;

            @Override
            public String getCaption(Ability option) {
                return option.getName();
            }
        });

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(keyAbility);
        layout.addComponent(getToolbar());

        return layout;
    }
}
