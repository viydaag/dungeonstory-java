package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassFeature.ClassFeatureUsage;
import com.dungeonstory.backend.data.ClassFeature.RestType;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ClassFeatureForm extends DSAbstractForm<ClassFeature> {

    private static final long serialVersionUID = 5217088283624359889L;

    private TextField                       name;
    private DSTextArea                      description;
    private EnumComboBox<ClassFeatureUsage> usage;
    private ComboBox<ClassFeature>          parent;
    private IntegerField                    nbUse;
    private EnumComboBox<RestType>          restType;
    private ComboBox<ClassFeature>          replacement;

    private ClassFeatureDataService classFeatureService = null;

    public ClassFeatureForm() {
        super(ClassFeature.class);
        classFeatureService = Services.getClassFeatureService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth(50, Unit.PERCENTAGE);
        description = new DSTextArea("Description").withFullWidth().withRows(10);
        usage = new EnumComboBox<>(ClassFeatureUsage.class, "Usage");
        nbUse = new IntegerField("Nombre d'utilisation avant repos");
        restType = new EnumComboBox<RestType>(RestType.class, "Type de repos requis");
        parent = new ComboBox<>("Don parent", classFeatureService.findAllClassFeaturesWithoutParent());
        parent.setWidth(50, Unit.PERCENTAGE);
        replacement = new ComboBox<>("Remplace le don");
        replacement.setWidth(50, Unit.PERCENTAGE);

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponents(nbUse, restType);
        layout.addComponents(parent, replacement);
        layout.addComponent(getToolbar());

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        parent.setItems(classFeatureService.findAllClassFeaturesWithoutParent());
        replacement.setItems(classFeatureService.findAllClassFeatureExcept(getEntity()));
    }

    @Override
    public String toString() {
        return "Dons de classe";
    }

}