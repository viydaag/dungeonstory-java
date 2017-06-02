package com.dungeonstory.ui.view.character;

import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.character.wizard.NewCharacterWizard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = NewCharacterView.NEW_CHARACTER_URI, displayName = "newCharacterView.caption")
public class NewCharacterView extends VerticalLayout implements View {

    private static final long serialVersionUID = 2928235696918477334L;

    public static final String NEW_CHARACTER_URI = "newCharacter";

    public NewCharacterView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        NewCharacterWizard wizard = new NewCharacterWizard();

        addComponent(wizard);
        setComponentAlignment(wizard, Alignment.MIDDLE_CENTER);
    }

}
