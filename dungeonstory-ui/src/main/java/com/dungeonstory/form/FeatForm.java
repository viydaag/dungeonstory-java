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
import com.dungeonstory.backend.data.Feat.RestType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.ComboBox;
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
    private TypedSelect<Feat>                      parent;
    private IntegerField                           nbUse;
    private EnumSelect<RestType>                   restType;
    private TypedSelect<Feat>                      replacement;

    private DataService<Ability, Long> abilityService = null;
    private FeatDataService            featService    = null;

    public FeatForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            featService = MockFeatService.getInstance();
            abilityService = MockAbilityService.getInstance();
        } else {
            featService = FeatService.getInstance();
            abilityService = AbilityService.getInstance();
        }
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("50%");
        description = new MTextArea("Description").withFullWidth().withRows(10);
        usage = new EnumSelect<FeatUsage>("Usage").withSelectType(ComboBox.class);
        nbUse = new IntegerField("Nombre d'utilisation avant repos");
        restType = new EnumSelect<RestType>("Type de repos requis").withSelectType(ComboBox.class);
        isClassFeature = new FormCheckBox("Don de classe");
        prerequisiteType = new EnumSelect<PrerequisiteType>("Type de prérequis");
        prerequisiteArmorProficiency = new TypedSelect<ArmorType.ProficiencyType>("Maitrise d'armure prérequise",
                Arrays.asList(ArmorType.ProficiencyType.values())).asComboBoxType();
        prerequisiteAbility = new TypedSelect<Ability>("Caractéristique prérequise", abilityService.findAll())
                .asComboBoxType();
        prerequisiteAbilityScore = new IntegerField("Score de caractéristique");
        parent = new TypedSelect<Feat>("Don parent", featService.findAllClassFeaturesWithoutParent()).asComboBoxType();
        replacement = new TypedSelect<Feat>("Remplace le don").asComboBoxType();

        isClassFeature.addValueChangeListener(event -> isClassFeatureChange(event));

        prerequisiteType.addMValueChangeListener(event -> adjustTypeVisibility((PrerequisiteType) event.getValue()));

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

    private void isClassFeatureChange(ValueChangeEvent event) {
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
        parent.setBeans(featService.findAllClassFeaturesWithoutParent());
        replacement.setBeans(featService.findAllClassFeatureExcept(getEntity()));
        isClassFeatureChange(null);
    }

    @Override
    public String toString() {
        return "Dons";
    }

}
