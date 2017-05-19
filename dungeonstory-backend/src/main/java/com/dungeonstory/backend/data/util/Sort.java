package com.dungeonstory.backend.data.util;

public class Sort {

    public enum SortOrder {
        ASC, DESC;
    }

    private String    property;
    private SortOrder order;

    public Sort(String property, SortOrder order) {
        setProperty(property);
        setOrder(order);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }

}
