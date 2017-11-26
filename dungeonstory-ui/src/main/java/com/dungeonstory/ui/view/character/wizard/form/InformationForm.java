package com.dungeonstory.ui.view.character.wizard.form;

import java.util.EnumSet;
import java.util.List;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.component.DSImage;
import com.dungeonstory.ui.component.ImageSelector;
import com.dungeonstory.ui.factory.ImageFactory;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.data.ValidationResult;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class InformationForm extends CharacterWizardStepForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = -2704789930623304546L;

    private TextField                name;
    private RadioButtonGroup<Gender> gender;
    private IntegerField             age;
    private IntegerField             weight;
    private TextField                height;
    private ComboBox<Alignment>      alignment;
    private ComboBox<Region>         region;
    private ImageSelector            imageSelector;
    private String                   image;

    private AlignmentDataService      alignmentService = Services.getAlignmentService();
    private DataService<Region, Long> regionService    = Services.getRegionService();

    private FormLayout layout;

    public InformationForm() {
        super(Character.class);
        setSavedHandler(this);
    }

    @Override
    public void onSave(Character entity) {
        entity.setImage(image);
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        layout = new FormLayout();
        layout.setMargin(new MarginInfo(true, true));

        name = new FTextField(messages.getMessage("informationStep.name.label")).withWidth("250px");
        gender = new RadioButtonGroup<Gender>(messages.getMessage("informationStep.sex.label"), EnumSet.allOf(Gender.class));
        age = new DSIntegerField(messages.getMessage("informationStep.age.label"));
        weight = new DSIntegerField(messages.getMessage("informationStep.weight.label"));
        height = new TextField(messages.getMessage("informationStep.height.label"));
        alignment = new ComboBox<>(messages.getMessage("informationStep.alignment.label"), alignmentService.findAllPlayable());
        region = new ComboBox<>(messages.getMessage("informationStep.region.label"), regionService.findAllOrderBy("name", "ASC"));
        
        getBinder().forMemberField(age).withValidator((value, context) -> {
            int minAge = getEntity().getRace().getMinAge();
            int maxAge = getEntity().getRace().getMaxAge();
            if (value.intValue() < minAge || value.intValue() > maxAge) {
                return ValidationResult.error(messages.getMessage("informationStep.age.validator", minAge, maxAge, getEntity().getRace().getName()));
            }
            return ValidationResult.ok();
        });

        gender.addValueChangeListener(event -> initImageSelector(event.getValue()));

        layout.addComponents(name, gender, age, weight, height, alignment, region);

        initImageSelector(null);

        return layout;
    }

    private void initImageSelector(Gender gender) {
        if (imageSelector == null) {
            imageSelector = new ImageSelector();
        } else {
            imageSelector.clear();
        }

        imageSelector.setMaxAllowed(5);
        imageSelector.setSelectable(true);
        imageSelector.setImageMaxHeight(150);
        imageSelector.setImageMaxWidth(150);

        List<DSImage> imageList = null;
        if (gender != null) {
            if (gender.equals(Gender.M)) {
                imageList = ImageFactory.getInstance().getMaleImages();
            } else {
                imageList = ImageFactory.getInstance().getFemaleImages();
            }
            imageSelector.addImages(imageList);
            imageSelector.addValueChangeListener(event -> {
                DSImage value = event.getValue();
                this.image = value.getRelativePath();
                adjustButtons();
            });
        }

        layout.addComponent(imageSelector);
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        age.setValue(getEntity().getRace().getMinAge());
        weight.setValue(getEntity().getRace().getAverageWeight());
        height.setValue(getEntity().getRace().getAverageHeight());
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = getBinder().isValid();
            boolean requiredFieldsFilled = true;
            if (image == null) {
                requiredFieldsFilled = false;
            }
            getSaveButton().setEnabled(requiredFieldsFilled && valid);
        }
    }

}
