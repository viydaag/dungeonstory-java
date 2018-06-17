package com.dungeonstory.ui.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.character.wizard.LevelUpCharacterWizard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@ViewConfig(uri = LevelUpView.LEVEL_UP_URI, displayName = "levelUpView.caption")
public class LevelUpView extends VerticalLayout implements View {

    private static final long serialVersionUID = 2081789976545395221L;

    public static final String LEVEL_UP_URI = "levelUp";

    public LevelUpView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Character character = CurrentUser.get().getCharacter();
        if (Services.getCharacterService().isAbleToLevelUp(character)) {
            LevelUpCharacterWizard wizard = new LevelUpCharacterWizard(character);
            addComponent(wizard);
            setComponentAlignment(wizard, Alignment.MIDDLE_CENTER);
        } else {
            Label header = new Label("Vous ne pouvez pas monter de niveau en ce moment.");
            header.addStyleName(ValoTheme.LABEL_H1);
            addComponent(header);
        }
    }

}
