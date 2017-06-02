package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Skill;

public class SkillGrid extends DSGrid<Skill> {

    private static final long serialVersionUID = 5108019895920968099L;

    public SkillGrid() {
        super();
        addColumn(Skill::getName).setCaption("Nom").setId("name");
        addColumn(Skill::getKeyAbility).setCaption("Caractéristique clé").setId("keyAbility");
        addColumn(Skill::getShortDescription).setCaption("Description courte").setId("shortDescription");
    }

}
