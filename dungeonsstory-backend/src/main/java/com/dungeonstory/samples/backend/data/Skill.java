package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Skill")
public class Skill extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -2967904843715939261L;

	private static Long idSkill = 1L;
	
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "keyAbilityId", nullable = false)
	private Ability keyAbility;

//	@Column(name = "armorCheckPenalty", nullable = false)
//	private int armorCheckPenalty;
//
//	@Column(name = "tryAgain", nullable = false)
//	private boolean tryAgain;
//
//	@Column(name = "trained", nullable = false)
//	private boolean trained;

//	@OneToMany(mappedBy = "skill")
//	private List<CharacterSkill> characters;
	
	@ManyToMany(mappedBy = "skills")
    private List<Character> characters;

	@OneToMany(mappedBy = "skill")
	private List<ClassSkill> classes;

	public Skill() {
		super();
		setId(idSkill++);
	}
	
	public Skill(String name, Ability ability) {
		this();
		this.name = name;
		this.keyAbility = ability;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Ability getKeyAbility() {
		return keyAbility;
	}

	public void setKeyAbility(Ability keyAbility) {
		this.keyAbility = keyAbility;
	}

//	public int getArmorCheckPenalty() {
//		return armorCheckPenalty;
//	}
//
//	public void setArmorCheckPenalty(int armorCheckPenalty) {
//		this.armorCheckPenalty = armorCheckPenalty;
//	}
//
//	public boolean isTryAgain() {
//		return tryAgain;
//	}
//
//	public void setTryAgain(boolean tryAgain) {
//		this.tryAgain = tryAgain;
//	}
//
//	public boolean isTrained() {
//		return trained;
//	}
//
//	public void setTrained(boolean trained) {
//		this.trained = trained;
//	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<ClassSkill> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassSkill> classes) {
		this.classes = classes;
	}

}
