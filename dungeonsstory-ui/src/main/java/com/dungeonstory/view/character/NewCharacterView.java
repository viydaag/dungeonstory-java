package com.dungeonstory.view.character;

import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;

@ViewConfig(uri = "newCharacter", displayName = "Nouveau personnage")
public class NewCharacterView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 2928235696918477334L;

    private LevelService levelService = LevelService.getInstance();

    public NewCharacterView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        CharacterWizard wizard = new CharacterWizard();

        // set character level 1
        wizard.getCharacter().setLevel(levelService.read(1L));

        wizard.addStep(new RaceStep(wizard), "raceChoice");
        wizard.addStep(new ClassStep(wizard), "classChoice");
        wizard.addStep(new AbilityScoreStep(wizard), "abilityScores");
        wizard.addStep(new SpellStep(wizard), "spellChoice");
        wizard.addStep(new SummaryStep(wizard), "summary");
        wizard.getNextButton().setEnabled(false); //set the next button disabled until a value is selected
        addComponent(wizard);
        setComponentAlignment(wizard, Alignment.MIDDLE_CENTER);
    }

}
