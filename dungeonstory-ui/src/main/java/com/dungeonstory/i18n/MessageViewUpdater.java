package com.dungeonstory.i18n;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

public class MessageViewUpdater implements ViewChangeListener {

    private static final long serialVersionUID = -1286678743159472768L;

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {

        View view = event.getNewView();
        if (view instanceof Translatable) {
            ((Translatable) view).updateMessageStrings();
        }

    }
}
