package com.dungeonstory.ui.field.event;

import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

public class ElementAddedEvent<ET> extends Event {

    private static final long serialVersionUID = 6676753598951537806L;

    private final ET element;

    public ElementAddedEvent(Component source, ET element) {
        super(source);
        this.element = element;
    }

    public ET getElement() {
        return element;
    }

}
