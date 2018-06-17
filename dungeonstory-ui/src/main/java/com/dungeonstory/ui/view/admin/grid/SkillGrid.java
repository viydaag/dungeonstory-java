package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Skill;
import com.vaadin.data.provider.ListDataProvider;

public class SkillGrid extends ReadOnlyGrid<Skill> {

    private static final long serialVersionUID = 5108019895920968099L;

    public SkillGrid() {
        super();
        addColumn(Skill::getName).setCaption("Nom").setId("name");
        addColumn(s -> s.getKeyAbility().getName()).setCaption("Caractéristique clé").setId("keyAbility");
//                .setComparator((s1, s2) -> s1.getKeyAbility().toString().compareTo(s2.getKeyAbility().toString()));
        addColumn(Skill::getDescription).setCaption("Description").setId("shortDescription");
        
        setDataProvider(new ListDataProvider<>(EnumSet.allOf(Skill.class)));
    }

}
