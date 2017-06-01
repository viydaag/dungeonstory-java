package com.dungeonstory.ui.view.admin.form;

import java.util.List;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.DivineDomainSpell;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.field.ElementCollectionGrid;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DivineDomainForm extends DSAbstractForm<DivineDomain> {

    private static final long serialVersionUID = -3105282955860848941L;

    private TextField name;
    private TextArea  description;

    private ElementCollectionGrid<DivineDomainSpell> spells;

    public static class DivineDomainSpellRow {
        IntegerField    level = new IntegerField();
        ComboBox<Spell> spell = new ComboBox<Spell>();
    }

    public DivineDomainForm() {
        super(DivineDomain.class);
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

        List<Spell> allSpells = Services.getSpellService().findAll();
        spells = new ElementCollectionGrid<>(DivineDomainSpell.class, DivineDomainSpellRow.class).withCaption("Sorts").withEditorInstantiator(() -> {
            DivineDomainSpellRow row = new DivineDomainSpellRow();
            // The ManyToOne field needs its options to be populated
            row.spell.setItems(allSpells);
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
