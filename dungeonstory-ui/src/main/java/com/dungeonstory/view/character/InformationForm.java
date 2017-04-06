package com.dungeonstory.view.character;

import java.util.List;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.ui.component.DSImage;
import com.dungeonstory.ui.component.ImageSelector;
import com.dungeonstory.ui.factory.ImageFactory;
import com.vaadin.data.Validator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class InformationForm extends DSAbstractForm<Character> implements SavedHandler<Character> {

    private static final long serialVersionUID = -2704789930623304546L;

    private TextField              name;
    private TypedSelect<Gender>    gender;
    private IntegerField           age;
    private IntegerField           weight;
    private TextField              height;
    private TypedSelect<Alignment> alignment;
    private TypedSelect<Region>    region;
    private ImageSelector          imageSelector;
    private String                 image;

    private AlignmentDataService alignmentService = AlignmentService.getInstance();
    private RegionService        regionService    = RegionService.getInstance();

    private FormLayout layout;

    public InformationForm() {
        super();
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

        name = new MTextField(messages.getMessage("informationStep.name.label")).withWidth("250px");
        gender = new TypedSelect<Gender>(messages.getMessage("informationStep.sex.label")).asOptionGroupType();
        gender.setOptions(Gender.values());
        age = new IntegerField(messages.getMessage("informationStep.age.label"));
        weight = new IntegerField(messages.getMessage("informationStep.weight.label"));
        height = new TextField(messages.getMessage("informationStep.height.label"));
        alignment = new TypedSelect<>(messages.getMessage("informationStep.alignment.label"), alignmentService.findAllPlayable()).asComboBoxType()
                .withWidth("250px");
        region = new TypedSelect<>(messages.getMessage("informationStep.region.label"), regionService.findAllOrderBy("name", "ASC")).asComboBoxType()
                .withWidth("250px");

        age.addValidator(new Validator() {

            private static final long serialVersionUID = -2710504516977136307L;

            @Override
            public void validate(Object value) throws InvalidValueException {
                Integer valueInt = (Integer) value;
                int minAge = getEntity().getRace().getMinAge();
                int maxAge = getEntity().getRace().getMaxAge();
                if (valueInt.intValue() < minAge || valueInt.intValue() > maxAge) {
                    throw new InvalidValueException(
                            messages.getMessage("informationStep.age.validator", minAge, maxAge, getEntity().getRace().getName()));
                }
            }
        });

        gender.addMValueChangeListener(event -> initImageSelector(event.getValue()));

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
                DSImage value = (DSImage) event.getProperty().getValue();
                this.image = value.getRelativePath();
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

}
