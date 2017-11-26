package com.dungeonstory.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * View shown when trying to navigate to a view that does not exist using
 * {@link com.vaadin.navigator.Navigator}.
 * 
 * 
 */
public class ErrorView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1350610251823871404L;
    
    private Label explanation;

    public ErrorView() {
        this("The view could not be found");
    }

    public ErrorView(String message) {
        Label header = new Label(message);
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);
        addComponent(explanation = new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(String.format(
                "You tried to navigate to a view ('%s') that does not exist.",
                event.getViewName()));
    }
}
