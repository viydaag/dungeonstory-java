package com.dungeonstory.ui.component;

import com.vaadin.fluent.api.FluentLabel;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

public class DSLabel
        extends Label
        implements FluentLabel<DSLabel> {

    private static final long serialVersionUID = -5448155688464449028L;

    public DSLabel() {
        super();
        init();
    }

    public DSLabel(String text) {
        super(text);
        init();
    }
    
    public DSLabel(int text) {
        super(String.valueOf(text));
        init();
    }
    
    public DSLabel(Object object) {
        super(object.toString());
        init();
    }

    public DSLabel(String text, ContentMode contentMode) {
        super(text, contentMode);
        init();
    }
    
    public DSLabel(String caption, String text) {
        super(text);
        setCaption(caption);
        init();
    }
    
    public DSLabel(String caption, int text) {
        this(text);
        setCaption(caption);
        init();
    }
    
    public DSLabel(String caption, Object object) {
        this(object);
        setCaption(caption);
    }

    public DSLabel withContent(String text) {
        setValue(text);
        init();
        return this;
    }

    private void init() {
        setWidth(100, Unit.PERCENTAGE);
    }

}
