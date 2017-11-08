package com.dungeonstory.ui.field;

import com.dungeonstory.backend.data.Level;
import com.vaadin.data.HasValue;
import com.vaadin.fluent.ui.FComboBox;
import com.vaadin.ui.Component;

public class LevelSpellsCollectionField<T> extends ElementCollectionField<T> {

    private static final long serialVersionUID = 947526857652822107L;

    private boolean isKnownSpells = false;

    public static class LevelSpellsRow {
        public FComboBox<Level> level         = new FComboBox<Level>().withEmptySelectionAllowed(false);
        public IntegerField     cantripsKnown = new DSIntegerField().withWidth("110px");
        public IntegerField     spellsKnown   = new DSIntegerField().withWidth("110px");
        public IntegerField     spellSlots1   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots2   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots3   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots4   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots5   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots6   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots7   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots8   = new DSIntegerField().withWidth("50px");
        public IntegerField     spellSlots9   = new DSIntegerField().withWidth("50px");
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
            final int nbRows = gridLayout.getRows();
            for (int row = 0; row < nbRows; row++) {
                Component c = gridLayout.getComponent(2, row);
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
