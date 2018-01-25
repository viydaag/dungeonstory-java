package com.dungeonstory.ui.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.util.ViewConfig.CreateMode;
import com.vaadin.fluent.ui.FButton;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@ViewConfig(uri = CharacterView.URI, displayName = "characterView.caption", createMode = CreateMode.ALWAYS_NEW)
public class CharacterView extends VerticalLayout implements View {

    private static final long serialVersionUID = 3904939774479485709L;

    public static final String URI = "character";

    private TabSheet tabsheet;

    public CharacterView() {
        tabsheet = new TabSheet();
    }

    @Override
    public void enter(ViewChangeEvent event) {

        User user = Services.getUserService().read(CurrentUser.get().getId());
        Services.getUserService().refresh(user);
        Character character = user.getCharacter();

        if (character.getExperience() >= character.getLevel().getMaxExperience()) {
            FButton levelUpButton = new FButton("Niveau").withIcon(VaadinIcons.ARROW_UP)
                                                         .withStyleName(ValoTheme.BUTTON_LARGE)
                                                         .withStyleName(ValoTheme.BUTTON_FRIENDLY);
            levelUpButton.addClickListener(e -> EventBus.post(new NavigationEvent(LevelUpView.LEVEL_UP_URI)));
            addComponent(levelUpButton);
        }

        CharacterInfoForm infoForm = new CharacterInfoForm();
        infoForm.setEntity(character);
        tabsheet.addTab(infoForm, "Informations");

        EquipmentList2 equipment = new EquipmentList2(character);
        tabsheet.addTab(equipment, "Équipement");

        ProficiencyList proficiencies = new ProficiencyList(character);
        tabsheet.addTab(proficiencies, "Maitrises");

        ClassFeatureList features = new ClassFeatureList(character);
        tabsheet.addTab(features, "Dons");

        addComponent(tabsheet);

    }

}
