package com.dungeonstory.ui.view.admin.form;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.vaadin.easyuploads.ImagePreviewField;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Condition;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Race.Size;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.service.DamageTypeDataService;
import com.dungeonstory.backend.service.LanguageDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SkillDataService;
import com.dungeonstory.backend.service.WeaponTypeDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.SubSetSelector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

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
    private ComboBox<DamageType>                                                      damageResistance;
    private ImagePreviewField                                                         image;

    private LanguageDataService   languageService   = null;
    private SkillDataService      skillService      = null;
    private WeaponTypeDataService weaponTypeService = null;
    private DamageTypeDataService damageTypeService = null;

    public RaceForm() {
        super(Race.class);
        languageService = Services.getLanguageService();
        skillService = Services.getSkillService();
        weaponTypeService = Services.getWeaponTypeService();
        damageTypeService = Services.getDamageTypeService();
    }

    @Override
    public String toString() {
        return "Races";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new DSTextArea("Description").withFullWidth().withRows(10);
        traits = new DSTextArea("Traits").withFullWidth().withRows(10);

        languages = new SubSetSelector<Language, Set<Language>>(Language.class);
        languages.setCaption("Langages de base");
        languages.getGrid().addColumn(Language::getName).setCaption("Langage").setId("languages");
        languages.getGrid().setColumnOrder("languages");
        languages.setItems(languageService.findAll());
        languages.setValue(new HashSet<>()); // nothing selected
        languages.setWidth("50%");

        extraLanguage = new FormCheckBox("Langage extra");
        size = new EnumComboBox<Size>(Size.class, "Type de grandeur");
        speed = new IntegerField("Vitesse de déplacement en 1 round (en pieds)");
        damageResistance = new ComboBox<>("Résistance au dommage", damageTypeService.findAll());

        strModifier = new IntegerField("Modificateur de force");
        dexModifier = new IntegerField("Modificateur de dextérité");
        conModifier = new IntegerField("Modificateur de constitution");
        intModifier = new IntegerField("Modificateur d'intelligence");
        wisModifier = new IntegerField("Modificateur de sagesse");
        chaModifier = new IntegerField("Modificateur de charisme");

        minAge = new IntegerField("Âge mimimum");
        maxAge = new IntegerField("Âge maximum");
        averageHeight = new MTextField("Taille moyenne (en pieds/pouce)");
        averageWeight = new IntegerField("Poids moyen (en lbs)");

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
        skillProficiencies.getGrid().addColumn(Skill::getKeyAbility).setCaption("Caractéristique clé").setId("keyAbility.name");
        skillProficiencies.setItems(skillService.findAll());
        skillProficiencies.setWidth("80%");
        skillProficiencies.setValue(null); // nothing selected

        image = new ImagePreviewField();
        image.setCaption("Image");
        image.setButtonCaption("Choisir une image");

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(traits);
        layout.addComponent(size);
        layout.addComponent(languages);
        layout.addComponent(extraLanguage);

        layout.addComponent(strModifier);
        layout.addComponent(dexModifier);
        layout.addComponent(conModifier);
        layout.addComponent(intModifier);
        layout.addComponent(wisModifier);
        layout.addComponent(chaModifier);

        layout.addComponent(minAge);
        layout.addComponent(maxAge);
        layout.addComponent(averageHeight);
        layout.addComponent(averageWeight);
        layout.addComponent(speed);

        layout.addComponent(savingThrowProficiencies);
        layout.addComponent(armorProficiencies);
        layout.addComponent(weaponProficiencies);
        layout.addComponent(skillProficiencies);
        layout.addComponent(damageResistance);
        layout.addComponent(image);

        layout.addComponent(getToolbar());

        return layout;
    }

}
