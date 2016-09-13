package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DSCharacter")
public class Character extends AbstractTimestampEntity implements Serializable {
    private static final long serialVersionUID = -967001655180847193L;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "gender", nullable = false)
    private char gender;

    @NotNull
    @Min(value = 0)
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull
    @Min(value = 0)
    @Column(name = "weight", nullable = false)
    private int weight;

    @NotNull
    @Min(value = 0)
    @Column(name = "heigth", nullable = false)
    private int heigth;

    @NotNull
    @OneToOne(mappedBy = "character")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "raceId", nullable = false)
    private Race race;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @NotNull
    @Min(value = 0)
    @Column(name = "experience", nullable = false)
    private long experience;

    @NotNull
    @Min(value = 0)
    @Column(name = "lifePoints", nullable = false)
    private int lifePoints;

    @NotNull
    @Min(value = 0)
    @Column(name = "baseArmorClass", nullable = false)
    private int baseArmorClass;

    @Column(name = "background", columnDefinition = "TEXT")
    private String background;

    @Column(name = "look", columnDefinition = "TEXT")
    private String look;

    @Column(name = "personnality", columnDefinition = "TEXT")
    private String personnality;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "alignmentId", nullable = false)
    private Alignment alignment;

    @ManyToOne
    @JoinColumn(name = "regionId", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "adventureId", nullable = true)
    private Adventure adventure;

    @OneToMany(mappedBy = "character")
    private List<Message> messages;

//    @OneToMany(mappedBy = "character")
//    private List<CharacterSkill> skills;
    
    @OneToMany(mappedBy = "character")
    private List<CharacterClass> classes;
    
    @ManyToMany
    @JoinTable(
        name="CharacterFeat",
        joinColumns={@JoinColumn(name="characterId", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="featId", referencedColumnName="id")})
    private List<Feat> feats;
    
    @ManyToMany
    @JoinTable(
        name="CharacterProficientSkill",
        joinColumns=@JoinColumn(name="characterId", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="skillId", referencedColumnName="id"))
    private List<Skill> skills;
    
    @OneToMany(mappedBy = "character")
    private List<CharacterEquipment> equipment;

    public Character() {

    }

}
