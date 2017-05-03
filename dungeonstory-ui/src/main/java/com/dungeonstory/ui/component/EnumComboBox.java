package com.dungeonstory.ui.component;

import java.util.EnumSet;

import com.vaadin.ui.ComboBox;

public class EnumComboBox<T extends Enum<T>> extends ComboBox<T> {

    private static final long serialVersionUID = 4760092438820397057L;

    public EnumComboBox(Class<T> enumClass) {
        super();
        init(enumClass);
    }

    public EnumComboBox(Class<T> enumClass, String caption) {
        super(caption);
        init(enumClass);
    }

    private void init(Class<T> enumClass) {
        setEmptySelectionAllowed(false);
        setItems(EnumSet.allOf(enumClass));
    }

}
