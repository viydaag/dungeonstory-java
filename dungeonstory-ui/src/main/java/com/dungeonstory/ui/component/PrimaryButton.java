package com.dungeonstory.ui.component;

import com.vaadin.event.ShortcutAction;
import com.vaadin.fluent.api.FluentButton;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

public class PrimaryButton
        extends Button
        implements FluentButton<PrimaryButton> {
    
    private static final long serialVersionUID = -9056866634999220064L;

    public PrimaryButton() {
        setupPrimaryButton();
    }

    public PrimaryButton(String caption) {
        super(caption);
        setupPrimaryButton();
    }

    public PrimaryButton(String caption, ClickListener listener) {
        super(caption, listener);
        setupPrimaryButton();
    }

    public PrimaryButton(Resource icon, ClickListener listener) {
        super(icon);
        addClickListener(listener);
        setupPrimaryButton();
    }

    public PrimaryButton(Resource icon, String caption, ClickListener listener) {
        super(caption, icon);
        addClickListener(listener);
        setupPrimaryButton();
    }

    private void setupPrimaryButton() {
        setStyleName("primary default");
        setClickShortcut(ShortcutAction.KeyCode.ENTER, null);
    }

}
