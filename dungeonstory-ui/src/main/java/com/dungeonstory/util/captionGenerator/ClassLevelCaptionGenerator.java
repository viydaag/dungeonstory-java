package com.dungeonstory.util.captionGenerator;

import java.util.List;

import org.vaadin.viritin.fields.CaptionGenerator;

import com.dungeonstory.util.converter.CollectionToStringConverter;

public class ClassLevelCaptionGenerator implements CaptionGenerator<List> {

    private static final long serialVersionUID = -8534636800179066959L;

    public ClassLevelCaptionGenerator() {

    }

    @Override
    public String getCaption(List option) {
        CollectionToStringConverter converter = new CollectionToStringConverter();
        converter.setDelimiter(" / ");
        return converter.convertToPresentation(option, String.class, null);
    }

}
