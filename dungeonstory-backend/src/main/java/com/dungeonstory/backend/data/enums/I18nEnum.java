package com.dungeonstory.backend.data.enums;

public interface I18nEnum {

    default public String getKey(String name, String param) {
        //        Class<?> clazz = getClass();
        //        Class<?> zuper = clazz.getSuperclass();
        //        if (zuper == Enum.class) {
        //            return clazz.getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
        //        } else {
        //            return zuper.getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
        //        }

        if (getClass().isEnum()) {
            String enclosingClassName = "";
            if (getClass().getEnclosingClass() != null) {
                enclosingClassName = getClass().getEnclosingClass().getSimpleName().toLowerCase();
            }
            if (enclosingClassName.isEmpty()) {
                return getClass().getSimpleName().toLowerCase() + "." + name.toLowerCase() + "." + param;
            } else {
                return enclosingClassName + "." + getClass().getSimpleName().toLowerCase() + "." + name.toLowerCase()
                        + "." + param;
            }
        }
        return "";
    }

    public String getName();
}
