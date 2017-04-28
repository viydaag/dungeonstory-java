package com.dungeonstory.view.adventure;

import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(displayName="", uri=AdventureView.URI)
public class AdventureView extends VerticalLayout implements View {
	
	public static final String URI = "adventure";

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
