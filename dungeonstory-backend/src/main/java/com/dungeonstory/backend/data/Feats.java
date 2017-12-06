package com.dungeonstory.backend.data;

public enum Feats {

    ACTOR(1L),
    ELEMENTAL_ADEPT_FIRE(3L),
    ELEMENTAL_ADEPT_COLD(4L),
    ELEMENTAL_ADEPT_LIGHTNING(5L),
    ELEMENTAL_ADEPT_THUNDER(6L),
    LIGHTLY_ARMORED(7L),
    MODERATELY_ARMORED(8L),
    HEAVILY_ARMORED(9L),
    ATHLETE(10L),
    SAVAGE_ATTACKER(11L), //Attaquant sauvage
    TAVERN_BRAWLER(12L), //Bagarreur de tavernes
    DUEL_WIELDER(13L),
    DEFENSIVE_DUELIST(14L), //Dueliste Défensif
    DURABLE(15L), //Résistant
    KEEN_MIND(16L), //Esprit vif
    CROSSBOW_EXPERT(17L), //Expert des arbalètes
    DUNGEON_DELVER(18L), //Explorateur de donjons
    CHARGER(19L),
    SKULKER(20L), //Furtif
    HEALER(21L), //Guérisseur
    SPELL_SNIPER(22L), //Lanceur de sorts d'attaque
    WAR_CASTER(23L), //Lanceur de sorts de bataille
    INSPIRING_LEADER(24L), //Leader inspirant
    LINGUIST(25L), //Linguiste
    GRAPPLER(26L), //Lutteur
    WEAPON_MASTER(27L), //Maitre d'armes 
    GREAT_WEAPON_MASTER(28L), //Maitre des armes à 2 mains
    POLEARM_MASTER(29L), //Maitre des armes d'hast 
    MEDIUM_ARMOR_MASTER(30L), //Maitre des armure intermédiaires
    HEAVY_ARMOR_MASTER(31L), // Maitre des armures lourdes
    SHIELD_MASTER(32L), //Maitre des boucliers
    MOBILE(33L), //Mobile
    OBSERVANT(34L), //Observateur
    MAGE_SLAYER(35L), //Pourfendeur de mage
    RESILIENT(36L), //Résistant'
    TOUGH(37L), //Robuste
    SENTINEL(38L), //Sentinelle
    SKILLED(39L), //Talentueux
    SHARPSHOOTER(40L), //Tireur d'élite
    ALERT(41L);

    //TODO
    //LUCKY
    //MAGIC_INITIATE
    //MARTIAL_ADEPT
    //MOUNTED_COMBATTANT

    private Long id;

    private Feats(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
