package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassSkillId implements Serializable {

    private static final long serialVersionUID = 7546960040742042357L;

    private Long classe;

    private Long skill;

    public ClassSkillId() {

    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classId) {
        this.classe = classId;
    }

    public Long getSkill() {
        return skill;
    }

    public void setSkill(Long skillId) {
        this.skill = skillId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
        result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
        if (classe == null) {
            if (other.classe != null) {
                return false;
            }
        } else if (!classe.equals(other.classe)) {
            return false;
        }
        if (skill == null) {
            if (other.skill != null) {
                return false;
            }
        } else if (!skill.equals(other.skill)) {
            return false;
        }
        return true;
    }

}
