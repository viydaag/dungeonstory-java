package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class TempleForm extends DSAbstractForm<Temple> {

    private static final long serialVersionUID = -686895331834107475L;
    
    private TextField      name;
    private DSTextArea     description;
    private ComboBox<City> city;
    private ComboBox<Deity> deity;

    public TempleForm() {
        super(Temple.class);
    }

    @Override
    public String toString() {
        return "Auberges";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new TextField("Nom");
        description = new DSTextArea("Description").withFullWidth().withRows(10);
        city = new ComboBox<City>("Ville", Services.getCityService().findAll());
        deity = new ComboBox<Deity>("Dieu", Services.getDeityService().findAll());

        layout.addComponents(name, description, city, deity);
        layout.addComponent(getToolbar());

        return layout;
    }
}
