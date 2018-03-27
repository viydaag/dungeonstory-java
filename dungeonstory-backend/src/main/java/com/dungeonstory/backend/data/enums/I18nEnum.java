package com.dungeonstory.backend.data.enums;

public interface I18nEnum {
    
    default public String getKey(String name, String param) {
        Class<?> clazz = getClass();
        Class<?> zuper = clazz.getSuperclass();
        if (zuper == Enum.class) {
            return clazz.getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
        } else {
            return zuper.getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
        }
    }
    
    public String getName();
}
