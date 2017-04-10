package com.dungeonstory.event;

import com.vaadin.navigator.View;

public class ViewAddedEvent {

    public enum ViewDestination {
        NAVIGATOR, NAVBAR, MENUBAR
    }

    private Class<? extends View> viewClass;
    private ViewDestination       destination;

    public ViewAddedEvent(Class<? extends View> viewClass) {
        this(viewClass, ViewDestination.NAVIGATOR);
    }

    public ViewAddedEvent(Class<? extends View> viewClass, ViewDestination destination) {
        this.viewClass = viewClass;
        this.destination = destination;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public ViewDestination getDestination() {
        return destination;
    }

}
