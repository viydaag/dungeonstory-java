package com.dungeonstory.ui.view;

import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "", displayName = "Home")
public class HomeView extends VerticalLayout implements View {

	private static final long serialVersionUID = 114267808090423646L;

	public HomeView() {
        Label caption = new Label("Welcome, " + CurrentUser.get().getUsername());

        caption.addStyleName(DSTheme.LABEL_HUGE);
        addComponents(caption);

        Button aboutViewButton = new Button("À propos", click -> EventBus.post(new NavigationEvent("about")));
        aboutViewButton.addStyleName(DSTheme.BUTTON_BORDERLESS);
        addComponent(aboutViewButton);

        Button sourceViewButton = new Button("Sources", click -> EventBus.post(new NavigationEvent("sources")));
        sourceViewButton.addStyleName(DSTheme.BUTTON_BORDERLESS);
        addComponent(sourceViewButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
