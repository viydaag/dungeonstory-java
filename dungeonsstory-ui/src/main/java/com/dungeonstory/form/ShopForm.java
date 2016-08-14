package com.dungeonstory.form;

import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ShopForm extends DSAbstractForm<Shop> {

    private static final long serialVersionUID = -3696502123462994399L;

    private TextField                             name;
    private TextArea                              description;
    private ElementCollectionTable<ShopEquipment> shopEquipments;

    //    DataService<Equipment, Long> equipmentService = MockEquipmentService.getInstance();

    public static class ShopEquipmentRow {
        TypedSelect<Equipment> equipment = new TypedSelect<Equipment>();
        IntegerField           quantity  = new IntegerField();
        IntegerField           unitPrice = new IntegerField();
    }

    public ShopForm() {
        super();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();

        shopEquipments = new ElementCollectionTable<ShopEquipment>(ShopEquipment.class, ShopEquipmentRow.class)
                .withCaption("Équipement").withEditorInstantiator(() -> {
                    ShopEquipmentRow row = new ShopEquipmentRow();

                    //                    row.equipment.setOptions(equipmentService.findAll());
                    return row;
                });
        shopEquipments.setPropertyHeader("equipment", "Nom");
        shopEquipments.setPropertyHeader("quantity", "Quantité");
        shopEquipments.setPropertyHeader("unitPrice", "Prix à l'unité");

        layout.addComponent(name);
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
