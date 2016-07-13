package com.dungeonstory.form;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AlignmentForm extends DSAbstractForm<Alignment> {

    private static final long serialVersionUID = -9195608720966852469L;
    
    private TextField name;
	private TextField shortDescription;
	private TextArea description;

	public AlignmentForm() {
	    super();
	}

	@Override
	public String toString() {
		return "Alignements";
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
