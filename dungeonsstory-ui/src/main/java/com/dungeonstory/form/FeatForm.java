package com.dungeonstory.form;

import java.util.Arrays;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.data.Feat.PrerequisiteType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class FeatForm extends DSAbstractForm<Feat> {

    private static final long serialVersionUID = 5697384706401456761L;

    private TextField                              name;
    private TextArea                               description;
    private EnumSelect<FeatUsage>                  usage;
    private FormCheckBox                           isClassFeature;
    private EnumSelect<PrerequisiteType>           prerequisiteType;
    private TypedSelect<ArmorType.ProficiencyType> prerequisiteArmorProficiency;
    private TypedSelect<Ability>                   prerequisiteAbility;
    private IntegerField                           prerequisiteAbilityScore;

    //    private FeatDataService            featService    = null;
    private DataService<Ability, Long> abilityService = null;

    public FeatForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            //            featService = MockFeatService.getInstance();
            abilityService = MockAbilityService.getInstance();
        } else {
            //            featService = FeatService.getInstance();
            abilityService = AbilityService.getInstance();
        }
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("50%");
        description = new MTextArea("Description").withFullWidth();
        usage = new EnumSelect<FeatUsage>("Usage");
        isClassFeature = new FormCheckBox("Don de classe");
        prerequisiteType = new EnumSelect<PrerequisiteType>("Type de prérequis");
        prerequisiteArmorProficiency = new TypedSelect<ArmorType.ProficiencyType>("Maitrise d'armure prérequise",
                Arrays.asList(ArmorType.ProficiencyType.values()));
        prerequisiteAbility = new TypedSelect<Ability>("Caractéristique prérequise", abilityService.findAll());
        prerequisiteAbilityScore = new IntegerField("Score de caractéristique");

        isClassFeature.addValueChangeListener(new ValueChangeListener() {

            private static final long serialVersionUID = -4194349047563258329L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                if (isClassFeature.getValue() == true) {
                    prerequisiteType.setValue(PrerequisiteType.NONE);
                    prerequisiteArmorProficiency.setValue(null);
                    prerequisiteAbility.setValue(null);
                    prerequisiteAbilityScore.setValue(null);
                    prerequisiteType.setVisible(false);
                    prerequisiteArmorProficiency.setVisible(false);
                    prerequisiteAbility.setVisible(false);
                    prerequisiteAbilityScore.setVisible(false);
                } else {
                    prerequisiteType.setVisible(true);
                    prerequisiteArmorProficiency.setVisible(true);
                    prerequisiteAbility.setVisible(true);
                    prerequisiteAbilityScore.setVisible(true);
                    adjustTypeVisibility(PrerequisiteType.NONE);
                }
            }
        });

        prerequisiteType.addMValueChangeListener(event -> adjustTypeVisibility((PrerequisiteType) event.getValue()));

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponent(isClassFeature);
        layout.addComponent(prerequisiteType);
        layout.addComponent(prerequisiteArmorProficiency);
        layout.addComponent(prerequisiteAbility);
        layout.addComponent(prerequisiteAbilityScore);
        layout.addComponent(getToolbar());

        return layout;
    }

    private void adjustTypeVisibility(PrerequisiteType type) {
        if (type != null) {
            switch (type) {
                case ARMOR_PROFICIENCY:
                    prerequisiteAbility.setValue(null);
                    prerequisiteAbilityScore.setValue(null);
                    prerequisiteAbility.setVisible(false);
                    prerequisiteAbilityScore.setVisible(false);
                    prerequisiteArmorProficiency.setVisible(true);
                    break;
                case ABILITY:
                    prerequisiteAbility.setVisible(true);
                    prerequisiteAbilityScore.setVisible(true);
                    prerequisiteArmorProficiency.setValue(null);
                    prerequisiteArmorProficiency.setVisible(false);
                    break;
                case NONE:
                default:
                    prerequisiteArmorProficiency.setValue(null);
                    prerequisiteAbility.setValue(null);
                    prerequisiteAbilityScore.setValue(null);
                    prerequisiteArmorProficiency.setVisible(false);
                    prerequisiteAbility.setVisible(false);
                    prerequisiteAbilityScore.setVisible(false);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Dons";
    }

}
