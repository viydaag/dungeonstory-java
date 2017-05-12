package com.dungeonstory.view.adventure;

import com.dungeonstory.backend.data.Message;
import com.dungeonstory.form.DSAbstractForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RichTextArea;

public class MessageForm extends DSAbstractForm<Message> {

	private static final long serialVersionUID = -1798357547943670210L;
	private RichTextArea text;

	@Override
	protected Component createContent() {
		FormLayout layout = new FormLayout();
		text = new RichTextArea("Message");
		text.setSizeFull();
		text.setNullRepresentation("");
		layout.addComponent(text);
		layout.addComponent(getToolbar());
		return layout;
	}

}
