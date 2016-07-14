package com.dungeonstory.client;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.checkbox.CheckBoxConnector;
import com.vaadin.shared.ui.Connect;
import com.dungeonstory.FormCheckBox;

@Connect(FormCheckBox.class)
public class FormCheckBoxConnector extends CheckBoxConnector {

    private static final long serialVersionUID = -6098217647009852897L;

    @Override
    public boolean delegateCaptionHandling() {
        return true;
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        this.getWidget().setText(null);
    }
}
