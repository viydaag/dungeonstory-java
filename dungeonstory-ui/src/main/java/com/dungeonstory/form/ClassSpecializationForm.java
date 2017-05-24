package com.dungeonstory.form;

import org.vaadin.viritin.v7.fields.ElementCollectionTable;
import org.vaadin.viritin.v7.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelSpell;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpecializationSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.ClassDataService;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.LevelDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.form.ClassForm.ClassLevelFeatureRow;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.util.field.ElementCollectionGrid;
import com.dungeonstory.util.field.LevelSpellsCollectionField;
import com.dungeonstory.util.field.LevelSpellsCollectionField.LevelSpellsRow;
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
    private ElementCollectionTable<ClassSpecLevelSpell>               classSpecSpells;
    private ElementCollectionGrid<ClassSpecLevelFeature>              classSpecFeatures;

    private LevelDataService   levelService   = null;
    private FeatDataService    featService    = null;
    private AbilityDataService abilityService = null;
    private SpellDataService   spellService   = null;
    private ClassDataService   classService   = null;

    public static class ClassSpecLevelFeatureRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Feat>  feat  = new TypedSelect<Feat>();
    }

    public static class ClassSpecLevelSpellRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Spell> spell = new TypedSelect<Spell>();
    }

    public ClassSpecializationForm() {
        super(ClassSpecialization.class);
        levelService = Services.getLevelService();
        featService = Services.getFeatService();
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

        classSpecSpells = new ElementCollectionTable<ClassSpecLevelSpell>(ClassSpecLevelSpell.class,
                ClassSpecLevelSpellRow.class).withCaption("Sorts de spécialisation").withEditorInstantiator(() -> {
                    ClassSpecLevelSpellRow row = new ClassSpecLevelSpellRow();
                    row.level.setOptions(levelService.findAll());
                    row.spell.setOptions(spellService.findAll());
                    return row;
                });
        classSpecSpells.setPropertyHeader("level", "Niveau");
        classSpecSpells.setPropertyHeader("spell", "Sort");
        classSpecSpells.setWidth("80%");

        classSpecFeatures = new ElementCollectionGrid<ClassSpecLevelFeature>(ClassSpecLevelFeature.class,
                ClassSpecLevelFeatureRow.class).withCaption("Dons de spécialisation").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setItems(levelService.findAll());
                    row.feat.setItems(featService.findAllClassFeatures());
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
