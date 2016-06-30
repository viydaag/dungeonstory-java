package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class ClassSkillId implements Serializable {
	
	private static final long serialVersionUID = 7546960040742042357L;

	private int classId;

	private int skillId;

	public ClassSkillId() {
		// TODO Auto-generated constructor stub
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

}
