package com.dungeonstory.ui.view.admin.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.fluent.ui.FComboBox;
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
        FComboBox<Equipment> equipment = new FComboBox<Equipment>().withEmptySelectionAllowed(false).withWidth(100, Unit.PERCENTAGE);
        IntegerField         quantity  = new IntegerField();
        IntegerField         unitPrice = new IntegerField();
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

        shopEquipments = new ElementCollectionGrid<>(ShopEquipment.class, ShopEquipmentRow.class).withCaption("Équipement")
                .withEditorInstantiator(() -> {
                    ShopEquipmentRow row = new ShopEquipmentRow();
                    List<Equipment> currentEquipment = new ArrayList<Equipment>();
                    if (shopEquipments.getValue() != null) {
                        currentEquipment = shopEquipments.getValue().stream().map(ShopEquipment::getEquipment).collect(Collectors.toList());
                    }
                    List<Equipment> purchasableEquipment = equipmentService.findAllPurchasable();
                    purchasableEquipment.removeAll(currentEquipment);
                    row.equipment.setItems(purchasableEquipment);
                    row.equipment.addValueChangeListener(event -> row.unitPrice.setValue(event.getValue().getBasePrice()));
                    return row;
                });
        shopEquipments.setPropertyHeader("equipment", "Nom");
        shopEquipments.setPropertyHeader("quantity", "Quantité");
        shopEquipments.setPropertyHeader("unitPrice", "Prix à l'unité");

        getBinder().forMemberField(shopEquipments).withValidator((value, context) -> shopEquipments.isValid()).withValidator(checkDuplicateItems());

        layout.addComponent(name);
        layout.addComponent(city);
        layout.addComponent(description);
        layout.addComponent(shopEquipments);
        layout.addComponent(getToolbar());

        return layout;
    }

    private Validator<? super List<ShopEquipment>> checkDuplicateItems() {
        return (value, context) -> {
            Set<Equipment> allItems = new HashSet<>();
            Optional<Equipment> duplicate = value.stream()
                                                 .map(ShopEquipment::getEquipment)
                                                 .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was already in the set.
                                                 .findFirst();

            if (duplicate.isPresent()) {
                return ValidationResult.error("Un item se retrouve 2 fois dans la liste");
            }
            return ValidationResult.ok();
        };
    }

    @Override
    public String toString() {
        return "Magasins";
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        shopEquipments.clearStatusLabel();
    }

}
