package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Skill;

public class SkillGrid extends DSGrid<Skill> {

    private static final long serialVersionUID = 5108019895920968099L;

    public SkillGrid() {
        super(Skill.class);
        withProperties("name", "keyAbility.name", "shortDescription");
        withColumnHeaders("Nom", "Caractéristique clé", "Description courte");
    }

}
