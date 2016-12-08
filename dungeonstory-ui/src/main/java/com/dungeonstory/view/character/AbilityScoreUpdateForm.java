package com.dungeonstory.view.character;

import java.util.Arrays;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;

public class AbilityScoreUpdateForm extends AbilityScoreForm {

    private static final long serialVersionUID = 1307298207679565178L;

    private final int MAX_SCORE = 20;

    private Character       backupEntity;
    private FeatDataService featService = null;

    private EnumSelect<UpdateType> updateType;
    private TypedSelect<Feat>      featChoice;
    private Label                  featDescription;
    private HorizontalSpacedLayout featLayout;

    public enum UpdateType {
        ABILITY("Caractéristique"), FEAT("Don");

        private String value;

        private UpdateType(String name) {
            this.value = name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getValue();
        }
    }

    public AbilityScoreUpdateForm(Character character) {
        super();
        featService = FeatService.getInstance();
        backupEntity = character;
    }

    @Override
    protected Component createContent() {
        Component content = super.createContent();
        featLayout = new HorizontalSpacedLayout();
        featLayout.setWidth(100, Unit.PERCENTAGE);

        updateType = new EnumSelect<UpdateType>("Choix d'amélioration").withSelectType(OptionGroup.class);
        updateType.setBeans(Arrays.asList(UpdateType.values()));
        updateType.addMValueChangeListener(event -> {
            if (event.getValue() != null) {
                abilityLayout.setVisible(event.getValue() == UpdateType.ABILITY);
                featLayout.setVisible(event.getValue() == UpdateType.FEAT);

                if (event.getValue() == UpdateType.ABILITY) {
                    featChoice.setValue(null);
                } else {
                    resetPointToSpend();

                    strength.setReadOnly(false);
                    dexterity.setReadOnly(false);
                    constitution.setReadOnly(false);
                    intelligence.setReadOnly(false);
                    wisdom.setReadOnly(false);
                    charisma.setReadOnly(false);

                    strength.setValue(backupEntity.getStrength());
                    dexterity.setValue(backupEntity.getDexterity());
                    constitution.setValue(backupEntity.getConstitution());
                    intelligence.setValue(backupEntity.getIntelligence());
                    wisdom.setValue(backupEntity.getWisdom());
                    charisma.setValue(backupEntity.getCharisma());

                    strength.setReadOnly(true);
                    dexterity.setReadOnly(true);
                    constitution.setReadOnly(true);
                    intelligence.setReadOnly(true);
                    wisdom.setReadOnly(true);
                    charisma.setReadOnly(true);
                }
                onFieldGroupChange(getFieldGroup());
            }
        });

        getLayout().addComponentAsFirst(updateType);

        resetPointToSpend();

        featChoice = new TypedSelect<Feat>(Feat.class).withCaption("Choix de don").asComboBoxType().withFullWidth();
        featDescription = new MLabel();
        featLayout.addComponents(featChoice, featDescription);

        getLayout().addComponent(featLayout);

        return content;
    }

    private void resetPointToSpend() {
        pointsToSpend.setReadOnly(false);
        pointsToSpend.setValue(2);
        pointsToSpend.setReadOnly(true);
    }

    @Override
    public void afterSetEntity() {

        featChoice.setBeans(featService.findAllUnassignedFeats(getEntity()));
        featChoice.addMValueChangeListener(event -> {
            if (event.getValue() != null) {
                featDescription.setValue(event.getValue().getDescription());
            } else {
                featDescription.setValue("");
            }
            onFieldGroupChange(getFieldGroup());
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
        if (featChoice.getValue() != null) {
            entity.getFeats().add(featChoice.getValue());
        }
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isEagerValidation() && isBound()) {
            if (updateType.getValue() == UpdateType.ABILITY) {
                boolean beanModified = getFieldGroup().isBeanModified();
                boolean allPointsSpent = pointsToSpend.getValue() == 0;
                getSaveButton().setEnabled(beanModified && allPointsSpent && isValid());
            } else {
                getSaveButton().setEnabled(featChoice.getValue() != null);
            }
        }
    }

}
