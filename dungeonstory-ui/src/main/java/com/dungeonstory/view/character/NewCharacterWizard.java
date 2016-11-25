package com.dungeonstory.view.character;

import org.vaadin.teemu.wizards.event.WizardCompletedEvent;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.DSClass;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class NewCharacterWizard extends CharacterWizard {

    private static final long serialVersionUID = -8481346074235692258L;

    public NewCharacterWizard() {
        super();
        setOriginal(characterService.create());

        addStep(new RaceStep(this), RACE);
        addStep(new ClassStep(this), CLASS);
        addStep(new AbilityScoreStep(this, false), ABILITY);

        getNextButton().setEnabled(false); //set the next button disabled until a value is selected
    }

    @Override
    public void setChosenClass(DSClass chosenClass) {
        super.setChosenClass(chosenClass);

        removeStepsAfterClass();

        if (chosenClass.getIsSpellCasting()) {
            addStep(new SpellStep(this), SPELL);
        }

        addStep(new BackgroundStep(this), BACKGROUND);
        addStep(new InformationStep(this), INFO);
        addStep(new SummaryStep(this), SUMMARY);
    }

    private void removeStepsAfterClass() {
        removeStep(SPELL);
        removeStep(BACKGROUND);
        removeStep(INFO);
        removeStep(SUMMARY);
    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        character.setUser(CurrentUser.get());
        characterService.create(character);
        Notification.show("Personnage créé!", Type.HUMANIZED_MESSAGE);
        super.wizardCompleted(event);
    }

}
