package com.dungeonstory.ui.captionGenerator;

import java.util.Set;

import org.vaadin.viritin.fields.CaptionGenerator;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.vaadin.data.ValueContext;

public class ClassLevelCaptionGenerator implements CaptionGenerator<Set<DSClass>> {

    private static final long serialVersionUID = -8534636800179066959L;

    public ClassLevelCaptionGenerator() {

    }

    @Override
    public String getCaption(Set<DSClass> option) {
        CollectionToStringConverter converter = new CollectionToStringConverter();
        converter.setDelimiter(" / ");
        return converter.convertToPresentation(option, new ValueContext());
    }

}
