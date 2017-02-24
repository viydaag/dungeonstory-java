package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.layout.GridSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

public abstract class AbilityScoreForm extends DSAbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 1620075615699297395L;

    protected IntegerField strength;
    protected IntegerField dexterity;
    protected IntegerField constitution;
    protected IntegerField intelligence;
    protected IntegerField wisdom;
    protected IntegerField charisma;

    protected MLabel strengthLabel;
    protected MLabel dexterityLabel;
    protected MLabel constitutionLabel;
    protected MLabel intelligenceLabel;
    protected MLabel wisdomLabel;
    protected MLabel charismaLabel;

    protected IntegerField         pointsToSpend;
    protected GridSpacedLayout     gridLayout;
    protected VerticalSpacedLayout abilityLayout;
    private VerticalSpacedLayout   layout;

    public AbilityScoreForm() {
        super();
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        layout = new VerticalSpacedLayout();
        abilityLayout = new VerticalSpacedLayout();

        pointsToSpend = new IntegerField(messages.getMessage("abilityScoreStep.points.label")).withWidth("50px");

        if (Configuration.getInstance().isDebug()) {
            Button assignAll = new Button("assign");
            assignAll.addClickListener(event -> {
                pointsToSpend.setReadOnly(false);
                pointsToSpend.setValue(0);
                pointsToSpend.setReadOnly(true);
                getFieldGroup().setBeanModified(true);
                onFieldGroupChange(getFieldGroup());
            });
            abilityLayout.addComponent(assignAll);
        }

        gridLayout = new GridSpacedLayout(5, 7);
        gridLayout.addComponent(new MLabel(messages.getMessage("abilityScoreStep.ability.label")), 0, 0);
        gridLayout.addComponent(new MLabel(messages.getMessage("abilityScoreStep.score.label")), 1, 0);

        strength = new IntegerField().withWidth("50px");
        strengthLabel = new MLabel(messages.getMessage("ability.str.caption"));
        gridLayout.addComponent(strengthLabel, 0, 1);
        gridLayout.addComponent(strength, 1, 1);

        dexterity = new IntegerField().withWidth("50px");
        dexterityLabel = new MLabel(messages.getMessage("ability.dex.caption"));
        gridLayout.addComponent(dexterityLabel, 0, 2);
        gridLayout.addComponent(dexterity, 1, 2);

        constitution = new IntegerField().withWidth("50px");
        constitutionLabel = new MLabel(messages.getMessage("ability.con.caption"));
        gridLayout.addComponent(constitutionLabel, 0, 3);
        gridLayout.addComponent(constitution, 1, 3);

        intelligence = new IntegerField().withWidth("50px");
        intelligenceLabel = new MLabel(messages.getMessage("ability.int.caption"));
        gridLayout.addComponent(intelligenceLabel, 0, 4);
        gridLayout.addComponent(intelligence, 1, 4);

        wisdom = new IntegerField().withWidth("50px");
        wisdomLabel = new MLabel(messages.getMessage("ability.wis.caption"));
        gridLayout.addComponent(wisdomLabel, 0, 5);
        gridLayout.addComponent(wisdom, 1, 5);

        charisma = new IntegerField().withWidth("50px");
        charismaLabel = new MLabel(messages.getMessage("ability.cha.caption"));
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
        if (isEagerValidation() && isBound()) {
            boolean beanModified = getFieldGroup().isBeanModified();
            boolean allPointsSpent = pointsToSpend.getValue() == 0;
            getSaveButton().setEnabled(beanModified && allPointsSpent && isValid());
        }
    }

    public VerticalSpacedLayout getLayout() {
        return layout;
    }

}
