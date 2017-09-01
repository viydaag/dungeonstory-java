package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.data.Feat.PrerequisiteType;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class FeatForm extends DSAbstractForm<Feat> {

    private static final long serialVersionUID = 5697384706401456761L;

    private TextField                               name;
    private FTextArea                              description;
    private EnumComboBox<FeatUsage>                 usage;
    private EnumComboBox<PrerequisiteType>          prerequisiteType;
    private EnumComboBox<ArmorType.ProficiencyType> prerequisiteArmorProficiency;
    private ComboBox<Ability>                       prerequisiteAbility;
    private IntegerField                            prerequisiteAbilityScore;

    private AbilityDataService abilityService = null;

    public FeatForm() {
        super(Feat.class);
        abilityService = Services.getAbilityService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom").withWidth(50, Unit.PERCENTAGE);
        description = new FTextArea("Description").withFullWidth().withRows(10);
        usage = new EnumComboBox<FeatUsage>(FeatUsage.class, "Usage");
        prerequisiteType = new EnumComboBox<PrerequisiteType>(PrerequisiteType.class, "Type de prérequis");
        prerequisiteArmorProficiency = new EnumComboBox<>(ArmorType.ProficiencyType.class,
                "Maitrise d'armure prérequise");
        prerequisiteAbility = new ComboBox<Ability>("Caractéristique prérequise", abilityService.findAll());
        prerequisiteAbilityScore = new IntegerField("Score de caractéristique");

        prerequisiteType.addSelectionListener(event -> adjustTypeVisibility(event.getValue()));

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponents(prerequisiteType, prerequisiteArmorProficiency, prerequisiteAbility,
                prerequisiteAbilityScore);
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
