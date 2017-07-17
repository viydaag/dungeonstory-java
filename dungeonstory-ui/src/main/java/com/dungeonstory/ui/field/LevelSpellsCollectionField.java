package com.dungeonstory.ui.field;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.backend.data.Level;
import com.vaadin.data.HasValue;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

public class LevelSpellsCollectionField<T> extends ElementCollectionField<T> {

    private static final long serialVersionUID = 947526857652822107L;

    private boolean isKnownSpells = false;

    public static class LevelSpellsRow {
        public ComboBox<Level> level         = new ComboBox<Level>();
        public IntegerField    cantripsKnown = new IntegerField().withWidth("110px");
        public IntegerField    spellsKnown   = new IntegerField().withWidth("110px");
        public IntegerField    spellSlots1   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots2   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots3   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots4   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots5   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots6   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots7   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots8   = new IntegerField().withWidth("50px");
        public IntegerField    spellSlots9   = new IntegerField().withWidth("50px");
    }

    public LevelSpellsCollectionField(Class<T> elementType) {
        super(elementType, LevelSpellsRow.class);
        setPropertyHeader("level", "Niveau");
        setPropertyHeader("cantripsKnown", "Sorts 0 connus");
        setPropertyHeader("spellsKnown", "Sorts connus");
        setPropertyHeader("spellSlots1", "Sorts 1");
        setPropertyHeader("spellSlots2", "Sorts 2");
        setPropertyHeader("spellSlots3", "Sorts 3");
        setPropertyHeader("spellSlots4", "Sorts 4");
        setPropertyHeader("spellSlots5", "Sorts 5");
        setPropertyHeader("spellSlots6", "Sorts 6");
        setPropertyHeader("spellSlots7", "Sorts 7");
        setPropertyHeader("spellSlots8", "Sorts 8");
        setPropertyHeader("spellSlots9", "Sorts 9");
    }

    @Override
    public void onElementAdded() {
        super.onElementAdded();
        refreshVisibility();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void refreshVisibility() {
        if (isVisible()) {
            final int nbRows = getLayout().getRows();
            for (int row = 0; row < nbRows; row++) {
                Component c = getLayout().getComponent(2, row);
                if (c != null) {
                    if (c instanceof HasValue && !isKnownSpells) {
                        ((HasValue) c).setValue(((HasValue) c).getEmptyValue());
                    }
                    c.setVisible(isKnownSpells);
                }
            }
        }
    }

    public void setKnownSpells(boolean isKnownSpells) {
        this.isKnownSpells = isKnownSpells;
        refreshVisibility();
    }

}
