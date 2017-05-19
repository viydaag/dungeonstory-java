package com.dungeonstory.ui.component;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

public class HtmlLabel extends Label {

    private static final long serialVersionUID = -80274893616744119L;

    public HtmlLabel(String content) {
        super(content, ContentMode.HTML);
    }

    public HtmlLabel(String caption, String content) {
        super(content, ContentMode.HTML);
        setCaption(caption);
    }


}
