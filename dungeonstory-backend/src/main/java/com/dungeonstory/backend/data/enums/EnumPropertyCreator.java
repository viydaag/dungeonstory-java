package com.dungeonstory.backend.data.enums;

public class EnumPropertyCreator {

    public static void main(String[] args) {

        String enumName = args[0];
        String propertyName = args[1];
        
        try {
            Class<?> enumClass = Class.forName(enumName);
            
            if (enumClass.isEnum()) {
                Object[] enumConstants = enumClass.getEnumConstants();
                for (Object c : enumConstants) {
                    Enum<?> aEnum = (Enum<?>) c;
                    System.out.println(enumClass.getSimpleName().toLowerCase() + "." + aEnum.name().toLowerCase() + "." + propertyName + "=");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
