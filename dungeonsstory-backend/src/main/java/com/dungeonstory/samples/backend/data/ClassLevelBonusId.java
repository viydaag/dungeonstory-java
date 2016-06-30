package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class ClassLevelBonusId implements Serializable {

	private static final long serialVersionUID = -348590476654163585L;

	private Long classId;

	private Long levelId;

	public ClassLevelBonusId() {
		// TODO Auto-generated constructor stub
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

}
