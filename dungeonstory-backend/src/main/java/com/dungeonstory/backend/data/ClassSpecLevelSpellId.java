package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassSpecLevelSpellId implements Serializable {
    
    private static final long serialVersionUID = 8559253925871170628L;

    private Long classSpec;

    private Long level;

    private Long spell;

    public ClassSpecLevelSpellId() {

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

    public Long getSpell() {
        return spell;
    }

    public void setSpell(Long spell) {
        this.spell = spell;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classSpec == null) ? 0 : classSpec.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((spell == null) ? 0 : spell.hashCode());
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
        if (!(obj instanceof ClassSpecLevelSpellId)) {
            return false;
        }
        ClassSpecLevelSpellId other = (ClassSpecLevelSpellId) obj;
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
        if (spell == null) {
            if (other.spell != null) {
                return false;
            }
        } else if (!spell.equals(other.spell)) {
            return false;
        }
        return true;
    }

}
