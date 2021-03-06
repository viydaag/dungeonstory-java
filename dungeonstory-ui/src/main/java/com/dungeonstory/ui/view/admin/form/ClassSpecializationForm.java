package com.dungeonstory.ui.view.admin.form;

import java.util.EnumSet;
import java.util.List;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelSpell;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpecializationSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.ClassDataService;
import com.dungeonstory.backend.service.ClassFeatureDataService;
import com.dungeonstory.backend.service.LevelDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.field.LevelSpellsCollectionField;
import com.dungeonstory.ui.field.LevelSpellsCollectionField.LevelSpellsRow;
import com.dungeonstory.ui.view.admin.form.ClassForm.ClassLevelFeatureRow;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.fluent.ui.FComboBox;
import com.vaadin.fluent.ui.FTextArea;
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
    private SpellDataService        spellService        = null;
    private ClassDataService        classService        = null;

    public static class ClassSpecLevelFeatureRow {
        FComboBox<Level>        level      = new FComboBox<Level>().withEmptySelectionAllowed(false);
        FComboBox<ClassFeature> feature    = new FComboBox<ClassFeature>().withEmptySelectionAllowed(false).withWidth("100%");
        IntegerField            nbToChoose = new DSIntegerField();
    }

    public static class ClassSpecLevelSpellRow {
        FComboBox<Level> level = new FComboBox<Level>().withEmptySelectionAllowed(false);
        FComboBox<Spell> spell = new FComboBox<Spell>().withEmptySelectionAllowed(false).withWidth("100%");
    }

    public ClassSpecializationForm() {
        super(ClassSpecialization.class);
        levelService = Services.getLevelService();
        classFeatureService = Services.getClassFeatureService();
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
        description = new FTextArea("Description").withFullWidth();
        parentClass = new ComboBox<DSClass>("Classe parente");
        parentClass.setItems(classService.findAll());

        isSpellCasting = new FormCheckBox("Capacité à lancer des sorts");
        spellCastingAbility = new ComboBox<Ability>("Caractéristique de sort");
        spellCastingAbility.setItems(EnumSet.allOf(Ability.class));
        spellCastingAbility.setEmptySelectionAllowed(false);
        isSpellCasting.addValueChangeListener(this::isSpellCastingChange);

        List<Level> allLevels = levelService.findAll();
        spellSlots = (LevelSpellsCollectionField<ClassSpecializationSpellSlots>) new LevelSpellsCollectionField<ClassSpecializationSpellSlots>(
                ClassSpecializationSpellSlots.class).withCaption("Nombre de sorts").withEditorInstantiator(() -> {
                    LevelSpellsRow row = new LevelSpellsRow();
                    row.level.setItems(allLevels);
                    return row;
                });
        spellSlots.setKnownSpells(true);

        List<Spell> allSpells = spellService.findAll();
        classSpecSpells = new ElementCollectionGrid<>(ClassSpecLevelSpell.class, ClassSpecLevelSpellRow.class).withCaption("Sorts de spécialisation")
                .withEditorInstantiator(() -> {
                    ClassSpecLevelSpellRow row = new ClassSpecLevelSpellRow();
                    row.level.setItems(allLevels);
                    row.spell.setItems(allSpells);
                    return row;
                });
        classSpecSpells.setPropertyHeader("level", "Niveau");
        classSpecSpells.setPropertyHeader("spell", "Sort");
        classSpecSpells.setWidth("80%");
        getBinder().forMemberField(classSpecSpells).withValidator((value, context) -> classSpecSpells.isValid());

        List<ClassFeature> allClassFeatures = classFeatureService.findAll();
        classSpecFeatures = new ElementCollectionGrid<ClassSpecLevelFeature>(ClassSpecLevelFeature.class, ClassSpecLevelFeatureRow.class)
                .withCaption("Dons de spécialisation").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setItems(allLevels);
                    row.feature.setItems(allClassFeatures);
                    row.feature.addSelectionListener(selection -> {
                        if (selection.getValue() == null || selection.getValue().getChildren().isEmpty()) {
                            row.nbToChoose.setVisible(false);
                            row.nbToChoose.setValue(1);
                        } else {
                            row.nbToChoose.setVisible(true);
                        }
                    });
                    return row;
                });
        classSpecFeatures.setPropertyHeader("level", "Niveau");
        classSpecFeatures.setPropertyHeader("feature", "Don");
        classSpecFeatures.setPropertyHeader("nbToChoose", "Nb à choisir");
        classSpecFeatures.setWidth("80%");
        getBinder().forMemberField(classSpecFeatures).withValidator((value, context) -> classSpecFeatures.isValid());

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
        classSpecSpells.clearStatusLabel();
        classSpecFeatures.clearStatusLabel();
    }
}
