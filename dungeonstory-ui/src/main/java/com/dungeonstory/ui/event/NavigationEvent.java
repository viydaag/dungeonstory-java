package com.dungeonstory.ui.event;


public class NavigationEvent {
    private String viewName;

    public NavigationEvent(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
