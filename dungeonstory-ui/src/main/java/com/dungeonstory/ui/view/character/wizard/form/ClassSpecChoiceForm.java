package com.dungeonstory.ui.view.character.wizard.form;

import java.util.List;
import java.util.Optional;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.ListType;
import com.dungeonstory.ui.converter.CollectionToStringListConverter.UnorderedListType;
import com.dungeonstory.ui.converter.DescriptiveEntityCollectionToStringListConverter;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.layout.FormLayoutNoSpace;
import com.vaadin.data.ValueContext;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ClassSpecChoiceForm extends CharacterWizardStepForm<CharacterClass> implements AbstractForm.SavedHandler<CharacterClass> {

    private static final long serialVersionUID = 6382868944768026273L;

    private Character character;

    private ComboBox<ClassSpecialization> classSpecialization;

    private ComboBox<Deity> deity;
    private Label           deityDescription;

    private DSTextArea classDescription;
    private DSLabel    classFeaturesLabel;
    private DSLabel    classFeatures;

    public ClassSpecChoiceForm(Character character) {
        super(CharacterClass.class);
        this.character = character;
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        HorizontalLayout layout = new HorizontalLayout();

        Messages messages = Messages.getInstance();

        VerticalLayout classFieldsLayout = new VerticalLayout();
        classSpecialization = new ComboBox<>("Spécialisation");
        classSpecialization.setEmptySelectionAllowed(false);
        classSpecialization.setWidth(80, Unit.PERCENTAGE);

        deity = new ComboBox<Deity>(messages.getMessage("classStep.deity.label"));
        deity.setVisible(false);
        deity.setWidth(80, Unit.PERCENTAGE);
        deityDescription = new DSLabel().withWidth(80, Unit.PERCENTAGE);

        classFieldsLayout.addComponents(classSpecialization, deity, deityDescription);

        VerticalLayout classDescriptionLayout = new VerticalLayout();
        classDescription = new DSTextArea(messages.getMessage("classStep.class.description")).withFullWidth().withRows(10);

        FormLayoutNoSpace classFeatureLabelLayout = new FormLayoutNoSpace();
        classFeaturesLabel = new DSLabel().withStyleName(ValoTheme.LABEL_H4);
        classFeatureLabelLayout.addComponent(classFeaturesLabel);

        VerticalLayout classFeatureLayout = new VerticalLayout();
        classFeatures = new DSLabel("", ContentMode.HTML);
        classFeatureLayout.addComponents(classFeatures);

        classSpecialization.addValueChangeListener(event -> {
            ClassSpecialization chosenSpec = event.getValue();
            if (chosenSpec != null) {

                if (deity.isVisible()) {
                    deity.clear();
                    deity.setItems(Services.getDeityService().findAllByDomain(chosenSpec));
                }

                // refresh class info
                classDescription.setValue(chosenSpec.getDescription());
                classFeaturesLabel.withCaption(messages.getMessage("classStep.classFeatures.label"));

                DescriptiveEntityCollectionToStringListConverter<List<?>> listConverter = new DescriptiveEntityCollectionToStringListConverter<List<?>>();
                listConverter.setListType(ListType.UNORDERED);
                listConverter.setUnorderedBullet(UnorderedListType.CIRCLE);
                List<ClassSpecLevelFeature> classFeaturesForLevel = chosenSpec.getClassSpecFeatures();
                classFeatures.withContent(listConverter.convertToPresentation(classFeaturesForLevel, new ValueContext()));

            } else {
                hideClassFields();
            }
        });

        deity.addValueChangeListener(event -> {
            deityDescription.setValue(event.getValue() == null ? "" : event.getValue().getDescription());
            adjustButtons();
        });

        classDescriptionLayout.addComponents(classDescription, classFeatureLabelLayout, classFeatureLayout);

        layout.setSizeFull();
        layout.addComponents(classFieldsLayout, classDescriptionLayout);
        layout.setExpandRatio(classFieldsLayout, 1);
        layout.setExpandRatio(classDescriptionLayout, 2);

        return layout;
    }

    private void hideClassFields() {
        showHideDeity(true);

        classDescription.clear();
        classFeaturesLabel.withCaption("").withContent("");
        classFeatures.withCaption("").withContent("");
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();

        classDescription.setReadOnly(true);
        classSpecialization.setItems(getEntity().getClasse().getClassSpecs());

        showHideDeity(false);
    }

    private void showHideDeity(boolean forceHide) {
        Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(getEntity().getClasse(), getEntity().getClassLevel());
        if (!forceHide && classLevelBonusOpt.isPresent() && classLevelBonusOpt.get().getDeity()) {
            deity.setVisible(true);
        } else {
            deity.setVisible(false);
            deity.setValue(null);
            deityDescription.setValue("");
        }
    }

    @Override
    public void onSave(CharacterClass entity) {
        character.getClassFeatures().addAll(ClassUtil.getClassFeaturesForLevel(entity.getClassSpecialization(), entity.getClassLevel()));
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = getBinder().isValid();
            boolean requiredFieldsFilled = true;
            if (deity.isVisible() && deity.getValue() == null) {
                requiredFieldsFilled = false;
            }
            getSaveButton().setEnabled(requiredFieldsFilled && valid);
        }
    }

}
