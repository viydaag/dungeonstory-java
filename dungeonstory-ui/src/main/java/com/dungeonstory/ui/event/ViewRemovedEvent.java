package com.dungeonstory.ui.event;

public class ViewRemovedEvent {

    private String viewName;

    public ViewRemovedEvent(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
