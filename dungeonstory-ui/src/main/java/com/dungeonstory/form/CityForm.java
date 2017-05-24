package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CityForm extends DSAbstractForm<City> {

    private static final long serialVersionUID = -6970583377484285924L;

    private TextField           name;
    private ComboBox<Region> region;
    private TextField           shortDescription;
    private TextArea            description;

    private DataService<Region, Long> regionService;

    public CityForm() {
        super(City.class);
            regionService = Services.getRegionService();
    }

    @Override
    public String toString() {
        return "Villes";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        region = new ComboBox<Region>("Région", regionService.findAll());
        shortDescription = new MTextField("Description courte");
        description = new DSTextArea("Description").withFullWidth();

        layout.addComponent(name);
        layout.addComponent(region);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(getToolbar());

        return layout;
    }
}
