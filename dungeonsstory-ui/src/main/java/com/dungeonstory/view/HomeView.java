package com.dungeonstory.view;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;

@ViewConfig(uri = "", displayName = "Home")
public class HomeView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = 114267808090423646L;

	public HomeView() {
        Label caption = new Label("Welcome, " + CurrentUser.get().getUsername());
//        Label description = new Label(
//                "This project contains a collection of tips and tricks that will hopefully make it easier and more fun for you to work with Vaadin. Please read the readme file at <a href='https://github.com/vaadin-marcus/vaadin-tips'>https://github.com/vaadin-marcus/vaadin-tips</a>.", ContentMode.HTML);

        addComponents(caption);

        caption.addStyleName(DSTheme.LABEL_HUGE);
//        description.addStyleName(DSTheme.LABEL_LARGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
