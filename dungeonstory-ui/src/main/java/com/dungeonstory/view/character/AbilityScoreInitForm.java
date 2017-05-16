package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.i18n.Messages;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class AbilityScoreInitForm extends AbilityScoreForm {

    private static final long serialVersionUID = -708599071923669623L;

    private IntegerField strengthModifier;
    private IntegerField dexterityModifier;
    private IntegerField constitutionModifier;
    private IntegerField intelligenceModifier;
    private IntegerField wisdomModifier;
    private IntegerField charismaModifier;

    private final int MIN_SCORE = 8;
    private final int MAX_SCORE = 15;

    public AbilityScoreInitForm() {
        super();
    }

    @Override
    protected Component createContent() {
        Component content = super.createContent();

        Messages messages = Messages.getInstance();

        pointsToSpend.setValue(27);
        pointsToSpend.setReadOnly(true);

        gridLayout.removeAllComponents();
        gridLayout.addComponent(new MLabel(messages.getMessage("abilityScoreStep.ability.label")), 0, 0);
        gridLayout.addComponent(new MLabel(messages.getMessage("abilityScoreStep.raceModifier.label")), 1, 0);
        gridLayout.addComponent(new MLabel(messages.getMessage("abilityScoreStep.score.label")), 2, 0);

        strengthModifier = new IntegerField();
        gridLayout.addComponent(strengthLabel, 0, 1);
        gridLayout.addComponent(strengthModifier, 1, 1);
        gridLayout.addComponent(strength, 2, 1);

        dexterityModifier = new IntegerField();
        gridLayout.addComponent(dexterityLabel, 0, 2);
        gridLayout.addComponent(dexterityModifier, 1, 2);
        gridLayout.addComponent(dexterity, 2, 2);

        constitutionModifier = new IntegerField();
        gridLayout.addComponent(constitutionLabel, 0, 3);
        gridLayout.addComponent(constitutionModifier, 1, 3);
        gridLayout.addComponent(constitution, 2, 3);

        intelligenceModifier = new IntegerField();
        gridLayout.addComponent(intelligenceLabel, 0, 4);
        gridLayout.addComponent(intelligenceModifier, 1, 4);
        gridLayout.addComponent(intelligence, 2, 4);

        wisdomModifier = new IntegerField();
        gridLayout.addComponent(wisdomLabel, 0, 5);
        gridLayout.addComponent(wisdomModifier, 1, 5);
        gridLayout.addComponent(wisdom, 2, 5);

        charismaModifier = new IntegerField();
        gridLayout.addComponent(charismaLabel, 0, 6);
        gridLayout.addComponent(charismaModifier, 1, 6);
        gridLayout.addComponent(charisma, 2, 6);

        gridLayout.setColumnExpandRatio(0, 1);
        gridLayout.setColumnExpandRatio(1, 1);
        gridLayout.setColumnExpandRatio(2, 1);

        return content;
    }

    @Override
    public void afterSetEntity() {
        strengthModifier.setValue(getEntity().getRace().getStrModifier());
        strength.setValue(MIN_SCORE + getEntity().getRace().getStrModifier());
        gridLayout.addComponent(createPlusButton(strength, strengthModifier.getValue()), 3, 1);
        gridLayout.addComponent(createMinusButton(strength, strengthModifier.getValue()), 4, 1);
        strength.setReadOnly(true);

        dexterityModifier.setValue(getEntity().getRace().getDexModifier());
        dexterity.setValue(MIN_SCORE + getEntity().getRace().getDexModifier());
        gridLayout.addComponent(createPlusButton(dexterity, dexterityModifier.getValue()), 3, 2);
        gridLayout.addComponent(createMinusButton(dexterity, dexterityModifier.getValue()), 4, 2);
        dexterity.setReadOnly(true);

        constitutionModifier.setValue(getEntity().getRace().getConModifier());
        constitution.setValue(MIN_SCORE + getEntity().getRace().getConModifier());
        gridLayout.addComponent(createPlusButton(constitution, constitutionModifier.getValue()), 3, 3);
        gridLayout.addComponent(createMinusButton(constitution, constitutionModifier.getValue()), 4, 3);
        constitution.setReadOnly(true);

        intelligenceModifier.setValue(getEntity().getRace().getIntModifier());
        intelligence.setValue(MIN_SCORE + getEntity().getRace().getIntModifier());
        gridLayout.addComponent(createPlusButton(intelligence, intelligenceModifier.getValue()), 3, 4);
        gridLayout.addComponent(createMinusButton(intelligence, intelligenceModifier.getValue()), 4, 4);
        intelligence.setReadOnly(true);

        wisdomModifier.setValue(getEntity().getRace().getWisModifier());
        wisdom.setValue(MIN_SCORE + getEntity().getRace().getWisModifier());
        gridLayout.addComponent(createPlusButton(wisdom, wisdomModifier.getValue()), 3, 5);
        gridLayout.addComponent(createMinusButton(wisdom, wisdomModifier.getValue()), 4, 5);
        wisdom.setReadOnly(true);

        charismaModifier.setValue(getEntity().getRace().getChaModifier());
        charisma.setValue(MIN_SCORE + getEntity().getRace().getChaModifier());
        gridLayout.addComponent(createPlusButton(charisma, charismaModifier.getValue()), 3, 6);
        gridLayout.addComponent(createMinusButton(charisma, charismaModifier.getValue()), 4, 6);
        charisma.setReadOnly(true);
    }

    private Button createPlusButton(IntegerField fieldAction, int modifier) {
        Button plusButton = new Button(VaadinIcons.PLUS);
        plusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue() + 1;
            int nbPointToSpend = 0;
            if (value <= MAX_SCORE + modifier) {
                nbPointToSpend = getNbPointsToSpend(value);
                if (pointsToSpend.getValue() >= nbPointToSpend) {
                    pointsToSpend.setValue(pointsToSpend.getValue() - nbPointToSpend);
                    fieldAction.setValue(value);
                }
            } else {
                Notification.show(Messages.getInstance().getMessage("abilityScoreStep.notif.minScore", MAX_SCORE + modifier), Type.HUMANIZED_MESSAGE);
            }
        });
        return plusButton;
    }

    private Button createMinusButton(IntegerField fieldAction, int modifier) {
        Button minusButton = new Button(VaadinIcons.MINUS);
        minusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue();
            int nbPointToSpend = 0;
            if (value > (MIN_SCORE + modifier)) {
                nbPointToSpend = getNbPointsToSpend(value);
                pointsToSpend.setValue(pointsToSpend.getValue() + nbPointToSpend);
                fieldAction.setValue(value - 1);
            } else {
                Notification.show(Messages.getInstance().getMessage("abilityScoreStep.notif.maxScore", MIN_SCORE + modifier), Type.HUMANIZED_MESSAGE);
            }
        });
        return minusButton;
    }

    private int getNbPointsToSpend(int value) {
        int nbPointToSpend = 0;
        if (value >= MIN_SCORE && value <= 13) {
            nbPointToSpend = 1;
        } else if (value >= 14 && value <= MAX_SCORE) {
            nbPointToSpend = 2;
        }
        return nbPointToSpend;
    }

    @Override
    public void onSave(Character entity) {

    }

}
