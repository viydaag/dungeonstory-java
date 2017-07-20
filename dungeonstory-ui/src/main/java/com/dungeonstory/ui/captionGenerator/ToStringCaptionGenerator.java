package com.dungeonstory.ui.captionGenerator;

import com.vaadin.ui.ItemCaptionGenerator;

public class ToStringCaptionGenerator<T> implements ItemCaptionGenerator<T> {

    private static final long serialVersionUID = -4727663676375459965L;

    @Override
    public String apply(T option) {
        if (option == null) {
            return "";
        } else {
            return option.toString();
        }
    }
}
