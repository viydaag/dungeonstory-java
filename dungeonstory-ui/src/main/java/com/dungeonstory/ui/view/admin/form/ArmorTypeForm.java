package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.ArmorType.ProficiencyType;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DoubleField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ArmorTypeForm extends DSAbstractForm<ArmorType> {

    private static final long serialVersionUID = 5931167771082380104L;

    private TextField                   name;
    private TextArea                    description;
    private EnumComboBox<ProficiencyType> proficiencyType;
    private IntegerField                maxDexBonus;
    private IntegerField                baseArmorClass;
    private FormCheckBox                stealthDisavantage;
    private IntegerField                minStrength;
    private DoubleField                 baseWeight;
    private IntegerField                basePrice;

    public ArmorTypeForm() {
        super(ArmorType.class);
    }

    @Override
    public String toString() {
        return "Types d'armure";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new DSTextArea("Description").withFullWidth();
        proficiencyType = new EnumComboBox<>(ProficiencyType.class, "Type de maitrise");
        maxDexBonus = new IntegerField("Bonus de dextérité maximum");
        baseArmorClass = new IntegerField("Classe d'armure de base");
        stealthDisavantage = new FormCheckBox("Désavantage sur furtivité");
        minStrength = new IntegerField("Force minimum pour porter l'armure");
        baseWeight = new DoubleField("Poids de base (lbs)");
        basePrice = new IntegerField("Prix de base");

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(proficiencyType);
        layout.addComponent(maxDexBonus);
        layout.addComponent(baseArmorClass);
        layout.addComponent(stealthDisavantage);
        layout.addComponent(minStrength);
        layout.addComponent(baseWeight);
        layout.addComponent(basePrice);
        layout.addComponent(getToolbar());

        return layout;
    }

}
