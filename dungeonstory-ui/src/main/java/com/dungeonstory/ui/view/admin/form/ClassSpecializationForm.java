package com.dungeonstory.ui.view.admin.form;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelSpell;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpecializationSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.ClassDataService;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.LevelDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.dungeonstory.ui.field.LevelSpellsCollectionField;
import com.dungeonstory.ui.field.LevelSpellsCollectionField.LevelSpellsRow;
import com.dungeonstory.ui.view.admin.form.ClassForm.ClassLevelFeatureRow;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassSpecializationForm extends DSAbstractForm<ClassSpecialization> {

    private static final long serialVersionUID = -9195608720966852469L;

    private TextField                                                 name;
    private ComboBox<DSClass>                                         parentClass;
    private TextArea                                                  description;
    private FormCheckBox                                              isSpellCasting;
    private ComboBox<Ability>                                         spellCastingAbility;
    private LevelSpellsCollectionField<ClassSpecializationSpellSlots> spellSlots;
    private ElementCollectionGrid<ClassSpecLevelSpell>                classSpecSpells;
    private ElementCollectionGrid<ClassSpecLevelFeature>              classSpecFeatures;

    private LevelDataService        levelService        = null;
    private ClassFeatureDataService classFeatureService = null;
    private AbilityDataService      abilityService      = null;
    private SpellDataService        spellService        = null;
    private ClassDataService        classService        = null;

    public static class ClassSpecLevelFeatureRow {
        ComboBox<Level>        level   = new ComboBox<>();
        ComboBox<ClassFeature> feature = new ComboBox<>();
    }

    public static class ClassSpecLevelSpellRow {
        ComboBox<Level> level = new ComboBox<>();
        ComboBox<Spell> spell = new ComboBox<>();
    }

    public ClassSpecializationForm() {
        super(ClassSpecialization.class);
        levelService = Services.getLevelService();
        classFeatureService = Services.getClassFeatureService();
        abilityService = Services.getAbilityService();
        spellService = Services.getSpellService();
        classService = Services.getClassService();
    }

    @Override
    public String toString() {
        return "Spécialisation de classe";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new TextField("Nom");
        description = new DSTextArea("Description").withFullWidth();
        parentClass = new ComboBox<DSClass>("Classe parente");
        parentClass.setItems(classService.findAll());

        isSpellCasting = new FormCheckBox("Capacité à lancer des sorts");
        spellCastingAbility = new ComboBox<Ability>("Caractéristique de sort");
        spellCastingAbility.setItems(abilityService.findAll());
        spellCastingAbility.setEmptySelectionAllowed(false);
        isSpellCasting.addValueChangeListener(this::isSpellCastingChange);

        spellSlots = (LevelSpellsCollectionField<ClassSpecializationSpellSlots>) new LevelSpellsCollectionField<ClassSpecializationSpellSlots>(
                ClassSpecializationSpellSlots.class).withCaption("Nombre de sorts").withEditorInstantiator(() -> {
                    LevelSpellsRow row = new LevelSpellsRow();
                    row.level.setItems(levelService.findAll());
                    return row;
                });
        spellSlots.setKnownSpells(true);

        classSpecSpells = new ElementCollectionGrid<>(ClassSpecLevelSpell.class, ClassSpecLevelSpellRow.class)
                .withCaption("Sorts de spécialisation").withEditorInstantiator(() -> {
                    ClassSpecLevelSpellRow row = new ClassSpecLevelSpellRow();
                    row.level.setItems(levelService.findAll());
                    row.spell.setItems(spellService.findAll());
                    return row;
                });
        classSpecSpells.setPropertyHeader("level", "Niveau");
        classSpecSpells.setPropertyHeader("spell", "Sort");
        classSpecSpells.setWidth("80%");

        classSpecFeatures = new ElementCollectionGrid<ClassSpecLevelFeature>(ClassSpecLevelFeature.class,
                ClassSpecLevelFeatureRow.class).withCaption("Dons de spécialisation").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setItems(levelService.findAll());
                    row.feature.setItems(classFeatureService.findAll());
                    return row;
                });
        classSpecFeatures.setPropertyHeader("level", "Niveau");
        classSpecFeatures.setPropertyHeader("feat", "Don");
        classSpecFeatures.setWidth("80%");

        layout.addComponent(name);
        layout.addComponent(parentClass);
        layout.addComponent(description);
        layout.addComponents(isSpellCasting, spellCastingAbility, spellSlots, classSpecSpells);
        layout.addComponent(classSpecFeatures);
        layout.addComponent(getToolbar());

        return layout;
    }

    public void isSpellCastingChange(ValueChangeEvent<Boolean> event) {
        if (isSpellCasting.getValue() == null || isSpellCasting.getValue() == false) {
            spellCastingAbility.setValue(null);
            spellCastingAbility.setVisible(false);
            spellSlots.clear();
            spellSlots.setVisible(false);
            classSpecSpells.clear();
            classSpecSpells.setVisible(false);
        } else {
            spellCastingAbility.setVisible(true);
            spellSlots.setVisible(true);
            spellSlots.onElementAdded();
            classSpecSpells.setVisible(true);
        }
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        isSpellCastingChange(null);
    }
}
