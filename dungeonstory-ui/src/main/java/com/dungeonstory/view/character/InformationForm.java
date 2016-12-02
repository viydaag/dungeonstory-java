package com.dungeonstory.view.character;

import java.io.File;

import org.vaadin.peter.imagestrip.ImageStrip;
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
import com.dungeonstory.util.ImageFilter;
import com.vaadin.data.Validator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
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
    private ImageStrip             image;

    private AlignmentDataService alignmentService = AlignmentService.getInstance();
    private RegionService    regionService    = RegionService.getInstance();

    public InformationForm() {
        super();
        setSavedHandler(this);
    }

    @Override
    public void onSave(Character entity) {

    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();
        layout.setMargin(new MarginInfo(true, true));

        name = new MTextField("Nom").withWidth("250px");
        gender = new TypedSelect<Gender>("Sexe").asOptionGroupType();
        gender.setOptions(Gender.values());
        age = new IntegerField("Âge");
        weight = new IntegerField("Poids en lbs");
        height = new TextField("Taille en pieds et pouces");
        alignment = new TypedSelect<>("Alignement", alignmentService.findAllPlayable()).asComboBoxType()
                .withWidth("250px");
        region = new TypedSelect<>("Région d'origine", regionService.findAllOrderBy("name", "ASC")).asComboBoxType()
                .withWidth("250px");

        age.addValidator(new Validator() {

            private static final long serialVersionUID = -2710504516977136307L;

            @Override
            public void validate(Object value) throws InvalidValueException {
                Integer valueInt = (Integer) value;
                int minAge = getEntity().getRace().getMinAge();
                int maxAge = getEntity().getRace().getMaxAge();
                if (valueInt.intValue() < minAge || valueInt.intValue() > maxAge) {
                    throw new InvalidValueException("L'âge doit être entre " + minAge + " et " + maxAge + " pour un "
                            + getEntity().getRace().getName());
                }
            }
        });

        image = new ImageStrip(org.vaadin.peter.imagestrip.ImageStrip.Alignment.HORIZONTAL);
        // Use animation
        image.setAnimated(true);

        // Make strip to behave like select
        image.setSelectable(true);

        // Set size of the box surrounding the images
        image.setImageBoxWidth(140);
        image.setImageBoxHeight(140);

        // Set maximum size of the images
        image.setImageMaxWidth(125);
        image.setImageMaxHeight(125);

        // Limit how many images are visible at most simultaneously
        image.setMaxAllowed(6);

        // Add few images to the strip using different methods
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        File imageDir = new File(basepath + "/WEB-INF/images/");

        if (imageDir.isDirectory()) { // make sure it's a directory
            for (final File imageFile : imageDir.listFiles(new ImageFilter())) {
                FileResource resource = new FileResource(imageFile);
                image.addImage(resource);
            }
        }

        layout.addComponents(name, gender, age, weight, height, alignment, region, image);

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        age.setValue(getEntity().getRace().getMinAge());
        weight.setValue(getEntity().getRace().getAverageWeight());
        height.setValue(getEntity().getRace().getAverageHeight());
    }

}
