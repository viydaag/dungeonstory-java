package com.dungeonstory.form;

import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.samples.backend.data.Region;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class RegionForm extends AbstractForm<Region> {
	private TextField name;
	private TextField shortDescription;
	private TextArea description;

	public RegionForm() {

	}

	@Override
	public String toString() {
		return "Regions";
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new TextField("Nom");
		shortDescription = new TextField("Description courte");
		description = new TextArea("Description");

		layout.addComponent(name);
		layout.addComponent(shortDescription);
		layout.addComponent(description);
		layout.addComponent(getToolbar());

		return layout;
	}
}
