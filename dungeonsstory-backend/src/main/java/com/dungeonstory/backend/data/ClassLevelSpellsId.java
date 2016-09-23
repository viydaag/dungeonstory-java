package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassLevelSpellsId implements Serializable {
    
    private static final long serialVersionUID = -6048112689742799627L;

    private Long classe;

    private Long level;

    public ClassLevelSpellsId() {

    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classe) {
        this.classe = classe;
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
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
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
        if (!(obj instanceof ClassLevelSpellsId)) {
            return false;
        }
        ClassLevelSpellsId other = (ClassLevelSpellsId) obj;
        if (classe == null) {
            if (other.classe != null) {
                return false;
            }
        } else if (!classe.equals(other.classe)) {
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
