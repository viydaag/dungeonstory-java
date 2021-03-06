package com.dungeonstory.ui.view.admin.form;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import org.vaadin.easyuploads.ImagePreviewField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Race.Size;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.enums.ArmorType;
import com.dungeonstory.backend.data.enums.Condition;
import com.dungeonstory.backend.data.enums.DamageType;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.WeaponTypeDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.layout.MultiColumnFormLayout;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RaceForm extends DSAbstractForm<Race> {

    private static final long serialVersionUID = 831509196632558212L;

    private TextField name;
    private TextArea  description;
    private TextArea  traits;

    private EnumComboBox<Size>                      size;
    private SubSetSelector<Language, Set<Language>> languages;
    private FormCheckBox                            extraLanguage;

    private IntegerField strModifier;
    private IntegerField dexModifier;
    private IntegerField conModifier;
    private IntegerField intModifier;
    private IntegerField wisModifier;
    private IntegerField chaModifier;

    private IntegerField minAge;
    private IntegerField maxAge;
    private TextField    averageHeight;
    private IntegerField averageWeight;
    private IntegerField speed;

    private SubSetSelector<Condition, Set<Condition>>                                 savingThrowProficiencies;
    private SubSetSelector<ArmorType.ProficiencyType, Set<ArmorType.ProficiencyType>> armorProficiencies;
    private SubSetSelector<WeaponType, Set<WeaponType>>                               weaponProficiencies;
    private SubSetSelector<Skill, Set<Skill>>                                         skillProficiencies;
    private SubSetSelector<DamageType, Set<DamageType>>                               damageResistance;
    private ImagePreviewField                                                         image;

    private WeaponTypeDataService weaponTypeService = null;

    public RaceForm() {
        super(Race.class);
        weaponTypeService = Services.getWeaponTypeService();
    }

    @Override
    public String toString() {
        return "Races";
    }

    @Override
    protected Component createContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(new MarginInfo(true, false));

        name = new FTextField("Nom");
        description = new FTextArea("Description").withFullWidth().withRows(10);
        traits = new FTextArea("Traits").withFullWidth().withRows(10);

        languages = new SubSetSelector<Language, Set<Language>>(Language.class);
        languages.setCaption("Langages de base");
        languages.getGrid().addColumn(Language::getName).setCaption("Langage").setId("languages");
        languages.getGrid().setColumnOrder("languages");
        languages.setItems(EnumSet.allOf(Language.class));
        languages.setValue(EnumSet.noneOf(Language.class)); // nothing selected
        languages.setWidth("50%");

        extraLanguage = new FormCheckBox("Langage extra");
        size = new EnumComboBox<Size>(Size.class, "Type de grandeur");
        speed = new DSIntegerField("Vitesse de déplacement en 1 round (en pieds)").withWidth(100, Unit.PIXELS);
        
        damageResistance = new SubSetSelector<>(DamageType.class);
        damageResistance.setCaption("Résistance aux dommages");
        damageResistance.getGrid().addColumn(DamageType::getName).setCaption("Type").setId("damageType");
        damageResistance.getGrid().setColumnOrder("damageType");
        damageResistance.setItems(EnumSet.allOf(DamageType.class));
        damageResistance.setValue(EnumSet.noneOf(DamageType.class)); // nothing selected
        damageResistance.setWidth("50%");

        strModifier = new DSIntegerField("Modificateur de force");
        dexModifier = new DSIntegerField("Modificateur de dextérité");
        conModifier = new DSIntegerField("Modificateur de constitution");
        intModifier = new DSIntegerField("Modificateur d'intelligence");
        wisModifier = new DSIntegerField("Modificateur de sagesse");
        chaModifier = new DSIntegerField("Modificateur de charisme");

        minAge = new DSIntegerField("Âge mimimum").withWidth(100, Unit.PIXELS);
        maxAge = new DSIntegerField("Âge maximum").withWidth(100, Unit.PIXELS);
        averageHeight = new FTextField("Taille moyenne (en pieds/pouce)").withWidth(100, Unit.PIXELS);
        averageWeight = new DSIntegerField("Poids moyen (en lbs)").withWidth(100, Unit.PIXELS);

        savingThrowProficiencies = new SubSetSelector<>(Condition.class);
        savingThrowProficiencies.setCaption("Avantage au jet de sauvegarde contre");
        savingThrowProficiencies.getGrid().addColumn(Condition::getName).setCaption("Condition").setId("savingThrowProficiencies");
        savingThrowProficiencies.setItems(Arrays.asList(Condition.values()));
        savingThrowProficiencies.setValue(null); // nothing selected
        savingThrowProficiencies.setWidth("50%");

        armorProficiencies = new SubSetSelector<>(ArmorType.ProficiencyType.class);
        armorProficiencies.setCaption("Maitrises d'armure");
        armorProficiencies.getGrid().addColumn(ArmorType.ProficiencyType::getName).setCaption("Maitrise").setId("armorProficiencies");
        armorProficiencies.setItems(Arrays.asList(ArmorType.ProficiencyType.values()));
        armorProficiencies.setValue(null); // nothing selected
        armorProficiencies.setWidth("50%");

        weaponProficiencies = new SubSetSelector<>(WeaponType.class);
        weaponProficiencies.setCaption("Maitrises d'arme");
        weaponProficiencies.getGrid().addColumn(WeaponType::getName).setCaption("Maitrise").setId("weaponProficiencies");
        weaponProficiencies.setItems(weaponTypeService.findAll());
        weaponProficiencies.setValue(null); // nothing selected
        weaponProficiencies.setWidth("50%");

        skillProficiencies = new SubSetSelector<>(Skill.class);
        skillProficiencies.setCaption("Maitrise de compétence");
        skillProficiencies.getGrid().addColumn(Skill::getName).setCaption("Compétence").setId("name");
        skillProficiencies.getGrid().addColumn(Skill::getKeyAbility).setCaption("Caractéristique clé").setId(
                "keyAbility.name");
        skillProficiencies.setItems(EnumSet.allOf(Skill.class));
        skillProficiencies.setWidth("80%");
        skillProficiencies.setValue(null); // nothing selected

        image = new ImagePreviewField();
        image.setCaption("Image");
        image.setButtonCaption("Choisir une image");

        FormLayout nameForm = new FormLayout();
        nameForm.addComponents(name, description, traits, size);

        MultiColumnFormLayout abilityForm = new MultiColumnFormLayout(2, "Modificateurs");
        abilityForm.addComponents(0, strModifier, dexModifier, conModifier);
        abilityForm.addComponents(1, intModifier, wisModifier, chaModifier);

        MultiColumnFormLayout infoForm = new MultiColumnFormLayout(2);
        infoForm.addComponents(0, averageHeight, averageWeight, speed);
        infoForm.addComponents(1, minAge, maxAge);

        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(languages);
        formLayout.addComponent(extraLanguage);
        formLayout.addComponent(savingThrowProficiencies);
        formLayout.addComponent(armorProficiencies);
        formLayout.addComponent(weaponProficiencies);
        formLayout.addComponent(skillProficiencies);
        formLayout.addComponent(damageResistance);
        formLayout.addComponent(image);

        formLayout.addComponent(getToolbar());

        layout.addComponents(nameForm, abilityForm, infoForm, formLayout);

        return layout;
    }

}
