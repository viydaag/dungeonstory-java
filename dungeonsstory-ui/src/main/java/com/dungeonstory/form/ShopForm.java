package com.dungeonstory.form;

import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.EquipmentDataService;
import com.dungeonstory.backend.service.impl.CityService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.mock.MockCityService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ShopForm extends DSAbstractForm<Shop> {

	private static final long serialVersionUID = -3696502123462994399L;

	private TextField							  name;
	private TextArea							  description;
	private TypedSelect<City>					  city;
	private ElementCollectionTable<ShopEquipment> shopEquipments;

	private EquipmentDataService	equipmentService = null;
	private DataService<City, Long>	cityService		 = null;

	public static class ShopEquipmentRow {
		TypedSelect<Equipment> equipment = new TypedSelect<Equipment>(Equipment.class);
		IntegerField		   quantity	 = new IntegerField();
		IntegerField		   unitPrice = new IntegerField();
	}

	public ShopForm() {
		super();
		if (Configuration.getInstance().isMock()) {
			equipmentService = MockEquipmentService.getInstance();
			cityService = MockCityService.getInstance();
		} else {
			equipmentService = EquipmentService.getInstance();
			cityService = CityService.getInstance();
		}
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		description = new MTextArea("Description").withFullWidth();
		city = new TypedSelect<City>("Ville", cityService.findAll());

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
