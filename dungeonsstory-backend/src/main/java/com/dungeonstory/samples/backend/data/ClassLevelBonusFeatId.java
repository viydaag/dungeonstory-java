package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClassLevelBonusFeatId implements Serializable {
	private Long classId;

	private Long levelId;

	private Long featId;

	public ClassLevelBonusFeatId() {
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

	public Long getFeatId() {
		return featId;
	}

	public void setFeatId(Long featId) {
		this.featId = featId;
	}

}
