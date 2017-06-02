package com.dungeonstory.ui.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.util.ViewConfig.CreateMode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

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

        CharacterInfoForm infoForm = new CharacterInfoForm();
        Character character = CurrentUser.get().getCharacter();
        infoForm.setEntity(character);
        tabsheet.addTab(infoForm, "Informations");

//        EquipmentList equipment = new EquipmentList();
//        tabsheet.addTab(equipment, "Équipement");

        ProficiencyList proficiencies = new ProficiencyList(character);
        tabsheet.addTab(proficiencies, "Maitrises");

        ClassFeatureList features = new ClassFeatureList(character);
        tabsheet.addTab(features, "Dons");

        addComponent(tabsheet);

    }

}
