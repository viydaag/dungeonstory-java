package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.Character;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class AbilityScoreUpdateForm extends AbilityScoreForm {

    private static final long serialVersionUID = 8013063449371444637L;

    private final int MAX_SCORE = 20;

    public AbilityScoreUpdateForm() {
        super();
    }

    @Override
    protected Component createContent() {
        Component content = super.createContent();

        pointsToSpend.setReadOnly(false);
        pointsToSpend.setValue(2);
        pointsToSpend.setReadOnly(true);

        return content;
    }

    @Override
    public void afterSetEntity() {
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
        Button plusButton = new Button(FontAwesome.PLUS);
        plusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue() + 1;
            int nbPointToSpend = 1;
            if (value <= MAX_SCORE) {
                if (pointsToSpend.getValue() >= nbPointToSpend) {
                    pointsToSpend.setReadOnly(false);
                    pointsToSpend.setValue(pointsToSpend.getValue() - nbPointToSpend);
                    pointsToSpend.setReadOnly(true);
                    fieldAction.setReadOnly(false);
                    fieldAction.setValue(value);
                    fieldAction.setReadOnly(true);
                }
            } else {
                Notification.show("Le score maximum est " + MAX_SCORE, Type.HUMANIZED_MESSAGE);
            }
        });
        return plusButton;
    }

    private Button createMinusButton(IntegerField fieldAction, int minValue) {
        Button minusButton = new Button(FontAwesome.MINUS);
        minusButton.addClickListener(event -> {
            int value = fieldAction.getValue().intValue();
            int nbPointToSpend = 1;
            if (value > minValue) {
                pointsToSpend.setReadOnly(false);
                pointsToSpend.setValue(pointsToSpend.getValue() + nbPointToSpend);
                pointsToSpend.setReadOnly(true);
                fieldAction.setReadOnly(false);
                fieldAction.setValue(value - 1);
                fieldAction.setReadOnly(true);
            } else {
                Notification.show("Vous ne pouvez pas diminuer une valeur initiale", Type.HUMANIZED_MESSAGE);
            }
        });
        return minusButton;
    }

    @Override
    public void onSave(Character entity) {

    }

}
