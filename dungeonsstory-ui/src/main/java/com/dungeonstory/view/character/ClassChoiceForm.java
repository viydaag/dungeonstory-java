package com.dungeonstory.view.character;

import java.util.Optional;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.fields.config.ListSelectConfig;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ClassChoiceForm extends AbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 6382868944768026273L;

    private ClassService classService = ClassService.getInstance();

    TypedSelect<DSClass> classe;
    private MTextArea    classDescription;
    private MLabel       proficienciesLabel;
    private MLabel       armorProficiencies;
    private MLabel       weaponProficiencies;
    private MLabel       savingThrowProficiencies;
    private MLabel       skillProficiencies;

    public ClassChoiceForm() {
        super();
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();
        classe = new TypedSelect<DSClass>("Choix de classe", classService.findAll())
                .asListSelectType(new ListSelectConfig().withRows((int) classService.count()))
                .withNullSelectionAllowed(false);
        classe.setRequired(true);
        classe.setImmediate(true);
        classe.addValidator(new NullValidator("La classe ets obligatoire", false));
        classe.addMValueChangeListener(event -> {
            getFieldGroup().setBeanModified(true);
            onFieldGroupChange(getFieldGroup());
        });

        VerticalSpacedLayout classDescriptionLayout = new VerticalSpacedLayout();
        classDescription = new MTextArea("Description").withRows(10);

        FormLayout properties = new FormLayout();
        proficienciesLabel = new MLabel();
        armorProficiencies = new MLabel();
        weaponProficiencies = new MLabel();
        savingThrowProficiencies = new MLabel();
        skillProficiencies = new MLabel();

        properties.addComponents(proficienciesLabel, armorProficiencies, weaponProficiencies, savingThrowProficiencies,
                skillProficiencies);

        classe.addMValueChangeListener(event -> {
            if (event.getValue() != null) {
                classDescription.setValue(event.getValue().getDescription());
                proficienciesLabel.withCaption("Maitrises").withStyleName(ValoTheme.LABEL_H4);
                armorProficiencies.withCaption("Armures").withContent(collectionConverter
                        .convertToPresentation(event.getValue().getArmorProficiencies(), String.class, null));
                weaponProficiencies.withCaption("Armes").withContent(collectionConverter
                        .convertToPresentation(event.getValue().getWeaponProficiencies(), String.class, null));
                savingThrowProficiencies.withCaption("Jets de sauvegarde").withContent(collectionConverter
                        .convertToPresentation(event.getValue().getSavingThrowProficiencies(), String.class, null));
                skillProficiencies.withCaption("Compétences").withContent(collectionConverter
                        .convertToPresentation(event.getValue().getBaseSkills(), String.class, null));
            } else {
                classDescription.clear();
                proficienciesLabel.setCaption("");
                armorProficiencies.withCaption("").withContent("");
                weaponProficiencies.withCaption("").withContent("");
                savingThrowProficiencies.withCaption("").withContent("");
                skillProficiencies.withCaption("").withContent("");
            }
        });

        classDescriptionLayout.addComponents(classDescription, properties);

        layout.setSizeFull();
        layout.addComponents(classe, classDescriptionLayout);
        layout.setExpandRatio(classe, 1);
        layout.setExpandRatio(classDescriptionLayout, 2);

        return layout;
    }

    public TypedSelect<DSClass> getClasse() {
        return classe;
    }

    @Override
    public void onSave(Character entity) {
        DSClass chosenClass = getClasse().getValue();
        CharacterClass chosenCharacterClass = null;

        Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(entity, chosenClass);

        if (assignedClass.isPresent()) {
            assignedClass.get().setClassLevel(assignedClass.get().getClassLevel() + 1);
            chosenCharacterClass = assignedClass.get();
        } else {
            CharacterClass classe = new CharacterClass();
            classe.setCharacter(entity);
            classe.setClasse(chosenClass);
            classe.setClassLevel(1);
            entity.getClasses().add(classe);
            chosenCharacterClass = classe;
        }

        entity.getArmorProficiencies().addAll(chosenClass.getArmorProficiencies());
        entity.getSkillProficiencies().addAll(chosenClass.getBaseSkills());
        entity.getWeaponProficiencies().addAll(chosenClass.getWeaponProficiencies());
        entity.getFeats().addAll(ClassUtil.getClassFeaturesForLevel(chosenClass, chosenCharacterClass.getClassLevel()));

    }

}
