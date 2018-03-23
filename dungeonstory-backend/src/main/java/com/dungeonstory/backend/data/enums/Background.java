package com.dungeonstory.backend.data.enums;

import java.util.EnumSet;
import java.util.Set;

import com.dungeonstory.backend.Labels;
import com.dungeonstory.backend.data.Tool.ToolType;

public enum Background implements I18nEnum {

    ACOLYTE(2, EnumSet.of(Skill.RELIGION, Skill.INSIGHT), EnumSet.noneOf(ToolType.class)),
    CHARLATAN(0, EnumSet.of(Skill.SLEIGHT_OF_HAND, Skill.DECEPTION), EnumSet.of(ToolType.FORGERY, ToolType.DISGUISE)),
    CRIMINAL(0, EnumSet.of(Skill.STEALTH, Skill.DECEPTION), EnumSet.of(ToolType.GAMING, ToolType.THIEVE)),
    ENTERTAINER(0, EnumSet.of(Skill.ACROBATICS, Skill.PERFORMANCE), EnumSet.of(ToolType.DISGUISE, ToolType.MUSICAL)),
    FOLK_HERO(0, EnumSet.of(Skill.ANIMAL_HANDLING, Skill.SURVIVAL), EnumSet.of(ToolType.ARTISAN, ToolType.VEHICLES)),
    GUILD_ARTISAN(1, EnumSet.of(Skill.PERSUASION, Skill.INSIGHT), EnumSet.of(ToolType.ARTISAN)),
    HERMIT(1, EnumSet.of(Skill.RELIGION, Skill.MEDICINE), EnumSet.of(ToolType.HERBALISM)),
    NOBLE(1, EnumSet.of(Skill.HISTORY, Skill.PERSUASION), EnumSet.of(ToolType.GAMING)),
    OUTLANDER(1, EnumSet.of(Skill.ATHLETICS, Skill.SURVIVAL), EnumSet.of(ToolType.MUSICAL)),
    SAGE(2, EnumSet.of(Skill.ARCANA, Skill.HISTORY), EnumSet.noneOf(ToolType.class)),
    SAILOR(0, EnumSet.of(Skill.ATHLETICS, Skill.PERCEPTION), EnumSet.of(ToolType.VEHICLES, ToolType.NAVIGATOR)),
    SOLDIER(0, EnumSet.of(Skill.ATHLETICS, Skill.INTIMIDATION), EnumSet.of(ToolType.VEHICLES, ToolType.GAMING)),
    URCHIN(0, EnumSet.of(Skill.SLEIGHT_OF_HAND, Skill.STEALTH), EnumSet.of(ToolType.DISGUISE, ToolType.THIEVE));

    private int           nbAdditionalLanguage;
    private Set<Skill>    skillProficiencies;
    private Set<ToolType> toolProficiencies;
    
    private Background(int nbAdditionalLanguage, Set<Skill> skillProficiencies, Set<ToolType> toolProficiencies) {
        this.nbAdditionalLanguage = nbAdditionalLanguage;
        this.skillProficiencies = skillProficiencies;
        this.toolProficiencies = toolProficiencies;
    }

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }

    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }

    public String getTraits() {
        return Labels.getInstance().getMessage(getKey(name(), "traits"));
    }

    public String getIdeals() {
        return Labels.getInstance().getMessage(getKey(name(), "ideals"));
    }

    public String getPurposes() {
        return Labels.getInstance().getMessage(getKey(name(), "purposes"));
    }

    public String getFlaws() {
        return Labels.getInstance().getMessage(getKey(name(), "flaws"));
    }

    public int getNbAdditionalLanguage() {
        return nbAdditionalLanguage;
    }

    public Set<Skill> getSkillProficiencies() {
        return EnumSet.copyOf(skillProficiencies);
    }

    public Set<ToolType> getToolProficiencies() {
        return EnumSet.copyOf(toolProficiencies);
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
