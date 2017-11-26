package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassFeature.ClassFeatureUsage;
import com.dungeonstory.backend.data.ClassFeature.RestType;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.IntegerField;
import com.vaadin.fluent.ui.FComboBox;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ClassFeatureForm
        extends DSAbstractForm<ClassFeature> {

    private static final long serialVersionUID = 5217088283624359889L;

    private TextField                       name;
    private FTextArea                       description;
    private EnumComboBox<ClassFeatureUsage> usage;
    private ComboBox<ClassFeature>          parent;
    private ComboBox<Level>                 requiredLevel;
    private IntegerField                    nbUse;
    private EnumComboBox<RestType>          restType;
    private IntegerField                    pointCost;
    private ComboBox<ClassFeature>          replacement;

    private ClassFeatureDataService classFeatureService = null;

    public ClassFeatureForm() {
        super(ClassFeature.class);
        classFeatureService = Services.getClassFeatureService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom").withWidth(50, Unit.PERCENTAGE);
        description = new FTextArea("Description").withFullWidth().withRows(10);
        usage = new EnumComboBox<>(ClassFeatureUsage.class, "Usage");
        nbUse = new DSIntegerField("Nombre d'utilisation avant repos");
        restType = new EnumComboBox<RestType>(RestType.class, "Type de repos requis");
        pointCost = new DSIntegerField("Coût en points");
        parent = new FComboBox<ClassFeature>("Don parent").withWidth(50, Unit.PERCENTAGE);
        replacement = new FComboBox<ClassFeature>("Remplace le don").withWidth(50, Unit.PERCENTAGE);
        requiredLevel = new ComboBox<>("Niveau requis", Services.getLevelService().findAll());

        parent.addValueChangeListener(event -> {
            requiredLevel.setVisible(event.getValue() != null);
            if (event.getValue() == null) {
                requiredLevel.setValue(null);
            }
        });

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponents(nbUse, restType, pointCost);
        layout.addComponents(parent, requiredLevel, replacement);
        layout.addComponent(getToolbar());

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        if (getEntity() != null) {
            parent.setItems(classFeatureService.findAllClassFeaturesWithoutParent());
            parent.setValue(getEntity().getParent());
            replacement.setItems(classFeatureService.findAllClassFeatureExcept(getEntity()));
            replacement.setValue(getEntity().getReplacement());
        }
    }

    @Override
    public String toString() {
        return "Dons de classe";
    }

}
