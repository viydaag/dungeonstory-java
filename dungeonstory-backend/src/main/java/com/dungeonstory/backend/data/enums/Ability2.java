package com.dungeonstory.backend.data.enums;

public enum Ability2 implements I18nEnum {
    
    STRENGTH("ability.str.caption", "ability.str.caption", "ability.str.abbr"),
    DEXTERITY("ability.dex.caption","ability.dex.caption", "ability.dex.abbr"),
    CONSTITUTION("ability.con.caption", "ability.con.caption", "ability.con.abbr"),
    INTELLIGENCE("ability.int.caption", "ability.int.caption", "ability.int.abbr"),
    WISDOM("ability.wis.caption", "ability.wis.caption", "ability.wis.abbr"),
    CHARISMA("ability.cha.caption", "ability.cha.caption", "ability.cha.abbr");
    
    private String nameKey;
    private String descriptionKey;
    private String abbreviationKey;
    
    private Ability2(String nameKey, String descriptionKey, String abbreviationKey) {
        this.nameKey = nameKey;
        this.descriptionKey = descriptionKey;
        this.abbreviationKey = abbreviationKey;
    }

    @Override
    public String getNameKey() {
        return nameKey;
    }

    @Override
    public String getDescriptionKey() {
        return descriptionKey;
    }    
    
    public String getAbbreviationKey() {
        return abbreviationKey;
    }

}
