package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Character;

public class CharacterGrid extends BeanGrid<Character> {

    private static final long serialVersionUID = 8060713315641761422L;

    public CharacterGrid() {
        super(Character.class);
        withColumns("name");
        withHeaderCaption("Nom");
    }

}
