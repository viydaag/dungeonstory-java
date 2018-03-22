package com.dungeonstory.ui.component;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.I18nEnum;
import com.dungeonstory.ui.captionGenerator.I18nEnumCaptionGenerator;
import com.vaadin.ui.ComboBox;

public class I18nEnumComboBox<T extends Enum<T> & I18nEnum> extends ComboBox<T> {

    private static final long serialVersionUID = 4982690783465869686L;

    public I18nEnumComboBox(Class<T> enumClass) {
        super();
        init(enumClass);
    }

    public I18nEnumComboBox(Class<T> enumClass, String caption) {
        super(caption);
        init(enumClass);
    }

    private void init(Class<T> enumClass) {
        setEmptySelectionAllowed(false);
        setItems(EnumSet.allOf(enumClass));
        setItemCaptionGenerator(new I18nEnumCaptionGenerator<T>());
    }

}
