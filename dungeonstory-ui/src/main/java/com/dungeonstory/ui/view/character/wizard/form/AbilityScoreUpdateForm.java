package com.dungeonstory.ui.view.character.wizard.form;

import java.util.Arrays;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.RadioButtonGroup;

public class AbilityScoreUpdateForm extends AbilityScoreForm {

    private static final long serialVersionUID = 1307298207679565178L;

    private final int MAX_SCORE = 20;

    private Character       backupEntity;
    private FeatDataService featService = null;

    private RadioButtonGroup<UpdateType> updateType;
    private ComboBox<Feat>           featChoice;
    private Label                  featDescription;
    private HorizontalLayout             featLayout;

    public enum UpdateType {
        ABILITY("abilityScoreStep.updateType.ability.value"), FEAT("abilityScoreStep.updateType.feat.value");

        private String value;

        private UpdateType(String name) {
            this.value = name;
        }

        public String getValue() {
            return Messages.getInstance().getMessage(this.value);
        }

        @Override
        public String toString() {
            return getValue();
        }
    }

    public AbilityScoreUpdateForm(Character character) {
        super();
        featService = Services.getFeatService();
        backupEntity = character;
    }

    @Override
    protected Component createContent() {
        Messages messages = Messages.getInstance();
        Component content = super.createContent();
        featLayout = new HorizontalLayout();
        featLayout.setWidth(100, Unit.PERCENTAGE);

        updateType = new RadioButtonGroup<UpdateType>(messages.getMessage("abilityScoreStep.updateType.label"));
        updateType.setItems(Arrays.asList(UpdateType.values()));
        updateType.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                abilityLayout.setVisible(event.getValue() == UpdateType.ABILITY);
                featLayout.setVisible(event.getValue() == UpdateType.FEAT);

                if (event.getValue() == UpdateType.ABILITY) {
                    featChoice.setValue(null);
                } else {
                    resetPointToSpend();

                    strength.setValue(backupEntity.getStrength());
                    dexterity.setValue(backupEntity.getDexterity());
                    constitution.setValue(backupEntity.getConstitution());
                    intelligence.setValue(backupEntity.getIntelligence());
                    wisdom.setValue(backupEntity.getWisdom());
                    charisma.setValue(backupEntity.getCharisma());
                }
                adjustButtons();
            }
        });

        getLayout().addComponentAsFirst(updateType);

        resetPointToSpend();

        featChoice = new ComboBox<Feat>(messages.getMessage("abilityScoreStep.feat.label"));
        featChoice.setWidth(100, Unit.PERCENTAGE);
        featDescription = new MLabel();
        featLayout.addComponents(featChoice, featDescription);

        getLayout().addComponent(featLayout);

        return content;
    }

    private void resetPointToSpend() {
        pointsToSpend.setValue(2);
    }

    @Override
    public void afterSetEntity() {

        featChoice.setItems(featService.findAllUnassignedFeats(getEntity()));
        featChoice.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                featDescription.setValue(event.getValue().getDescription());
            } else {
                featDescription.setValue("");
            }
            adjustButtons();
        });
        updateType.setValue(UpdateType.ABILITY);

        gridLayout.addComponent(createPlusButton(strength), 3, 1);
        gridLayout.addComponent(createMinusButton(strength, getEntity().getStrength()), 4, 1);
        strength.setReadOnly(true);

        gridLayout.addComponent(createPlusButton(dexterity), 3, 2);
        gridLayout.addComponent(createMinusButton(dexterity, getEntity().getDexterity()), 4, 2);
        dexterity.setReadOnly(true);

        gridLayout.addComponent(createPlusButton(constitution), 3, 3);
        gridLayout.addComponent(createMinusButton(constitution, getEntity().getConstitution()), 4, 3);
        constitution.setReadOnly(true);

        gridLayout.addComponent(createPlusButton(intelligence), 3, 4);
        gridLayout.addComponent(createMinusButton(intelligence, getEntity().getIntelligence()), 4, 4);
        intelligence.setReadOnly(true);

        gridLayout.addComponent(createPlusButton(wisdom), 3, 5);
        gridLayout.addComponent(createMinusButton(wisdom, getEntity().getWisdom()), 4, 5);
        wisdom.setReadOnly(true);

        gridLayout.addComponent(createPlusButton(charisma), 3, 6);
        gridLayout.addComponent(createMinusButton(charisma, getEntity().getCharisma()), 4, 6);
        charisma.setReadOnly(true);
    }

    private Button createPlusButton(IntegerField fieldAction) {
        Button plusButton = new Button(VaadinIcons.PLUS);
        plusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue() + 1;
            int nbPointToSpend = 1;
            if (value <= MAX_SCORE) {
                if (pointsToSpend.getValue() >= nbPointToSpend) {
                    pointsToSpend.setValue(pointsToSpend.getValue() - nbPointToSpend);
                    fieldAction.setValue(value);
                }
            } else {
                Notification.show(Messages.getInstance().getMessage("abilityScoreStep.notif.maxScore", MAX_SCORE), Type.HUMANIZED_MESSAGE);
            }
        });
        return plusButton;
    }

    private Button createMinusButton(IntegerField fieldAction, int minValue) {
        Button minusButton = new Button(VaadinIcons.MINUS);
        minusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue();
            int nbPointToSpend = 1;
            if (value > minValue) {
                pointsToSpend.setValue(pointsToSpend.getValue() + nbPointToSpend);
                fieldAction.setValue(value - 1);
            } else {
                Notification.show(Messages.getInstance().getMessage("abilityScoreStep.notif.minusInitial"), Type.HUMANIZED_MESSAGE);
            }
        });
        return minusButton;
    }

    @Override
    public void onSave(Character entity) {
        if (featChoice.getValue() != null) {
            entity.getFeats().add(featChoice.getValue());
        }
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            if (updateType.getValue() == UpdateType.ABILITY) {
                boolean isValid = getBinder().isValid();
                boolean allPointsSpent = pointsToSpend.getValue() == 0;
                getSaveButton().setEnabled(allPointsSpent && isValid);
            } else {
                getSaveButton().setEnabled(featChoice.getValue() != null);
            }
        }
    }

}
