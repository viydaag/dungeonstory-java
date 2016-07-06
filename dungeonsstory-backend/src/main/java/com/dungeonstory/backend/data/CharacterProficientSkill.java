package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class CharacterProficientSkill implements Serializable {

	private static final long serialVersionUID = -1388182981473999767L;

	@Id
	@Column(name = "characterId")
	private Long characterId;

	@Id
	@Column(name = "skillId")
	private Long skillId;

	public CharacterProficientSkill() {
		// TODO Auto-generated constructor stub
	}

}
