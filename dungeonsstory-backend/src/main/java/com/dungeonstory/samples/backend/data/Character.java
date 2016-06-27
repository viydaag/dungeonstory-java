package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
public class Character extends AbstractTimestampEntity implements Serializable
{
    @Column(name="name", nullable=false)
    private String name;
    
    @Column(name="gender", nullable=false)
    private char gender;
    
    @Min(value = 0)
    @Column(name="age", nullable=false)
    private int age;
    
    @Min(value = 0)
    @Column(name="weight", nullable=false)
    private int weight;
    
    @Min(value = 0)
    @Column(name="heigth", nullable=false)
    private int heigth;
    
    @OneToOne(mappedBy="character")
    @JoinColumn(name="userId", nullable=false)
    private User user;
    
    @Min(value = 1)
    @Column(name="level", nullable=false)
    private int level;
    
    @Min(value = 0)
    @Column(name="experience", nullable=false)
    private long experience;
    
    @Min(value = 0)
    @Column(name="lifePoints", nullable=false)
    private int lifePoints;
    
    @Min(value = 0)
    @Column(name="baseArmorClass", nullable=false)
    private int baseArmorClass;
    
    @Column(name="background", columnDefinition="TEXT")
    private String background;
    
    @Column(name="look", columnDefinition="TEXT")
    private String look;
    
    @Column(name="personnality", columnDefinition="TEXT")
    private String personnality;
    
    @ManyToOne
    @JoinColumn(name = "alignmentId", nullable=false)
    private Alignment alignment;
    
    @ManyToOne
    @JoinColumn(name = "regionId", nullable=false)
    private Region region;
    
    @ManyToOne
    @JoinColumn(name = "adventureId", nullable=true)
    private Adventure adventure;

    @OneToMany(mappedBy = "character")
    private List<CharacterSkill> skills;
    
    @OneToMany(mappedBy = "character")
    private List<CharacterClass> classes;
    
    @ManyToMany
    @JoinTable(
        name="CharacterFeat",
        joinColumns={@JoinColumn(name="characterId", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="featId", referencedColumnName="id")})
    private List<Feat> feats;

    public Character()
    {
        // TODO Auto-generated constructor stub
    }

    public void addSkill(Skill skill, int rank)
    {
        CharacterSkill association = new CharacterSkill();
        association.setSkill(skill);
        association.setCharacter(this);
        association.setSkillId(skill.getId());
        association.setCharacterId(getId());
        association.setRank(rank);

        this.skills.add(association);
        // Also add the association object to the skill
        skill.getCharacters().add(association);
    }

}
