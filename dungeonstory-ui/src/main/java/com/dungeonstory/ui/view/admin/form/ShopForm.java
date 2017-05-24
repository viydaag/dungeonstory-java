package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.v7.fields.ElementCollectionTable;
import org.vaadin.viritin.v7.fields.TypedSelect;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.CityDataService;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ShopForm extends DSAbstractForm<Shop> {

    private static final long serialVersionUID = -3696502123462994399L;

    private TextField                             name;
    private TextArea                              description;
    private ComboBox<City>                        city;
    private ElementCollectionTable<ShopEquipment> shopEquipments;

    private EquipmentDataService equipmentService = null;
    private CityDataService      cityService      = null;

    public static class ShopEquipmentRow {
        TypedSelect<Equipment> equipment = new TypedSelect<Equipment>(Equipment.class);
        IntegerField           quantity  = new IntegerField();
        IntegerField           unitPrice = new IntegerField();
    }

    public ShopForm() {
        super(Shop.class);
        equipmentService = Services.getEquipmentService();
        cityService = Services.getCityService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new DSTextArea("Description").withFullWidth();
        city = new ComboBox<City>("Ville", cityService.findAll());

        shopEquipments = new ElementCollectionTable<ShopEquipment>(ShopEquipment.class, ShopEquipmentRow.class)
                .withCaption("Équipement").withEditorInstantiator(() -> {
                    ShopEquipmentRow row = new ShopEquipmentRow();
                    row.equipment.setOptions(equipmentService.findAllPurchasable());
                    return row;
                });
        shopEquipments.setPropertyHeader("equipment", "Nom");
        shopEquipments.setPropertyHeader("quantity", "Quantité");
        shopEquipments.setPropertyHeader("unitPrice", "Prix à l'unité");

        layout.addComponent(name);
        layout.addComponent(city);
        layout.addComponent(description);
        layout.addComponent(shopEquipments);
        layout.addComponent(getToolbar());

        return layout;
    }

    @Override
    public String toString() {
        return "Magasins";
    }

}
