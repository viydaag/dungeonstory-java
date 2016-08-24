package com.dungeonstory.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Race;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class RaceForm extends DSAbstractForm<Race> {

    private static final long serialVersionUID = 831509196632558212L;

    private TextField name;
    private TextField shortDescription;
    private TextArea  description;

    private IntegerField strModifier;
    private IntegerField dexModifier;
    private IntegerField conModifier;
    private IntegerField intModifier;
    private IntegerField wisModifier;
    private IntegerField chaModifier;

    private IntegerField minAge;
    private IntegerField maxAge;
    private TextField    ageModifier;
    private IntegerField averageHeight;
    private TextField    heightModifier;
    private IntegerField averageWeight;
    private TextField    weightModifier;

    public RaceForm() {
        super();
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

        strModifier = new IntegerField("Modificateur de force");
        dexModifier = new IntegerField("Modificateur de dextérité");
        conModifier = new IntegerField("Modificateur de constitution");
        intModifier = new IntegerField("Modificateur d'intelligence");
        wisModifier = new IntegerField("Modificateur de sagesse");
        chaModifier = new IntegerField("Modificateur de charisme");

        minAge = new IntegerField("Âge mimimum");
        maxAge = new IntegerField("Âge maximum");
        ageModifier = new MTextField("Modificateur d'âge");
        averageHeight = new IntegerField("Taille moyenne (en pieds/pouce)");
        heightModifier = new MTextField("Modificateur de taille (en pouce)");
        averageWeight = new IntegerField("Poids moyen (en lbs)");
        weightModifier = new MTextField("Modificateur de poids (en lbs)");

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);

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

        layout.addComponent(getToolbar());

        return layout;
    }

}
