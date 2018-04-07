package com.dungeonstory.backend.data;

import java.io.Serializable;

import com.dungeonstory.backend.data.enums.Ability;

public class MonsterSavingThrowId implements Serializable {

    private static final long serialVersionUID = 6565804563622879668L;

    private Long monster;

    private Ability ability;

    public MonsterSavingThrowId() {

    }

    public Long getMonster() {
        return monster;
    }

    public void setMonster(Long monster) {
        this.monster = monster;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ability == null) ? 0 : ability.hashCode());
        result = prime * result + ((monster == null) ? 0 : monster.hashCode());
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
        MonsterSavingThrowId other = (MonsterSavingThrowId) obj;
        if (ability != other.ability)
            return false;
        if (monster == null) {
            if (other.monster != null)
                return false;
        } else if (!monster.equals(other.monster))
            return false;
        return true;
    }


}
