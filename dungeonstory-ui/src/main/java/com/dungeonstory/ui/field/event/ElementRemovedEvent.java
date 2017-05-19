package com.dungeonstory.ui.field.event;

import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

public class ElementRemovedEvent<ET> extends Event {

    private static final long serialVersionUID = 4496256479520602153L;

    private final ET element;

    public ElementRemovedEvent(Component source, ET element) {
        super(source);
        this.element = element;
    }

    public ET getElement() {
        return element;
    }

}
