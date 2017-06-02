package com.dungeonstory.ui.component;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

public class DSLabel extends Label {

    private static final long serialVersionUID = -5448155688464449028L;

    public DSLabel() {
        super();
    }

    public DSLabel(String text) {
        super(text);
    }

    public DSLabel(String text, ContentMode contentMode) {
        super(text, contentMode);
    }
    
    public DSLabel(String caption, String text) {
        super(text);
        setCaption(caption);
    }

}
