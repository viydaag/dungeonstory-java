package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class ClassLevelBonusFeatId implements Serializable {
	
	private static final long serialVersionUID = 6085535553525670809L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classId == null) ? 0 : classId.hashCode());
		result = prime * result + ((featId == null) ? 0 : featId.hashCode());
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ClassLevelBonusFeatId)) {
			return false;
		}
		ClassLevelBonusFeatId other = (ClassLevelBonusFeatId) obj;
		if (classId == null) {
			if (other.classId != null) {
				return false;
			}
		} else if (!classId.equals(other.classId)) {
			return false;
		}
		if (featId == null) {
			if (other.featId != null) {
				return false;
			}
		} else if (!featId.equals(other.featId)) {
			return false;
		}
		if (levelId == null) {
			if (other.levelId != null) {
				return false;
			}
		} else if (!levelId.equals(other.levelId)) {
			return false;
		}
		return true;
	}

}
