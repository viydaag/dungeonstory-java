package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;
import com.dungeonstory.backend.data.Character;

public enum Feat implements I18nEnum, CharacterOperation {

    ACTOR(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    },
    ELEMENTAL_ADEPT_FIRE(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    },
    ELEMENTAL_ADEPT_COLD(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    },
    ELEMENTAL_ADEPT_LIGHTNING(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    },
    ELEMENTAL_ADEPT_THUNDER(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    },
    LIGHTLY_ARMORED(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    },
    MODERATELY_ARMORED(FeatUsage.PASSIVE, PrerequisiteType.ARMOR_PROFICIENCY) {
        public void apply(Character character) {
            
        }
    },
    HEAVILY_ARMORED(FeatUsage.PASSIVE, PrerequisiteType.ARMOR_PROFICIENCY) {
        public void apply(Character character) {
            
        }
    },
    ATHLETE(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    },
    SAVAGE_ATTACKER(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Attaquant sauvage
    TAVERN_BRAWLER(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Bagarreur de tavernes
    DUEL_WIELDER(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    },
    DEFENSIVE_DUELIST(FeatUsage.REACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Dueliste Défensif
    DURABLE(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Résistant
    KEEN_MIND(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Esprit vif
    CROSSBOW_EXPERT(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Expert des arbalètes
    DUNGEON_DELVER(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Explorateur de donjons
    CHARGER(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    },
    SKULKER(FeatUsage.PASSIVE, PrerequisiteType.ABILITY) {
        public void apply(Character character) {
            
        }
    }, //Furtif
    HEALER(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Guérisseur
    SPELL_SNIPER(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    }, //Lanceur de sorts d'attaque
    WAR_CASTER(FeatUsage.PASSIVE, PrerequisiteType.CAST_SPELL) {
        public void apply(Character character) {
            
        }
    }, //Lanceur de sorts de bataille
    INSPIRING_LEADER(FeatUsage.ACTION, PrerequisiteType.ABILITY) {
        public void apply(Character character) {
            
        }
    }, //Leader inspirant
    LINGUIST(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Linguiste
    GRAPPLER(FeatUsage.ACTION, PrerequisiteType.ABILITY) {
        public void apply(Character character) {
            
        }
    }, //Lutteur
    WEAPON_MASTER(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Maitre d'armes 
    GREAT_WEAPON_MASTER(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Maitre des armes à 2 mains
    POLEARM_MASTER(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Maitre des armes d'hast 
    MEDIUM_ARMOR_MASTER(FeatUsage.PASSIVE, PrerequisiteType.ARMOR_PROFICIENCY) {
        public void apply(Character character) {
            
        }
    }, //Maitre des armure intermédiaires
    HEAVY_ARMOR_MASTER(FeatUsage.PASSIVE, PrerequisiteType.ARMOR_PROFICIENCY) {
        public void apply(Character character) {
            
        }
    }, // Maitre des armures lourdes
    SHIELD_MASTER(FeatUsage.ACTION, PrerequisiteType.ARMOR_PROFICIENCY) {
        public void apply(Character character) {
            
        }
    }, //Maitre des boucliers
    MOBILE(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Mobile
    OBSERVANT(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Observateur
    MAGE_SLAYER(FeatUsage.REACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Pourfendeur de mage
    RESILIENT(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Résistant'
    TOUGH(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Robuste
    SENTINEL(FeatUsage.ACTION, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Sentinelle
    SKILLED(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Talentueux
    SHARPSHOOTER(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }, //Tireur d'élite
    ALERT(FeatUsage.PASSIVE, PrerequisiteType.NONE) {
        public void apply(Character character) {
            
        }
    }; //Vigilant

    //TODO
    //LUCKY
    //MAGIC_INITIATE
    //MARTIAL_ADEPT
    //MOUNTED_COMBATTANT

    public enum FeatUsage {
        PASSIVE, ACTION, ACTION_BONUS, REACTION
    }

    public enum PrerequisiteType {
        NONE, ARMOR_PROFICIENCY, ABILITY, CAST_SPELL
    }

    private FeatUsage                 usage;
    private PrerequisiteType          prerequisiteType = PrerequisiteType.NONE;
    private ArmorType.ProficiencyType prerequisiteArmorProficiency;
    private Ability                   prerequisiteAbility;
    private Integer                   prerequisiteAbilityScore;

    private Feat(FeatUsage usage, PrerequisiteType prerequisiteType) {
        this.usage = usage;
        this.prerequisiteType = prerequisiteType;
    }

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }

    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }

    public FeatUsage getUsage() {
        return usage;
    }

    public PrerequisiteType getPrerequisiteType() {
        return prerequisiteType;
    }

    public ArmorType.ProficiencyType getPrerequisiteArmorProficiency() {
        return prerequisiteArmorProficiency;
    }

    public Ability getPrerequisiteAbility() {
        return prerequisiteAbility;
    }

    public Integer getPrerequisiteAbilityScore() {
        return prerequisiteAbilityScore;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
