package com.dungeonstory.view;

import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "about", displayName = "À propos")
public class AboutView extends VerticalLayout implements View {

    private static final long serialVersionUID = -2859906686491127658L;

    public static final String VIEW_NAME = "About";

    public AboutView() {
        CustomLayout aboutContent = new CustomLayout("aboutview");
        aboutContent.setStyleName(DSTheme.ABOUT_CONTENT);

        // you can add Vaadin components in predefined slots in the custom layout
        aboutContent.addComponent(new Label(
                FontAwesome.INFO_CIRCLE.getHtml() + " This application is using Vaadin " + Version.getFullVersion(),
                ContentMode.HTML), "info");

        setSizeFull();
        setStyleName(DSTheme.ABOUT_VIEW);

        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
