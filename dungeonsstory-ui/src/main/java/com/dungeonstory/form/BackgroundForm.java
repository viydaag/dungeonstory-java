package com.dungeonstory.form;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.Background.LanguageChoice;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class BackgroundForm extends DSAbstractForm<Background> {

    private static final long serialVersionUID = -9195608720966852469L;

    private TextField name;
    private TextArea  description;
    private TextArea  traits;
    private TextArea  ideals;
    private TextArea  purposes;
    private TextArea  flaws;

    private DSSubSetSelector<Skill>    skillProficiencies;
    private DSSubSetSelector<ToolType> toolProficiencies;
    private EnumSelect<LanguageChoice> additionalLanguage;

    private DataService<Skill, Long> skillService     = null;

    public BackgroundForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            skillService = MockSkillService.getInstance();
        } else {
            skillService = SkillService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Background";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth().withRows(12);
        traits = new MTextArea("Traits").withFullWidth().withRows(12);
        ideals = new MTextArea("Idéaux").withFullWidth().withRows(12);
        purposes = new MTextArea("Buts").withFullWidth().withRows(12);
        flaws = new MTextArea("Défauts").withFullWidth().withRows(12);

        skillProficiencies = new DSSubSetSelector<Skill>(Skill.class);
        skillProficiencies.setCaption("Maitrise de compétence");
        skillProficiencies.setVisibleProperties("name", "keyAbility.name");
        skillProficiencies.setColumnHeader("name", "Compétence");
        skillProficiencies.setColumnHeader("keyAbility.name", "Caractéristique clé");
        skillProficiencies.setOptions((List<Skill>) skillService.findAll());
        skillProficiencies.setWidth("80%");
        skillProficiencies.setValue(null); //nothing selected

        toolProficiencies = new DSSubSetSelector<ToolType>(ToolType.class);
        toolProficiencies.setCaption("Maitrise d'outil");
        toolProficiencies.setVisibleProperties("name");
        toolProficiencies.setColumnHeader("name", "Outil");
        toolProficiencies.setOptions(Arrays.asList(ToolType.values()));
        toolProficiencies.setWidth("80%");
        toolProficiencies.setValue(new HashSet<ToolType>()); //nothing selected

        additionalLanguage = new EnumSelect<LanguageChoice>("Nb langage additionnel");

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(traits);
        layout.addComponent(ideals);
        layout.addComponent(purposes);
        layout.addComponent(flaws);
        layout.addComponent(skillProficiencies);
        layout.addComponent(toolProficiencies);
        layout.addComponent(additionalLanguage);
        layout.addComponent(getToolbar());

        return layout;
    }
}
