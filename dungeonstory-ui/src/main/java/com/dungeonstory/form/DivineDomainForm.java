package com.dungeonstory.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.v7.fields.ElementCollectionTable;
import org.vaadin.viritin.v7.fields.TypedSelect;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.DivineDomainSpell;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.ui.component.DSTextArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DivineDomainForm extends DSAbstractForm<DivineDomain> {

    private static final long serialVersionUID = -3105282955860848941L;

    private TextField name;
    private TextArea  description;

    private ElementCollectionTable<DivineDomainSpell> spells;
    private DataService<Spell, Long>                  spellService = null;

    public static class DivineDomainSpellRow {
        IntegerField       level = new IntegerField();
        TypedSelect<Spell> spell = new TypedSelect<Spell>();
    }

    public DivineDomainForm() {
        super(DivineDomain.class);
        if (Configuration.getInstance().isMock()) {
            spellService = MockSpellService.getInstance();
        } else {
            spellService = SpellService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Domaines divins";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new DSTextArea("Description").withFullWidth();
        
        spells = new ElementCollectionTable<DivineDomainSpell>(DivineDomainSpell.class, DivineDomainSpellRow.class)
                .withCaption("Sorts").withEditorInstantiator(() -> {
                    DivineDomainSpellRow row = new DivineDomainSpellRow();
                    // The ManyToOne field needs its options to be populated
                    row.spell.setOptions(spellService.findAll());
                    return row;
                });
        spells.setPropertyHeader("level", "Niveau");
        spells.setPropertyHeader("spell", "Sort");

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(spells);
        layout.addComponent(getToolbar());

        return layout;
    }
}
