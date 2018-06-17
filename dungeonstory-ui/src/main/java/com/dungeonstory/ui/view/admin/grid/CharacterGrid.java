package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.Services;
import com.vaadin.ui.renderers.ProgressBarRenderer;

public class CharacterGrid
        extends DSGrid<Character> {

    private static final long serialVersionUID = 8060713315641761422L;

    public CharacterGrid() {
        super();

        addColumn(Character::getName).setCaption("Nom").setId("name");
        addColumn(character -> {
            Level previousLevel = Services.getLevelService().read(character.getLevel().getId() - 1);
            double progress = 0.0;
            if (previousLevel == null) {
                progress = (double) character.getExperience() / (double) character.getLevel().getMaxExperience();
            } else {
                progress = (double) (character.getExperience() - previousLevel.getMaxExperience())
                        / (double) (character.getLevel().getMaxExperience() - previousLevel.getMaxExperience());
            }
            return progress;
        }, new ProgressBarRenderer()).setCaption("Progrès").setId("progress");

    }

}
