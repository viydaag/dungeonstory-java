package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Skill implements I18nEnum {

    ATHLETICS(Ability.STRENGTH),
    ACROBATICS(Ability.DEXTERITY),
    SLEIGHT_OF_HAND(Ability.DEXTERITY),
    STEALTH(Ability.DEXTERITY),
    ARCANA(Ability.INTELLIGENCE),
    HISTORY(Ability.INTELLIGENCE),
    INVESTIGATION(Ability.INTELLIGENCE),
    NATURE(Ability.INTELLIGENCE),
    RELIGION(Ability.INTELLIGENCE),
    ANIMAL_HANDLING(Ability.WISDOM),
    INSIGHT(Ability.WISDOM),
    MEDICINE(Ability.WISDOM),
    PERCEPTION(Ability.WISDOM),
    SURVIVAL(Ability.WISDOM),
    DECEPTION(Ability.CHARISMA),
    INTIMIDATION(Ability.CHARISMA),
    PERFORMANCE(Ability.CHARISMA),
    PERSUASION(Ability.CHARISMA);

    private Ability keyAbility;

    private Skill(Ability keyAbility) {
        this.keyAbility = keyAbility;
    }
    
    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }
    
    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }

    public Ability getKeyAbility() {
        return keyAbility;
    }

}
