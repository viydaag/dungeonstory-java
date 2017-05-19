package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Character;

public class CharacterGrid extends DSGrid<Character> {

    private static final long serialVersionUID = 8060713315641761422L;

    public CharacterGrid() {
        super();
        //        withGeneratedColumn("delete", new PropertyValueGenerator<String>() {
        //
        //            private static final long serialVersionUID = 6332188902387035064L;
        //
        //            @Override
        //            public String getValue(Item item, Object itemId, Object propertyId) {
        //                return "Supprimer";
        //            }
        //
        //            @Override
        //            public Class<String> getType() {
        //                return String.class;
        //            }
        //        });
        //        withProperties("name", "delete");
        //        withColumnHeaders("Nom", "");

        addColumn(Character::getName).setCaption("Nom").setId("name");
    }

}
