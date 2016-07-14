package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Region;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class RegionForm extends DSAbstractForm<Region> {

    private static final long serialVersionUID = 1416085344583485158L;
    
    private TextField name;
	private TextField shortDescription;
	private TextArea description;

	public RegionForm() {
	    super();
	}

	@Override
	public String toString() {
		return "Regions";
	}

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();

		name = new MTextField("Nom");
		shortDescription = new MTextField("Description courte");
		description = new MTextArea("Description").withFullWidth();
		
		layout.addComponent(name);
		layout.addComponent(shortDescription);
		layout.addComponent(description);
		layout.addComponent(getToolbar());

		return layout;
	}
}
