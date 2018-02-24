package com.dungeonstory.backend.data.enums;

public enum Skill2 implements I18nEnum {

    ATHLETICS("skill.athletics.name", "skill.athletics.description", Ability2.STRENGTH),
    ACROBATICS("skill.acrobatics.name", "skill.athletics.description", Ability2.DEXTERITY),
    SLEIGHT_OF_HAND("skill.sleightOfHand.name", "skill.sleightOfHand.description", Ability2.DEXTERITY),
    STEALTH("skill.stealth.name", "skill.stealth.description", Ability2.DEXTERITY),
    ARCANA("skill.arcana.name", "skill.arcana.description", Ability2.INTELLIGENCE),
    HISTORY("skill.history.name", "skill.history.description", Ability2.INTELLIGENCE),
    INVESTIGATION("skill.investigation.name", "skill.investigation.description", Ability2.INTELLIGENCE),
    NATURE("skill.nature.name", "skill.nature.description", Ability2.INTELLIGENCE),
    RELIGION("skill.religion.name", "skill.religion.description", Ability2.INTELLIGENCE),
    ANIMAL_HANDLING("skill.animalHandling.name", "skill.animalHandling.description", Ability2.WISDOM),
    INSIGHT("skill.insight.name", "skill.insight.description", Ability2.WISDOM),
    MEDICINE("skill.medicine.name", "skill.medicine.description", Ability2.WISDOM),
    PERCEPTION("skill.perception.name", "skill.perception.description", Ability2.WISDOM),
    SURVIVAL("skill.survival.name", "skill.survival.description", Ability2.WISDOM),
    DECEPTION("skill.deception.name", "skill.deception.description", Ability2.CHARISMA),
    INTIMIDATION("skill.intimidation.name", "skill.intimidation.description", Ability2.CHARISMA),
    PERFORMANCE("skill.performance.name", "skill.performance.description", Ability2.CHARISMA),
    PERSUASION("skill.persuasion.name", "skill.persuasion.description", Ability2.CHARISMA);

    private String   nameKey;
    private String   descriptionKey;
    private Ability2 keyAbility;

    private Skill2(String nameKey, String descriptionKey, Ability2 keyAbility) {
        this.nameKey = nameKey;
        this.descriptionKey = descriptionKey;
        this.keyAbility = keyAbility;
    }

    @Override
    public String getNameKey() {
        return nameKey;
    }

    @Override
    public String getDescriptionKey() {
        return descriptionKey;
    }

    public Ability2 getKeyAbility() {
        return keyAbility;
    }

}
