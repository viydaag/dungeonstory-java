package com.dungeonstory.ui.event;

import com.vaadin.navigator.View;

public class ViewAddedEvent {

    public enum ViewDestination {
        NAVIGATOR, NAVBAR, MENUBAR
    }

    private Class<? extends View> viewClass;
    private ViewDestination       destination;
    private String                caption;
    private String                uri;

    public ViewAddedEvent(Class<? extends View> viewClass) {
        this(viewClass, ViewDestination.NAVIGATOR, null, null);
    }

    public ViewAddedEvent(Class<? extends View> viewClass, ViewDestination destination) {
        this(viewClass, destination, null, null);
    }

    public ViewAddedEvent(Class<? extends View> viewClass, ViewDestination destination, String caption) {
        this(viewClass, destination, caption, null);
    }
    
    public ViewAddedEvent(Class<? extends View> viewClass, ViewDestination destination, String caption, String uri) {
        this.viewClass = viewClass;
        this.destination = destination;
        this.caption = caption;
        this.uri = uri;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public ViewDestination getDestination() {
        return destination;
    }

    public String getCaption() {
        return caption;
    }

    public String getUri() {
        return uri;
    }

}
