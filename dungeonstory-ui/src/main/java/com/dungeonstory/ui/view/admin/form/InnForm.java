package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class InnForm extends DSAbstractForm<Inn> {

    private static final long serialVersionUID = -4290194980205588830L;

    private TextField      name;
    private FTextArea     description;
    private ComboBox<City> city;

    public InnForm() {
        super(Inn.class);
    }

    @Override
    public String toString() {
        return "Auberges";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        description = new FTextArea("Description").withFullWidth().withRows(10);
        city = new ComboBox<City>("Ville", Services.getCityService().findAll());

        layout.addComponents(name, description, city);
        layout.addComponent(getToolbar());

        return layout;
    }
}
