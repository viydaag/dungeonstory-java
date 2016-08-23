package com.dungeonstory.backend.data;

public enum Condition {
    
    BLINDED("Aveugle"),
    CHARMED("Charmé"),
    CURSED("Maudit"),
    DEAFENED("Sourd"),
    FRIGHTENED("Effrayé"),
    GRAPPLED("Saisi"),
    INCAPACITATED("Incapable"),
    INVISIBLE("Invisible"),
    MUTED("Muet"),
    PARALYZED("Paralysé"),
    PETRIFIED("Pétrifié"),
    POISONED("Empoisonné"),
    PRONE("Couché"),
    RESTRAINED("Restreint"),
    STUNNED("Assommé"),
    UNCONSCIOUS("Inconscient");
    
    private String name;
    
    private Condition(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

}
