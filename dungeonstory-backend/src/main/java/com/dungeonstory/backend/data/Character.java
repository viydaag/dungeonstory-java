package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;
import org.eclipse.persistence.annotations.PrivateOwned;

import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.enums.Alignment;
import com.dungeonstory.backend.data.enums.ArmorType;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.data.enums.Terrain;

@Entity
@Table(name = "DSCharacter")
@NamedQuery(name = Character.HAS_FEAT, query = "SELECT c FROM Character c JOIN c.feats f WHERE c.id = :characterId AND f = :feat")
public class Character extends AbstractTimestampEntity implements Serializable, HasStats {

    public static final String HAS_FEAT = "hasFeat";

    private static final long serialVersionUID = -967001655180847193L;

    public enum Gender {
        M("Homme", "male"), F("Femme", "female");

        private String name;
        private String imageDir;

        private Gender(String name, String imageDir) {
            this.name = name;
            this.imageDir = imageDir;
        }

        public String getName() {
            return this.name;
        }

        public String getImageDir() {
            return imageDir;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    @Column(name = "weight", nullable = false)
    private int weight;

    @NotNull
    @Pattern(regexp = "\\d+\\'(\\d+\\\")*")
    @Column(name = "height", nullable = false)
    private String height;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "userId", referencedColumnName = "id", updatable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "raceId", nullable = false, updatable = false)
    private Race race;

    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 9, fraction = 0)
    @Column(name = "experience", nullable = false)
    private long experience;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    @Column(name = "lifePoints", nullable = false)
    private int lifePoints;

