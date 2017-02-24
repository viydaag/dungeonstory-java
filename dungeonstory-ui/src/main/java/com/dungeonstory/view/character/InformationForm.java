package com.dungeonstory.view.character;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.peter.imagestrip.ImageStrip;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;

import com.dungeonstory.DSConstant;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.ImageFilter;
import com.vaadin.data.Validator;
import com.vaadin.server.FileResource;
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
    private ImageStrip             imageStrip;

    private Map<ImageStrip.Image, String> imageMap;
    private String                        image;

    private AlignmentDataService alignmentService = AlignmentService.getInstance();
    private RegionService        regionService    = RegionService.getInstance();
    
    private FormLayout layout;

    public InformationForm() {
        super();
        setSavedHandler(this);
        imageMap = new HashMap<ImageStrip.Image, String>();
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

        gender.addMValueChangeListener(event -> initImageStrip(event.getValue()));

        initImageStrip(null);

        layout.addComponents(name, gender, age, weight, height, alignment, region, imageStrip);

        return layout;
    }

    private void initImageStrip(Gender gender) {
        if (imageStrip != null) {
            layout.removeComponent(imageStrip);
        }
        
        imageStrip = new ImageStrip(org.vaadin.peter.imagestrip.ImageStrip.Alignment.HORIZONTAL);
        imageStrip.setHeight(200, Unit.PIXELS);
        imageStrip.setCaption(Messages.getInstance().getMessage("informationStep.image.label"));

        // Use animation
        imageStrip.setAnimated(true);

        // Make strip to behave like select
        imageStrip.setSelectable(true);

        // Add ValueChangeListener to listen for image selection
        imageStrip.addValueChangeListener(event -> {
            ImageStrip.Image selectedImage = (ImageStrip.Image) event.getProperty().getValue();
            image = imageMap.get(selectedImage);
        });

        // Set size of the box surrounding the images
        imageStrip.setImageBoxWidth(120);
        imageStrip.setImageBoxHeight(180);

        // Set maximum size of the images
        imageStrip.setImageMaxWidth(110);
        imageStrip.setImageMaxHeight(170);

        // Limit how many images are visible at most simultaneously
        imageStrip.setMaxAllowed(7);

        if (gender != null && gender.getImageDir() != null) {
            File imageDir = new File(DSConstant.getImageDir() + "/" + gender.getImageDir());

            if (imageDir.isDirectory()) { // make sure it's a directory
                for (final File imageFile : imageDir.listFiles(new ImageFilter())) {
                    FileResource resource = new FileResource(imageFile);
                    ImageStrip.Image image = imageStrip.addImage(resource);
                    imageMap.put(image, "/" + gender.getImageDir() + "/" + imageFile.getName());
                }
            }
        }
        
        layout.addComponent(imageStrip);
        
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        age.setValue(getEntity().getRace().getMinAge());
        weight.setValue(getEntity().getRace().getAverageWeight());
        height.setValue(getEntity().getRace().getAverageHeight());
    }

}
