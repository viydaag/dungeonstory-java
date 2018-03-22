package com.dungeonstory.ui.captionGenerator;

import com.dungeonstory.backend.data.enums.I18nEnum;
import com.vaadin.ui.ItemCaptionGenerator;

public class I18nEnumCaptionGenerator<T extends I18nEnum> implements ItemCaptionGenerator<T> {

    private static final long serialVersionUID = -3612199343545399829L;

    @Override
    public String apply(T item) {
        return item.getName();
    }

}
