package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.data.Feat.PrerequisiteType;
import com.dungeonstory.backend.data.Feat.RestType;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class FeatForm extends DSAbstractForm<Feat> {

    private static final long serialVersionUID = 5697384706401456761L;

    private TextField                               name;
    private DSTextArea                              description;
    private EnumComboBox<FeatUsage>                 usage;
    private FormCheckBox                            isClassFeature;
    private EnumComboBox<PrerequisiteType>          prerequisiteType;
    private EnumComboBox<ArmorType.ProficiencyType> prerequisiteArmorProficiency;
    private ComboBox<Ability>                       prerequisiteAbility;
    private IntegerField                            prerequisiteAbilityScore;
    private ComboBox<Feat>                          parent;
    private IntegerField                            nbUse;
    private EnumComboBox<RestType>                  restType;
    private ComboBox<Feat>                          replacement;

    private AbilityDataService abilityService = null;
    private FeatDataService    featService    = null;

    public FeatForm() {
        super(Feat.class);
        featService = Services.getFeatService();
        abilityService = Services.getAbilityService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth(50, Unit.PERCENTAGE);
        description = new DSTextArea("Description").withFullWidth().withRows(10);
        usage = new EnumComboBox<FeatUsage>(FeatUsage.class, "Usage");
        nbUse = new IntegerField("Nombre d'utilisation avant repos");
        restType = new EnumComboBox<RestType>(RestType.class, "Type de repos requis");
        isClassFeature = new FormCheckBox("Don de classe");
        prerequisiteType = new EnumComboBox<PrerequisiteType>(PrerequisiteType.class, "Type de prérequis");
        prerequisiteArmorProficiency = new EnumComboBox<>(ArmorType.ProficiencyType.class,
                "Maitrise d'armure prérequise");
        prerequisiteAbility = new ComboBox<Ability>("Caractéristique prérequise", abilityService.findAll());
        prerequisiteAbilityScore = new IntegerField("Score de caractéristique");
        parent = new ComboBox<Feat>("Don parent", featService.findAllClassFeaturesWithoutParent());
        parent.setWidth(50, Unit.PERCENTAGE);
        replacement = new ComboBox<Feat>("Remplace le don");
        replacement.setWidth(50, Unit.PERCENTAGE);

        isClassFeature.addValueChangeListener(event -> isClassFeatureChange(event));

        prerequisiteType.addSelectionListener(event -> adjustTypeVisibility(event.getValue()));

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponent(isClassFeature);
        layout.addComponents(nbUse, restType);
        layout.addComponents(prerequisiteType, prerequisiteArmorProficiency, prerequisiteAbility,
                prerequisiteAbilityScore);
        layout.addComponents(parent, replacement);
        layout.addComponent(getToolbar());

        return layout;
    }

    private void isClassFeatureChange(ValueChangeEvent<Boolean> event) {
        if (isClassFeature.getValue() == true) {
            parent.setVisible(true);
            prerequisiteType.setValue(PrerequisiteType.NONE);
            prerequisiteArmorProficiency.setValue(null);
            prerequisiteAbility.setValue(null);
            prerequisiteAbilityScore.setValue(null);
            prerequisiteType.setVisible(false);
            prerequisiteArmorProficiency.setVisible(false);
            prerequisiteAbility.setVisible(false);
            prerequisiteAbilityScore.setVisible(false);
            nbUse.setVisible(true);
            restType.setVisible(true);
        } else {
            prerequisiteType.setVisible(true);
            prerequisiteArmorProficiency.setVisible(true);
            prerequisiteAbility.setVisible(true);
            prerequisiteAbilityScore.setVisible(true);
            adjustTypeVisibility(PrerequisiteType.NONE);
            parent.setValue(null);
            parent.setVisible(false);
            nbUse.setVisible(false);
            nbUse.setValue(null);
            restType.setVisible(false);
            restType.setValue(null);
        }
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
    public void afterSetEntity() {
        super.afterSetEntity();
        parent.setItems(featService.findAllClassFeaturesWithoutParent());
        replacement.setItems(featService.findAllClassFeatureExcept(getEntity()));
        isClassFeatureChange(null);
    }

    @Override
    public String toString() {
        return "Dons";
    }

}
