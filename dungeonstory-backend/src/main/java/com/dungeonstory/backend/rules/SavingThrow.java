package com.dungeonstory.backend.rules;

import com.dungeonstory.backend.data.Ability;

public class SavingThrow {
    
    private Ability ability;
    private int modifier;

    public SavingThrow(Ability ability, int modifier) {
        super();
        this.ability = ability;
        this.modifier = modifier;
    }
    
    public Ability getAbility() {
        return ability;
    }

    public int getModifier() {
        return modifier;
    }
    
    @Override
    public String toString() {
        return getAbility().getAbbreviation() + " +" + modifier;
    }

}
