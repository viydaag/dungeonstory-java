package com.dungeonstory.ui.view.character.wizard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.vaadin.teemu.wizards.event.WizardCompletedEvent;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.event.CharacterCreatedEvent;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.i18n.Messages;
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
        getNextButton().setId("nextButton");
        getBackButton().setId("backButton");
        getCancelButton().setId("cancelButton");
        getFinishButton().setId("finishButton");
    }

    @Override
    public void setChosenClass(DSClass chosenClass) {
        super.setChosenClass(chosenClass);

        removeStepsAfterClass();

        CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);

        //check if a class specialization is available
        Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(chosenClass, assignedClass.getClassLevel());
        if (classLevelBonusOpt.isPresent()) {
            if (classLevelBonusOpt.get().getChooseClassSpecialization()) {
                addStep(new ClassSpecializationStep(this), CLASS_SPEC);
            }
        }

        //check if some class features need a choice
        List<ClassFeature> parentClassFeatures = ClassUtil.getClassFeaturesForLevel(chosenClass, assignedClass.getClassLevel())
                .filter(cf -> !cf.getChildren().isEmpty()).collect(Collectors.toList());
        if (!parentClassFeatures.isEmpty()) {
            addStep(new ClassFeatureStep(this), CLASS_FEATURE);
        }

        if (chosenClass.getIsSpellCasting()) {
            addStep(new SpellStep(this), SPELL);
        }

        addStep(new BackgroundStep(this), BACKGROUND);
        addStep(new InformationStep(this), INFO);
        addStep(new SummaryStep(this), SUMMARY);
    }

    private void removeStepsAfterClass() {
        removeStep(CLASS_SPEC);
        removeStep(SPELL);
        removeStep(BACKGROUND);
        removeStep(INFO);
        removeStep(SUMMARY);
    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        Messages messages = Messages.getInstance();
        User user = Services.getUserService().read(CurrentUser.get().getId());
        user.setCharacter(character);
        character.setUser(user);
        characterService.create(character);
        CurrentUser.set(user);
        Notification.show(messages.getMessage("newCharacterView.notif.created"), Type.HUMANIZED_MESSAGE);
        EventBus.post(new CharacterCreatedEvent(character));
        super.wizardCompleted(event);
    }

}
