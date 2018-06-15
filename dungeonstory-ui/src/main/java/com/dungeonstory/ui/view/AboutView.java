package com.dungeonstory.ui.view;

import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "about", displayName = "À propos")
public class AboutView extends Composite implements View {

    private static final long serialVersionUID = -2859906686491127658L;

    public static final String VIEW_NAME = "About";

    public AboutView() {
        VerticalLayout layout = new VerticalLayout();
        CustomLayout aboutContent = new CustomLayout("aboutview");
        aboutContent.setStyleName(DSTheme.ABOUT_CONTENT);

        // you can add Vaadin components in predefined slots in the custom layout
        aboutContent.addComponent(new Label(VaadinIcons.INFO_CIRCLE.getHtml()
                + Messages.getInstance().getMessage("aboutView.vaadinVersion.text") + " " +  Version.getFullVersion(),
                ContentMode.HTML), "info");

        layout.setSizeFull();
        layout.setStyleName(DSTheme.ABOUT_VIEW);

        layout.addComponent(aboutContent);
        layout.setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
    }

}
