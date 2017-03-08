package com.dungeonstory.view.character;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.ViewConfig.CreateMode;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@ViewConfig(uri = CharacterView.URI, displayName = "characterView.caption", createMode = CreateMode.ALWAYS_NEW)
public class CharacterView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 3904939774479485709L;

    public static final String URI = "character";

    public CharacterView() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void enter(ViewChangeEvent event) {
        CharacterInfoForm form = new CharacterInfoForm();
        Character character = CurrentUser.get().getCharacter();
        form.setEntity(character);
        addComponent(form);
    }

}
