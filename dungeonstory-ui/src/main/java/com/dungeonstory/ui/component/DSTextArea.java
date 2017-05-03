package com.dungeonstory.ui.component;

import com.vaadin.ui.TextArea;

public class DSTextArea extends TextArea {

    private static final long serialVersionUID = 1221256116012438205L;

    public DSTextArea() {
        super();
    }

    public DSTextArea(String caption) {
        super(caption);
    }

    public DSTextArea withFullWidth() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    public DSTextArea withWidth(String width) {
        setWidth(width);
        return this;
    }

    public DSTextArea withRows(int rows) {
        setRows(rows);
        return this;
    }

    

}
