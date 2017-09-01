package com.dungeonstory.ui.view.admin.form;

import java.util.List;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.CityDataService;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ShopForm extends DSAbstractForm<Shop> {

    private static final long serialVersionUID = -3696502123462994399L;

    private TextField                            name;
    private TextArea                             description;
    private ComboBox<City>                       city;
    private ElementCollectionGrid<ShopEquipment> shopEquipments;

    private EquipmentDataService equipmentService = null;
    private CityDataService      cityService      = null;

    public static class ShopEquipmentRow {
        ComboBox<Equipment> equipment = new ComboBox<>();
        IntegerField        quantity  = new IntegerField();
        IntegerField        unitPrice = new IntegerField();
    }

    public ShopForm() {
        super(Shop.class);
        equipmentService = Services.getEquipmentService();
        cityService = Services.getCityService();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new FTextField("Nom");
        description = new FTextArea("Description").withFullWidth();
        city = new ComboBox<City>("Ville", cityService.findAll());

        List<Equipment> purchasableEquipment = equipmentService.findAllPurchasable();
        shopEquipments = new ElementCollectionGrid<>(ShopEquipment.class, ShopEquipmentRow.class).withCaption("Équipement")
                .withEditorInstantiator(() -> {
                    ShopEquipmentRow row = new ShopEquipmentRow();
                    row.equipment.setItems(purchasableEquipment);
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
