package com.dungeonstory.ui.view.admin.form;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.Background.LanguageChoice;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DSSubSetSelector2;
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

    private DSSubSetSelector2<Skill, Set<Skill>>       skillProficiencies;
    private DSSubSetSelector2<ToolType, Set<ToolType>> toolProficiencies;
    private EnumComboBox<LanguageChoice>               additionalLanguage;

    private DataService<Skill, Long> skillService = null;

    public BackgroundForm() {
        super(Background.class);
        skillService = Services.getSkillService();
    }

    @Override
    public String toString() {
        return "Background";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new TextField("Nom");
        description = new DSTextArea("Description").withFullWidth().withRows(12);
        traits = new DSTextArea("Traits de personnalité").withFullWidth().withRows(12);
        ideals = new DSTextArea("Idéaux").withFullWidth().withRows(12);
        purposes = new DSTextArea("Buts").withFullWidth().withRows(12);
        flaws = new DSTextArea("Défauts").withFullWidth().withRows(12);

        skillProficiencies = new DSSubSetSelector2<>(Skill.class);
        skillProficiencies.setCaption("Maitrise de compétence");
        //        skillProficiencies.setVisibleProperties("name", "keyAbility.name");
        //        skillProficiencies.setColumnHeader("name", "Compétence");
        //        skillProficiencies.setColumnHeader("keyAbility.name", "Caractéristique clé");
        //        skillProficiencies.setOptions(skillService.findAll());
        skillProficiencies.getGrid().addColumn(Skill::getName).setCaption("Compétence").setId("name");
        skillProficiencies.getGrid().addColumn(Skill::getKeyAbility).setCaption("Caractéristique clé").setId("keyAbility.name");
        skillProficiencies.getGrid().setColumnOrder("name", "keyAbility.name");
        skillProficiencies.setItems(skillService.findAll());
        skillProficiencies.setWidth("80%");
        skillProficiencies.setValue(null); //nothing selected

        toolProficiencies = new DSSubSetSelector2<>(ToolType.class);
        toolProficiencies.setCaption("Maitrise d'outil");
        //        toolProficiencies.setVisibleProperties("name");
        //        toolProficiencies.setColumnHeader("name", "Outil");
        toolProficiencies.getGrid().addColumn(ToolType::getName).setCaption("Outil").setId("name");
        toolProficiencies.getGrid().setColumnOrder("name");
        toolProficiencies.setItems(Arrays.asList(ToolType.values()));
        toolProficiencies.setWidth("80%");
        toolProficiencies.setValue(new HashSet<ToolType>()); //nothing selected

        additionalLanguage = new EnumComboBox<>(LanguageChoice.class, "Nb langage additionnel");

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
