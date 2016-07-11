package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.samples.crud.BeanGrid;

public class ArmorTypeGrid extends BeanGrid<ArmorType> {

    private static final long serialVersionUID = -425928960446143041L;

    public ArmorTypeGrid() {
        super(ArmorType.class);
        withColumns("name", "proficiencyType", "maxDexBonus", "baseArmorClass");
        withHeaderCaption("Nom", "Type de compétence", "Bonus max dextérité", "Classe d'armure de base");

    }

}
