package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.layout.GridSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

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

    private IntegerField pointsToSpend;

    public AbilityScoreInitForm() {
        super();
    }

    @Override
    protected Component createContent() {
        
        VerticalSpacedLayout layout = new VerticalSpacedLayout();

        pointsToSpend = new IntegerField("Points").withWidth("50px");
        pointsToSpend.setValue(27);
        pointsToSpend.setReadOnly(true);

        GridSpacedLayout gridLayout = new GridSpacedLayout(5, 7);
        gridLayout.addComponent(new MLabel("Caractéristique"), 0, 0);
        gridLayout.addComponent(new MLabel("Modificateur de race"), 1, 0);
        gridLayout.addComponent(new MLabel("Score"), 2, 0);
        
        strength = new IntegerField().withWidth("50px");
        strengthModifier = new IntegerField();
        strengthLabel = new MLabel("Force");
        gridLayout.addComponent(strengthLabel, 0, 1);
        gridLayout.addComponent(strengthModifier, 1, 1);
        gridLayout.addComponent(strength, 2, 1);
        gridLayout.addComponent(createPlusButton(strength), 3, 1);
        gridLayout.addComponent(createMinusButton(strength), 4, 1);

        dexterity = new IntegerField().withWidth("50px");
        dexterityModifier = new IntegerField();
        dexterityLabel = new MLabel("Dextérité");
        gridLayout.addComponent(dexterityLabel, 0, 2);
        gridLayout.addComponent(dexterityModifier, 1, 2);
        gridLayout.addComponent(dexterity, 2, 2);
        gridLayout.addComponent(createPlusButton(dexterity), 3, 2);
        gridLayout.addComponent(createMinusButton(dexterity), 4, 2);

        constitution = new IntegerField().withWidth("50px");
        constitutionModifier = new IntegerField();
        constitutionLabel = new MLabel("Constitution");
        gridLayout.addComponent(constitutionLabel, 0, 3);
        gridLayout.addComponent(constitutionModifier, 1, 3);
        gridLayout.addComponent(constitution, 2, 3);
        gridLayout.addComponent(createPlusButton(constitution), 3, 3);
        gridLayout.addComponent(createMinusButton(constitution), 4, 3);

        intelligence = new IntegerField().withWidth("50px");
        intelligenceModifier = new IntegerField();
        intelligenceLabel = new MLabel("Intelligence");
        gridLayout.addComponent(intelligenceLabel, 0, 4);
        gridLayout.addComponent(intelligenceModifier, 1, 4);
        gridLayout.addComponent(intelligence, 2, 4);
        gridLayout.addComponent(createPlusButton(intelligence), 3, 4);
        gridLayout.addComponent(createMinusButton(intelligence), 4, 4);

        wisdom = new IntegerField().withWidth("50px");
        wisdomModifier = new IntegerField();
        wisdomLabel = new MLabel("Sagesse");
        gridLayout.addComponent(wisdomLabel, 0, 5);
        gridLayout.addComponent(wisdomModifier, 1, 5);
        gridLayout.addComponent(wisdom, 2, 5);
        gridLayout.addComponent(createPlusButton(wisdom), 3, 5);
        gridLayout.addComponent(createMinusButton(wisdom), 4, 5);

        charisma = new IntegerField().withWidth("50px");
        charismaModifier = new IntegerField();
        charismaLabel = new MLabel("Charisme");
        gridLayout.addComponent(charismaLabel, 0, 6);
        gridLayout.addComponent(charismaModifier, 1, 6);
        gridLayout.addComponent(charisma, 2, 6);
        gridLayout.addComponent(createPlusButton(charisma), 3, 6);
        gridLayout.addComponent(createMinusButton(charisma), 4, 6);

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
        strength.setReadOnly(true);

        dexterityModifier.setValue(getEntity().getRace().getDexModifier());
        dexterity.setValue(MIN_SCORE + getEntity().getRace().getDexModifier());
        dexterity.setReadOnly(true);

        constitutionModifier.setValue(getEntity().getRace().getConModifier());
        constitution.setValue(MIN_SCORE + getEntity().getRace().getConModifier());
        constitution.setReadOnly(true);

        intelligenceModifier.setValue(getEntity().getRace().getIntModifier());
        intelligence.setValue(MIN_SCORE + getEntity().getRace().getIntModifier());
        intelligence.setReadOnly(true);

        wisdomModifier.setValue(getEntity().getRace().getWisModifier());
        wisdom.setValue(MIN_SCORE + getEntity().getRace().getWisModifier());
        wisdom.setReadOnly(true);

        charismaModifier.setValue(getEntity().getRace().getChaModifier());
        charisma.setValue(MIN_SCORE + getEntity().getRace().getChaModifier());
        charisma.setReadOnly(true);
    }

    private Button createPlusButton(IntegerField fieldAction) {
        Button plusButton = new Button(FontAwesome.PLUS);
        plusButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
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
                }
            }

        });
        return plusButton;
    }

    private Button createMinusButton(IntegerField fieldAction) {
        Button minusButton = new Button(FontAwesome.MINUS);
        minusButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                int value = fieldAction.getValue().intValue();
                int nbPointToSpend = 0;
                if (value > MIN_SCORE) {
                    nbPointToSpend = getNbPointsToSpend(value);
                    pointsToSpend.setReadOnly(false);
                    pointsToSpend.setValue(pointsToSpend.getValue() + nbPointToSpend);
                    pointsToSpend.setReadOnly(true);
                    fieldAction.setReadOnly(false);
                    fieldAction.setValue(value - 1);
                    fieldAction.setReadOnly(true);
                }
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