    @NotNull
    @Min(value = 10)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "armorClass", nullable = false)
    private int armorClass;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "strength", nullable = false)
    private int strength;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "dexterity", nullable = false)
    private int dexterity;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "constitution", nullable = false)
    private int constitution;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "intelligence", nullable = false)
    private int intelligence;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "wisdom", nullable = false)
    private int wisdom;

    @NotNull
    @Min(value = 1)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "charisma", nullable = false)
    private int charisma;

    @OneToOne(mappedBy = "character", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, targetEntity = CharacterBackground.class)
    private CharacterBackground background;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "alignment", nullable = false)
    private Alignment alignment;

    @ManyToOne
    @JoinColumn(name = "regionId", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "adventureId", nullable = true)
    private Adventure adventure;

    @OneToMany(mappedBy = "character")
    private List<Message> messages;

    @OneToMany(mappedBy = "character", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE }, targetEntity = CharacterClass.class)
    @PrivateOwned
    private Set<CharacterClass> classes;

    @ElementCollection(targetClass = Feat.class)
    @CollectionTable(name = "CharacterFeat", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "feat", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Feat> feats;

    @ElementCollection(targetClass = Skill.class)
    @CollectionTable(name = "CharacterProficientSkill", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "skill", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Skill> skillProficiencies;

    @ManyToMany
    @JoinTable(name = "CharacterWeaponProficiencies", joinColumns = @JoinColumn(name = "characterId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "weaponTypeId", referencedColumnName = "id"))
    private Set<WeaponType> weaponProficiencies;

    @ElementCollection(targetClass = ArmorType.ProficiencyType.class)
    @CollectionTable(name = "CharacterArmorProficiencies", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "armorProficiency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ArmorType.ProficiencyType> armorProficiencies;

//    @ManyToMany
//    @JoinTable(name = "CharacterSavingThrowProficiencies", joinColumns = {
//            @JoinColumn(name = "characterId", referencedColumnName = "id") }, inverseJoinColumns = {
//                    @JoinColumn(name = "abilityId", referencedColumnName = "id") })
    @ElementCollection(targetClass = Ability.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CharacterSavingThrowProficiencies", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "ability", nullable = false)
    private Set<Ability> savingThrowProficiencies;

    @ElementCollection(targetClass = ToolType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CharacterToolProficiencies", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "toolType", nullable = false)
    private Set<ToolType> toolProficiencies;

    @OneToMany(mappedBy = "character", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @BatchFetch(value = BatchFetchType.JOIN)
    private Set<CharacterEquipment> equipment;

    @ManyToMany
    @JoinTable(name = "CharacterFavoredEnnemy", joinColumns = {
            @JoinColumn(name = "characterId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "creatureTypeId", referencedColumnName = "id") })
    private List<CreatureType> favoredEnnemies;

    @ElementCollection(targetClass = Terrain.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CharacterFavoredTerrain", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "terrain", nullable = false)
    private Set<Terrain> favoredTerrains;

    @ElementCollection(targetClass = Language.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CharacterLanguage", joinColumns = @JoinColumn(name = "characterId", nullable = false))
    @Column(name = "language", nullable = false)
    private Set<Language> languages;

    @ManyToOne
    @JoinColumn(name = "deityId", nullable = true)
    private Deity deity;

    @NotNull
    @Column(name = "image", nullable = false)
    private String image;

    @Min(value = 0)
    @Digits(integer = 12, fraction = 0)
    @Column(name = "gold", nullable = false)
    private long gold = 0;

    public Character() {
        super();
        classes = new HashSet<CharacterClass>();
        feats = new HashSet<Feat>();
        skillProficiencies = new HashSet<Skill>();
        equipment = new HashSet<CharacterEquipment>();
        languages = EnumSet.noneOf(Language.class);
        favoredEnnemies = new ArrayList<CreatureType>();
        favoredTerrains = new HashSet<Terrain>();
        armorProficiencies = EnumSet.noneOf(ArmorType.ProficiencyType.class);
        weaponProficiencies = new HashSet<>();
        savingThrowProficiencies = new HashSet<>();
        skillProficiencies = new HashSet<>();
        toolProficiencies = new HashSet<>();
    }

    @Override
    public Character clone() {
        try {
            Character c = (Character) super.clone();
            c.setClasses(c.getClasses().stream().map(CharacterClass::clone).collect(Collectors.toSet()));
            c.setArmorProficiencies(new HashSet<>(c.getArmorProficiencies()));
            c.setFavoredEnnemies(new ArrayList<>(c.getFavoredEnnemies()));
            c.setFavoredTerrains(new HashSet<>(c.getFavoredTerrains()));
            c.setFeats(new HashSet<>(c.getFeats()));
            c.setLanguages(EnumSet.copyOf(c.getLanguages()));
            c.setSavingThrowProficiencies(new HashSet<>(c.getSavingThrowProficiencies()));
            c.setSkillProficiencies(new HashSet<>(c.getSkillProficiencies()));
            c.setToolProficiencies(new HashSet<>(c.getToolProficiencies()));
            c.setWeaponProficiencies(new HashSet<>(c.getWeaponProficiencies()));
            return c;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Set<CharacterClass> getClasses() {
        return classes;
    }

    public void setClasses(Set<CharacterClass> classes) {
        this.classes = classes;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    @Override
    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public List<CreatureType> getFavoredEnnemies() {
        return favoredEnnemies;
    }

    public void setFavoredEnnemies(List<CreatureType> favoredEnnemies) {
        this.favoredEnnemies = favoredEnnemies;
    }

    public Set<Terrain> getFavoredTerrains() {
        return favoredTerrains;
    }

    public void setFavoredTerrains(Set<Terrain> favoredTerrains) {
        this.favoredTerrains = favoredTerrains;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public CharacterBackground getBackground() {
        return background;
    }

    public void setBackground(CharacterBackground background) {
        this.background = background;
    }

    public Set<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(Set<Skill> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public Set<WeaponType> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(Set<WeaponType> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public Set<ArmorType.ProficiencyType> getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(Set<ArmorType.ProficiencyType> armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    @Override
    public Set<Ability> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(Set<Ability> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public Set<ToolType> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(Set<ToolType> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public Set<CharacterEquipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<CharacterEquipment> equipment) {
        this.equipment = equipment;
    }

    public Set<Feat> getFeats() {
        return feats;
    }

    public void setFeats(Set<Feat> feats) {
        this.feats = feats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public void giveExperience(long xp) {
        this.experience += xp;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Deity getDeity() {
        return deity;
    }

    public void setDeity(Deity deity) {
        this.deity = deity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }
}
