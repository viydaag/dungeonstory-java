package com.dungeonstory.ui.view.character.wizard.form;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class AbilityScoreForm extends CharacterWizardStepForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 1620075615699297395L;

    protected IntegerField strength;
    protected IntegerField dexterity;
    protected IntegerField constitution;
    protected IntegerField intelligence;
    protected IntegerField wisdom;
    protected IntegerField charisma;

    protected DSLabel strengthLabel;
    protected DSLabel dexterityLabel;
    protected DSLabel constitutionLabel;
    protected DSLabel intelligenceLabel;
    protected DSLabel wisdoDSLabel;
    protected DSLabel charismaLabel;

    protected IntegerField   pointsToSpend;
    protected GridLayout     gridLayout;
    protected VerticalLayout abilityLayout;
    private VerticalLayout   layout;

    public AbilityScoreForm() {
        super(Character.class);
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        layout = new VerticalLayout();
        abilityLayout = new VerticalLayout();

        pointsToSpend = new DSIntegerField(messages.getMessage("abilityScoreStep.points.label")).withWidth("50px")
                                                                                                .withId("remainingPoints");

        if (Configuration.getInstance().isDebug()) {
            Button assignAll = new Button("assign");
            assignAll.addClickListener(event -> {
                pointsToSpend.setValue(0);
                adjustButtons();
            });
            abilityLayout.addComponent(assignAll);
        }

        gridLayout = new GridLayout(5, 7);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new DSLabel(messages.getMessage("abilityScoreStep.ability.label")), 0, 0);
        gridLayout.addComponent(new DSLabel(messages.getMessage("abilityScoreStep.score.label")), 1, 0);

        strength = new DSIntegerField().withWidth("50px");
        strengthLabel = new DSLabel(messages.getMessage("ability.str.caption"));
        gridLayout.addComponent(strengthLabel, 0, 1);
        gridLayout.addComponent(strength, 1, 1);

        dexterity = new DSIntegerField().withWidth("50px");
        dexterityLabel = new DSLabel(messages.getMessage("ability.dex.caption"));
        gridLayout.addComponent(dexterityLabel, 0, 2);
        gridLayout.addComponent(dexterity, 1, 2);

        constitution = new DSIntegerField().withWidth("50px");
        constitutionLabel = new DSLabel(messages.getMessage("ability.con.caption"));
        gridLayout.addComponent(constitutionLabel, 0, 3);
        gridLayout.addComponent(constitution, 1, 3);

        intelligence = new DSIntegerField().withWidth("50px");
        intelligenceLabel = new DSLabel(messages.getMessage("ability.int.caption"));
        gridLayout.addComponent(intelligenceLabel, 0, 4);
        gridLayout.addComponent(intelligence, 1, 4);

        wisdom = new DSIntegerField().withWidth("50px");
        wisdoDSLabel = new DSLabel(messages.getMessage("ability.wis.caption"));
        gridLayout.addComponent(wisdoDSLabel, 0, 5);
        gridLayout.addComponent(wisdom, 1, 5);

        charisma = new DSIntegerField().withWidth("50px");
        charismaLabel = new DSLabel(messages.getMessage("ability.cha.caption"));
        gridLayout.addComponent(charismaLabel, 0, 6);
        gridLayout.addComponent(charisma, 1, 6);

        gridLayout.setColumnExpandRatio(0, 1);
        gridLayout.setColumnExpandRatio(1, 1);

        abilityLayout.addComponents(pointsToSpend, gridLayout);

        layout.addComponents(abilityLayout);

        return layout;
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = getBinder().isValid();
            boolean allPointsSpent = pointsToSpend.getValue() == 0;
            getSaveButton().setEnabled(allPointsSpent && valid);
        }
    }

    public VerticalLayout getLayout() {
        return layout;
    }

}
