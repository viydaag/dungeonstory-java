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
    
    public DSLabel(int text) {
        super(String.valueOf(text));
    }
    
    public DSLabel(Object object) {
        super(object.toString());
    }

    public DSLabel(String text, ContentMode contentMode) {
        super(text, contentMode);
    }
    
    public DSLabel(String caption, String text) {
        super(text);
        setCaption(caption);
    }
    
    public DSLabel(String caption, int text) {
        this(text);
        setCaption(caption);
    }
    
    public DSLabel(String caption, Object object) {
        this(object);
        setCaption(caption);
    }

    public DSLabel withFullWidth() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    public DSLabel withWidth(int width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    public DSLabel withStyleName(String... styles) {
        for (String styleName : styles) {
            addStyleName(styleName);
        }
        return this;
    }

    public DSLabel withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    public DSLabel withContent(String text) {
        setValue(text);
        return this;
    }

}
