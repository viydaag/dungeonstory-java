package com.dungeonstory.event;

public class ViewRemovedEvent {

    private String viewName;

    public ViewRemovedEvent(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
