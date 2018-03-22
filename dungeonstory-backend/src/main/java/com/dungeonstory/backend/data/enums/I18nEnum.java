package com.dungeonstory.backend.data.enums;

public interface I18nEnum {
    
    default public String getKey(String name, String param) {
        return getClass().getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
    }
    
    public String getName();
}
