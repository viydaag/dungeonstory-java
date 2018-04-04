package com.dungeonstory.backend.data.enums;

public enum AccessRole {
    
    ADMIN("Administrateur"), MODERATOR("Modérateur"), PLAYER("Joueur");

    private String value;

    private AccessRole(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

}
