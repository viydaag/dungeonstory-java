package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassSpecializationSpellSlotsId implements Serializable {
    
    private static final long serialVersionUID = -2972533498865485257L;

    private Long classSpec;

    private Long level;

    public ClassSpecializationSpellSlotsId() {
        super();
    }

    public Long getClassSpec() {
        return classSpec;
    }

    public void setClassSpec(Long classSpec) {
        this.classSpec = classSpec;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classSpec == null) ? 0 : classSpec.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
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
        if (!(obj instanceof ClassSpecializationSpellSlotsId)) {
            return false;
        }
        ClassSpecializationSpellSlotsId other = (ClassSpecializationSpellSlotsId) obj;
        if (classSpec == null) {
            if (other.classSpec != null) {
                return false;
            }
        } else if (!classSpec.equals(other.classSpec)) {
            return false;
        }
        if (level == null) {
            if (other.level != null) {
                return false;
            }
        } else if (!level.equals(other.level)) {
            return false;
        }
        return true;
    }

}
