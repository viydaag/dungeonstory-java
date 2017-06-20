package com.dungeonstory.backend.data;

public enum Condition {
    
    BLINDED("Aveugle"),
    CHARMED("Charmé"),
    CURSED("Maudit"),
    DEAFENED("Sourd"),
    FRIGHTENED("Effrayé"),
    GRAPPLED("Aggripé"),
    INCAPACITATED("Incapable"),
    INVISIBLE("Invisible"),
    MUTED("Muet"),
    PARALYZED("Paralysé"),
    PETRIFIED("Pétrifié"),
    POISONED("Empoisonné"),
    PRONE("À terre"),
    RESTRAINED("Entravé"),
    SLEEP("Endormi"),
    STUNNED("Étourdi"),
    UNCONSCIOUS("Inconscient");
    
    private String name;
    
    private Condition(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
