package com.dungeonstory.form;

import java.util.List;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Race.Size;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class RaceForm extends DSAbstractForm<Race> {

    private static final long serialVersionUID = 831509196632558212L;

    private TextField name;
    private TextField shortDescription;
    private TextArea  description;

    private EnumSelect<Size> size;
    private DSSubSetSelector<Language> languages;

    private IntegerField strModifier;
    private IntegerField dexModifier;
    private IntegerField conModifier;
    private IntegerField intModifier;
    private IntegerField wisModifier;
    private IntegerField chaModifier;

    private IntegerField minAge;
    private IntegerField maxAge;
    private TextField    ageModifier;
    private TextField    averageHeight;
    private TextField    heightModifier;
    private IntegerField averageWeight;
    private TextField    weightModifier;
    
    private IntegerField speed;

    private DataService<Language, Long> languageService = null;

    public RaceForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            languageService = MockLanguageService.getInstance();
        } else {
            languageService = LanguageService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Races";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        shortDescription = new MTextField("Description courte");
        description = new MTextArea("Description").withFullWidth();

        languages = new DSSubSetSelector<Language>(Language.class);
        languages.setCaption("Langages de base");
        languages.setVisibleProperties("name");
        languages.setColumnHeader("name", "Langage");
        languages.setOptions((List<Language>) languageService.findAll());
        languages.setValue(null); // nothing selected
        languages.setWidth("50%");
        
        size = new EnumSelect<Size>("Type de grandeur");
        speed = new IntegerField("Vitesse de déplacement en 1 round (en pieds)");

        strModifier = new IntegerField("Modificateur de force");
        dexModifier = new IntegerField("Modificateur de dextérité");
        conModifier = new IntegerField("Modificateur de constitution");
        intModifier = new IntegerField("Modificateur d'intelligence");
        wisModifier = new IntegerField("Modificateur de sagesse");
        chaModifier = new IntegerField("Modificateur de charisme");

        minAge = new IntegerField("Âge mimimum");
        maxAge = new IntegerField("Âge maximum");
        ageModifier = new MTextField("Modificateur d'âge");
        averageHeight = new MTextField("Taille moyenne (en pieds/pouce)");
        heightModifier = new MTextField("Modificateur de taille (en pouce)");
        averageWeight = new IntegerField("Poids moyen (en lbs)");
        weightModifier = new MTextField("Modificateur de poids (en lbs)");
        
        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(size);
        layout.addComponent(languages);

        layout.addComponent(strModifier);
        layout.addComponent(dexModifier);
        layout.addComponent(conModifier);
        layout.addComponent(intModifier);
        layout.addComponent(wisModifier);
        layout.addComponent(chaModifier);

        layout.addComponent(minAge);
        layout.addComponent(maxAge);
        layout.addComponent(ageModifier);
        layout.addComponent(averageHeight);
        layout.addComponent(heightModifier);
        layout.addComponent(averageWeight);
        layout.addComponent(weightModifier);
        
        layout.addComponent(speed);

        layout.addComponent(getToolbar());

        return layout;
    }

}
