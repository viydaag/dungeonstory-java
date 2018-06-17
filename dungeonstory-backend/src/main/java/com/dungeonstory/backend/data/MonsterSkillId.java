package com.dungeonstory.backend.data;

import java.io.Serializable;

import com.dungeonstory.backend.data.enums.Skill;

public class MonsterSkillId implements Serializable {
    
    private static final long serialVersionUID = 4325306893401609721L;

    private Long monster;

    private Skill skill;

    public MonsterSkillId() {
        
    }

    public Long getMonster() {
        return monster;
    }

    public void setMonster(Long monster) {
        this.monster = monster;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((monster == null) ? 0 : monster.hashCode());
        result = prime * result + ((skill == null) ? 0 : skill.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MonsterSkillId other = (MonsterSkillId) obj;
        if (monster == null) {
            if (other.monster != null)
                return false;
        } else if (!monster.equals(other.monster))
            return false;
        if (skill == null) {
            if (other.skill != null)
                return false;
        } else if (!skill.equals(other.skill))
            return false;
        return true;
    }

}
