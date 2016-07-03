package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class ClassSkillId implements Serializable {
	
	private static final long serialVersionUID = 7546960040742042357L;

	private Long classId;

	private Long skillId;

	public ClassSkillId() {
		// TODO Auto-generated constructor stub
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classId == null) ? 0 : classId.hashCode());
		result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
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
		if (!(obj instanceof ClassSkillId)) {
			return false;
		}
		ClassSkillId other = (ClassSkillId) obj;
		if (classId == null) {
			if (other.classId != null) {
				return false;
			}
		} else if (!classId.equals(other.classId)) {
			return false;
		}
		if (skillId == null) {
			if (other.skillId != null) {
				return false;
			}
		} else if (!skillId.equals(other.skillId)) {
			return false;
		}
		return true;
	}


}
