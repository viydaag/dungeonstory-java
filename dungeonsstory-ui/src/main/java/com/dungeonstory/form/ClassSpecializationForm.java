package com.dungeonstory.form;

import org.vaadin.viritin.fields.ElementCollectionTable;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelSpell;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpecializationSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.FeatDataService;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.form.ClassForm.ClassLevelFeatureRow;
import com.dungeonstory.util.field.LevelSpellsCollectionField;
import com.dungeonstory.util.field.LevelSpellsCollectionField.LevelSpellsRow;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ClassSpecializationForm extends DSAbstractForm<ClassSpecialization> {

    private static final long serialVersionUID = -9195608720966852469L;

    private TextField                                                 name;
    private TypedSelect<DSClass>                                      parentClass;
    private TextArea                                                  description;
    private FormCheckBox                                              isSpellCasting;
    private TypedSelect<Ability>                                      spellCastingAbility;
    private LevelSpellsCollectionField<ClassSpecializationSpellSlots> spellSlots;
    private ElementCollectionTable<ClassSpecLevelSpell>               classSpecSpells;
    private ElementCollectionTable<ClassSpecLevelFeature>             classSpecFeatures;

    private DataService<Level, Long>   levelService   = null;
    private FeatDataService            featService    = null;
    private DataService<Ability, Long> abilityService = null;
    private DataService<Spell, Long>   spellService   = null;
    private DataService<DSClass, Long> classService   = null;

    public static class ClassSpecLevelFeatureRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Feat>  feat  = new TypedSelect<Feat>();
    }

    public static class ClassSpecLevelSpellRow {
        TypedSelect<Level> level = new TypedSelect<Level>();
        TypedSelect<Spell> spell = new TypedSelect<Spell>();
    }

    public ClassSpecializationForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            levelService = MockLevelService.getInstance();
            featService = MockFeatService.getInstance();
            abilityService = MockAbilityService.getInstance();
            spellService = MockSpellService.getInstance();
            classService = MockClassService.getInstance();
        } else {
            levelService = LevelService.getInstance();
            featService = FeatService.getInstance();
            abilityService = AbilityService.getInstance();
            spellService = SpellService.getInstance();
            classService = ClassService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Spécialisation de classe";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();
        parentClass = new TypedSelect<DSClass>("Classe parente");
        parentClass.setOptions(classService.findAll());

        isSpellCasting = new FormCheckBox("Capacité à lancer des sorts");
        spellCastingAbility = new TypedSelect<Ability>("Caractéristique de sort");
        spellCastingAbility.setOptions(abilityService.findAll());
        isSpellCasting.addValueChangeListener(this::isSpellCastingChange);

        spellSlots = (LevelSpellsCollectionField<ClassSpecializationSpellSlots>) new LevelSpellsCollectionField<ClassSpecializationSpellSlots>(
                ClassSpecializationSpellSlots.class).withCaption("Nombre de sorts").withEditorInstantiator(() -> {
                    LevelSpellsRow row = new LevelSpellsRow();
                    row.level.setOptions(levelService.findAll());
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

        classSpecFeatures = new ElementCollectionTable<ClassSpecLevelFeature>(ClassSpecLevelFeature.class,
                ClassSpecLevelFeatureRow.class).withCaption("Dons de spécialisation").withEditorInstantiator(() -> {
                    ClassLevelFeatureRow row = new ClassLevelFeatureRow();
                    row.level.setOptions(levelService.findAll());
                    row.feat.setOptions(featService.findAllClassFeatures());
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

    public void isSpellCastingChange(ValueChangeEvent event) {
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
