package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Skill;

public class SkillGrid extends DSGrid<Skill> {

    private static final long serialVersionUID = 5108019895920968099L;

    public SkillGrid() {
        super(Skill.class);
        //        getContainer().addNestedContainerBean("keyAbility");
        withProperties("name", "keyAbility.name", "shortDescription");
        withHeaderCaption("Nom", "Caractéristique clé", "Description courte");
    }

}
