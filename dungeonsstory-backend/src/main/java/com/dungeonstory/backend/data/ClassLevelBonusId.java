package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassLevelBonusId implements Serializable {

    private static final long serialVersionUID = -348590476654163585L;

    private Long classId;

    private Long levelId;

    public ClassLevelBonusId() {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classId == null) ? 0 : classId.hashCode());
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
        if (!(obj instanceof ClassLevelBonusId)) {
            return false;
        }
        ClassLevelBonusId other = (ClassLevelBonusId) obj;
        if (classId == null) {
            if (other.classId != null) {
                return false;
            }
        } else if (!classId.equals(other.classId)) {
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
