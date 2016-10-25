package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.layout.GridSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class AbilityScoreInitForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = -708599071923669623L;
    
    private IntegerField strength;
    private IntegerField dexterity;
    private IntegerField constitution;
    private IntegerField intelligence;
    private IntegerField wisdom;
    private IntegerField charisma;

    private IntegerField strengthModifier;
    private IntegerField dexterityModifier;
    private IntegerField constitutionModifier;
    private IntegerField intelligenceModifier;
    private IntegerField wisdomModifier;
    private IntegerField charismaModifier;

    private MLabel strengthLabel;
    private MLabel dexterityLabel;
    private MLabel constitutionLabel;
    private MLabel intelligenceLabel;
    private MLabel wisdomLabel;
    private MLabel charismaLabel;
    
    private final int MIN_SCORE = 8;
    private final int MAX_SCORE = 15;

    IntegerField pointsToSpend;
    GridSpacedLayout gridLayout;

    public AbilityScoreInitForm() {
        super();
    }

    @Override
    protected Component createContent() {
        
        VerticalSpacedLayout layout = new VerticalSpacedLayout();

        pointsToSpend = new IntegerField("Points").withWidth("50px");
        pointsToSpend.setValue(27);
        pointsToSpend.setReadOnly(true);

        if (Configuration.getInstance().isDebug()) {
            Button assignAll = new Button("assign");
            assignAll.addClickListener(event -> {
                pointsToSpend.setReadOnly(false);
                pointsToSpend.setValue(0);
                pointsToSpend.setReadOnly(true);
            });
            layout.addComponent(assignAll);
        }

        gridLayout = new GridSpacedLayout(5, 7);
        gridLayout.addComponent(new MLabel("Caractéristique"), 0, 0);
        gridLayout.addComponent(new MLabel("Modificateur de race"), 1, 0);
        gridLayout.addComponent(new MLabel("Score"), 2, 0);
        
        strength = new IntegerField().withWidth("50px");
        strengthModifier = new IntegerField();
        strengthLabel = new MLabel("Force");
        gridLayout.addComponent(strengthLabel, 0, 1);
        gridLayout.addComponent(strengthModifier, 1, 1);
        gridLayout.addComponent(strength, 2, 1);

        dexterity = new IntegerField().withWidth("50px");
        dexterityModifier = new IntegerField();
        dexterityLabel = new MLabel("Dextérité");
        gridLayout.addComponent(dexterityLabel, 0, 2);
        gridLayout.addComponent(dexterityModifier, 1, 2);
        gridLayout.addComponent(dexterity, 2, 2);

        constitution = new IntegerField().withWidth("50px");
        constitutionModifier = new IntegerField();
        constitutionLabel = new MLabel("Constitution");
        gridLayout.addComponent(constitutionLabel, 0, 3);
        gridLayout.addComponent(constitutionModifier, 1, 3);
        gridLayout.addComponent(constitution, 2, 3);

        intelligence = new IntegerField().withWidth("50px");
        intelligenceModifier = new IntegerField();
        intelligenceLabel = new MLabel("Intelligence");
        gridLayout.addComponent(intelligenceLabel, 0, 4);
        gridLayout.addComponent(intelligenceModifier, 1, 4);
        gridLayout.addComponent(intelligence, 2, 4);

        wisdom = new IntegerField().withWidth("50px");
        wisdomModifier = new IntegerField();
        wisdomLabel = new MLabel("Sagesse");
        gridLayout.addComponent(wisdomLabel, 0, 5);
        gridLayout.addComponent(wisdomModifier, 1, 5);
        gridLayout.addComponent(wisdom, 2, 5);

        charisma = new IntegerField().withWidth("50px");
        charismaModifier = new IntegerField();
        charismaLabel = new MLabel("Charisme");
        gridLayout.addComponent(charismaLabel, 0, 6);
        gridLayout.addComponent(charismaModifier, 1, 6);
        gridLayout.addComponent(charisma, 2, 6);

        gridLayout.setColumnExpandRatio(0, 1);
        gridLayout.setColumnExpandRatio(1, 1);
        gridLayout.setColumnExpandRatio(2, 1);

        layout.addComponents(pointsToSpend, gridLayout);


        return layout;
    }

    @Override
    public void afterSetEntity() {
        strengthModifier.setValue(getEntity().getRace().getStrModifier());
        strength.setValue(MIN_SCORE + getEntity().getRace().getStrModifier());
        gridLayout.addComponent(createPlusButton(strength), 3, 1);
        gridLayout.addComponent(createMinusButton(strength, strengthModifier.getValue()), 4, 1);
        strength.setReadOnly(true);

        dexterityModifier.setValue(getEntity().getRace().getDexModifier());
        dexterity.setValue(MIN_SCORE + getEntity().getRace().getDexModifier());
        gridLayout.addComponent(createPlusButton(dexterity), 3, 2);
        gridLayout.addComponent(createMinusButton(dexterity, dexterityModifier.getValue()), 4, 2);
        dexterity.setReadOnly(true);

        constitutionModifier.setValue(getEntity().getRace().getConModifier());
        constitution.setValue(MIN_SCORE + getEntity().getRace().getConModifier());
        gridLayout.addComponent(createPlusButton(constitution), 3, 3);
        gridLayout.addComponent(createMinusButton(constitution, constitutionModifier.getValue()), 4, 3);
        constitution.setReadOnly(true);

        intelligenceModifier.setValue(getEntity().getRace().getIntModifier());
        intelligence.setValue(MIN_SCORE + getEntity().getRace().getIntModifier());
        gridLayout.addComponent(createPlusButton(intelligence), 3, 4);
        gridLayout.addComponent(createMinusButton(intelligence, intelligenceModifier.getValue()), 4, 4);
        intelligence.setReadOnly(true);

        wisdomModifier.setValue(getEntity().getRace().getWisModifier());
        wisdom.setValue(MIN_SCORE + getEntity().getRace().getWisModifier());
        gridLayout.addComponent(createPlusButton(wisdom), 3, 5);
        gridLayout.addComponent(createMinusButton(wisdom, wisdomModifier.getValue()), 4, 5);
        wisdom.setReadOnly(true);

        charismaModifier.setValue(getEntity().getRace().getChaModifier());
        charisma.setValue(MIN_SCORE + getEntity().getRace().getChaModifier());
        gridLayout.addComponent(createPlusButton(charisma), 3, 6);
        gridLayout.addComponent(createMinusButton(charisma, charismaModifier.getValue()), 4, 6);
        charisma.setReadOnly(true);
    }

    private Button createPlusButton(IntegerField fieldAction) {
        Button plusButton = new Button(FontAwesome.PLUS);
        plusButton.addClickListener(event -> {
                int value = fieldAction.getValue().intValue() + 1;
                int nbPointToSpend = 0;
                if (value <= MAX_SCORE) {
                    nbPointToSpend = getNbPointsToSpend(value);
                    if (pointsToSpend.getValue() >= nbPointToSpend) {
                        pointsToSpend.setReadOnly(false);
                        pointsToSpend.setValue(pointsToSpend.getValue() - nbPointToSpend);
                        pointsToSpend.setReadOnly(true);
                        fieldAction.setReadOnly(false);
                        fieldAction.setValue(value);
                        fieldAction.setReadOnly(true);
                    }
                } else {
                    Notification.show("Le score maximum est 15", Type.HUMANIZED_MESSAGE);
                }
        });
        return plusButton;
    }

    private Button createMinusButton(IntegerField fieldAction, int modifier) {
        Button minusButton = new Button(FontAwesome.MINUS);
        minusButton.addClickListener(event -> {
                int value = fieldAction.getValue().intValue();
                int nbPointToSpend = 0;
                if (value > (MIN_SCORE + modifier)) {
                    nbPointToSpend = getNbPointsToSpend(value);
                    pointsToSpend.setReadOnly(false);
                    pointsToSpend.setValue(pointsToSpend.getValue() + nbPointToSpend);
                    pointsToSpend.setReadOnly(true);
                    fieldAction.setReadOnly(false);
                    fieldAction.setValue(value - 1);
                    fieldAction.setReadOnly(true);
                } else {
                    Notification.show("Le score minimum est " + (MIN_SCORE + modifier), Type.HUMANIZED_MESSAGE);
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

}
