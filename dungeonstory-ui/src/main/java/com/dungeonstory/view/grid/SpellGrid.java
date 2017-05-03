package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Spell;

public class SpellGrid extends DSGrid<Spell> {

    private static final long serialVersionUID = 1178337698356790032L;

    public SpellGrid() {
        super();
        addColumn(Spell::getLevel).setCaption("Niveau").setId("level");
        addColumn(Spell::getName).setCaption("Nom").setId("name");
        addColumn(Spell::getSchool).setCaption("École de magie").setId("school");
    }

}
