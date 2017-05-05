package com.dungeonstory.view.character;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.vaadin.viritin.fields.LabelField;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.CharacterDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.ui.component.DSImage;
import com.dungeonstory.ui.component.ImageSelector;
import com.dungeonstory.ui.factory.ImageFactory;
import com.dungeonstory.util.captionGenerator.ClassLevelCaptionGenerator;
import com.dungeonstory.util.field.ImageField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CharacterInfoForm extends AbstractForm<Character> {

    private static final long serialVersionUID = 667927823059386253L;

    private LabelField<String>    name;
    private LabelField<String>    gender;
    private LabelField<Alignment> alignment;
    private LabelField<Region>    region;

    private LabelField<Integer> age;
    private LabelField<Integer> weight;
    private LabelField<String>  height;

    private LabelField<Race>    race;
    private LabelField<Collection<DSClass>> classes;
    private LabelField<Level>   level;
    private LabelField<Long>    experience;
    private LabelField<Integer> lifePoints;

    private LabelField<Integer> strength;
    private LabelField<Integer> dexterity;
    private LabelField<Integer> constitution;
    private LabelField<Integer> intelligence;
    private LabelField<Integer> wisdom;
    private LabelField<Integer> charisma;
    private ImageField          image;
    private ImageSelector       imageSelector;

    private FormLayout infoLayout;

    private Button changeImageButton;
    private Button saveImageButton;

    public CharacterInfoForm() {
        super(Character.class);
    }

    @Override
    protected Component createContent() {

        VerticalLayout layout = new VerticalLayout();

        Messages messages = Messages.getInstance();

        Panel infoPanel = new Panel(messages.getMessage("characterView.info.panel.caption"));
        infoLayout = new FormLayout();
        infoLayout.setMargin(true);
        name = new LabelField<>(messages.getMessage("characterView.name.label"));
        gender = new LabelField<>(messages.getMessage("characterView.gender.label"));
        alignment = new LabelField<>(messages.getMessage("characterView.alignment.label"));
        region = new LabelField<>(messages.getMessage("characterView.region.label"));

        age = new LabelField<>(messages.getMessage("characterView.age.label"));
        weight = new LabelField<>(messages.getMessage("characterView.weight.label"));
        height = new LabelField<>(messages.getMessage("characterView.height.label"));

        HorizontalLayout imageLayout = new HorizontalLayout();
        image = new ImageField();
        changeImageButton = new Button("Changer");
        //        changeImageButton.addClickListener(e -> showImageStrip());
        changeImageButton.addClickListener(e -> showImageSelector());
        saveImageButton = new Button(messages.getMessage("button.save"));
        saveImageButton.addClickListener(e -> saveImage());
        saveImageButton.setVisible(false);
        imageLayout.addComponents(image, changeImageButton, saveImageButton);

        infoLayout.addComponents(name, gender, alignment, region, age, weight, height, imageLayout);
        infoPanel.setContent(infoLayout);

        Panel levelPanel = new Panel();
        FormLayout levelLayout = new FormLayout();
        levelLayout.setMargin(true);
        race = new LabelField<>(messages.getMessage("characterView.race.label"));
        classes = new LabelField<>(messages.getMessage("characterView.class.label"));
        classes.setCaptionGenerator(new ClassLevelCaptionGenerator());
        level = new LabelField<>(messages.getMessage("characterView.level.label"));
        experience = new LabelField<>(messages.getMessage("characterView.experience.label"));
        lifePoints = new LabelField<>(messages.getMessage("characterView.lifePoints.label"));
        levelLayout.addComponents(race, classes, level, experience, lifePoints);
        levelPanel.setContent(levelLayout);

        Panel abilityPanel = new Panel(messages.getMessage("characterView.ability.panel.caption"));
        abilityPanel.setSizeUndefined();
        FormLayout abilityLayout = new FormLayout();
        abilityLayout.setMargin(true);
        strength = new LabelField<>(messages.getMessage("ability.str.caption"));
        dexterity = new LabelField<>(messages.getMessage("ability.dex.caption"));
        constitution = new LabelField<>(messages.getMessage("ability.con.caption"));
        intelligence = new LabelField<>(messages.getMessage("ability.int.caption"));
        wisdom = new LabelField<>(messages.getMessage("ability.wis.caption"));
        charisma = new LabelField<>(messages.getMessage("ability.cha.caption"));
        abilityLayout.addComponents(strength, dexterity, constitution, intelligence, wisdom, charisma);
        abilityPanel.setContent(abilityLayout);

        layout.addComponents(infoPanel, levelPanel, abilityPanel);

        return layout;
    }

    private void showImageSelector() {
        changeImageButton.setVisible(false);
        if (imageSelector == null) {
            createImageSelector();
        }
        if (!imageSelector.isVisible()) {
            imageSelector.setVisible(true);
        }

        //find selected image
        Optional<DSImage> selectedImage = imageSelector.getImages().stream().filter(img -> img.getRelativePath().equals(this.image.getValue()))
                .findFirst();
        if (selectedImage.isPresent()) {
            imageSelector.setValueWithScroll(selectedImage.get());
        }

        infoLayout.addComponent(imageSelector);
    }

    private void createImageSelector() {
        imageSelector = new ImageSelector();
        imageSelector.setMaxAllowed(5);
        imageSelector.setSelectable(true);
        imageSelector.setImageMaxHeight(150);
        imageSelector.setImageMaxWidth(150);

        List<DSImage> imageList = null;
        if (getEntity().getGender().equals(Gender.M)) {
            imageList = ImageFactory.getInstance().getMaleImages();
        } else {
            imageList = ImageFactory.getInstance().getFemaleImages();
        }
        imageSelector.addImages(imageList);
        imageSelector.addValueChangeListener(event -> {
            DSImage value = event.getValue();
            image.setValue(value.getRelativePath());
            saveImageButton.setVisible(true);
        });
    }

    private void saveImage() {
        CharacterDataService service = Services.getCharacterService();
        Character c = service.saveOrUpdate(getEntity());
        setEntity(c);
        saveImageButton.setVisible(false);
        imageSelector.setVisible(false);
        changeImageButton.setVisible(true);
    }

}
