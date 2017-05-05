package com.dungeonstory.util.captionGenerator;

import java.util.Collection;

import org.vaadin.viritin.fields.CaptionGenerator;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.vaadin.data.ValueContext;

public class ClassLevelCaptionGenerator implements CaptionGenerator<Collection<DSClass>> {

    private static final long serialVersionUID = -8534636800179066959L;

    public ClassLevelCaptionGenerator() {

    }

    @Override
    public String getCaption(Collection<DSClass> option) {
        CollectionToStringConverter converter = new CollectionToStringConverter();
        converter.setDelimiter(" / ");
        return converter.convertToPresentation(option, new ValueContext());
    }

}
