package com.dungeonstory.form;

import java.util.Arrays;

import org.vaadin.easyuploads.ImagePreviewField;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Condition;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Race.Size;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class RaceForm extends DSAbstractForm<Race> {

    private static final long serialVersionUID = 831509196632558212L;

    private TextField name;
    private TextArea  description;
    private TextArea  traits;

    private EnumSelect<Size>           size;
    private DSSubSetSelector<Language> languages;
    private FormCheckBox               extraLanguage;

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

    private DSSubSetSelector<Condition>                 savingThrowProficiencies;
    private DSSubSetSelector<ArmorType.ProficiencyType> armorProficiencies;
    private DSSubSetSelector<WeaponType>                weaponProficiencies;
    private DSSubSetSelector<Skill>                     skillProficiencies;
    private TypedSelect<DamageType>                     damageResistance;
    private ImagePreviewField                           image;

    private DataService<Language, Long>   languageService   = null;
    private DataService<Skill, Long>      skillService      = null;
    private DataService<WeaponType, Long> weaponTypeService = null;
    private DataService<DamageType, Long> damageTypeService = null;

    public RaceForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            languageService = MockLanguageService.getInstance();
            skillService = MockSkillService.getInstance();
            weaponTypeService = MockWeaponTypeService.getInstance();
            damageTypeService = MockDamageTypeService.getInstance();
        } else {
            languageService = LanguageService.getInstance();
            skillService = SkillService.getInstance();
            weaponTypeService = WeaponTypeService.getInstance();
            damageTypeService = DamageTypeService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Races";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();
        traits = new MTextArea("Traits");

        languages = new DSSubSetSelector<Language>(Language.class);
        languages.setCaption("Langages de base");
        languages.setVisibleProperties("name");
        languages.setColumnHeader("name", "Langage");
        languages.setOptions(languageService.findAll());
        languages.setValue(null); // nothing selected
        languages.setWidth("50%");

        extraLanguage = new FormCheckBox("Langage extra");
        size = new EnumSelect<Size>("Type de grandeur");
        speed = new IntegerField("Vitesse de déplacement en 1 round (en pieds)");
        damageResistance = new TypedSelect<DamageType>("Résistance au dommage", damageTypeService.findAll());

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

        savingThrowProficiencies = new DSSubSetSelector<Condition>(Condition.class);
        savingThrowProficiencies.setCaption("Avantage au jet de sauvegarde contre");
        savingThrowProficiencies.setVisibleProperties("name");
        savingThrowProficiencies.setColumnHeader("name", "Condition");
        savingThrowProficiencies.setOptions(Arrays.asList(Condition.values()));
        savingThrowProficiencies.setValue(null); //nothing selected
        savingThrowProficiencies.setWidth("50%");

        armorProficiencies = new DSSubSetSelector<ArmorType.ProficiencyType>(ArmorType.ProficiencyType.class);
        armorProficiencies.setCaption("Maitrises d'armure");
        armorProficiencies.setVisibleProperties("name");
        armorProficiencies.setColumnHeader("name", "Maitrise");
        armorProficiencies.setOptions(Arrays.asList(ArmorType.ProficiencyType.values()));
        armorProficiencies.setValue(null); //nothing selected
        armorProficiencies.setWidth("50%");

        weaponProficiencies = new DSSubSetSelector<WeaponType>(WeaponType.class);
        weaponProficiencies.setCaption("Maitrises d'arme");
        weaponProficiencies.setVisibleProperties("name");
        weaponProficiencies.setColumnHeader("name", "Maitrise");
        weaponProficiencies.setOptions(weaponTypeService.findAll());
        weaponProficiencies.setValue(null); //nothing selected
        weaponProficiencies.setWidth("50%");

        skillProficiencies = new DSSubSetSelector<Skill>(Skill.class);
        skillProficiencies.setCaption("Maitrise de compétence");
        skillProficiencies.setVisibleProperties("name", "keyAbility.name");
        skillProficiencies.setColumnHeader("name", "Compétence");
        skillProficiencies.setColumnHeader("keyAbility.name", "Caractéristique clé");
        skillProficiencies.setOptions(skillService.findAll());
        skillProficiencies.setWidth("80%");
        skillProficiencies.setValue(null); //nothing selected

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
