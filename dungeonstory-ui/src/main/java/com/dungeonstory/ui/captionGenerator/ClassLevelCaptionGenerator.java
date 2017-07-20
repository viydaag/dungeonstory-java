package com.dungeonstory.ui.captionGenerator;

import java.util.Set;

import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.ItemCaptionGenerator;

public class ClassLevelCaptionGenerator implements ItemCaptionGenerator<Set<CharacterClass>> {

    private static final long serialVersionUID = -8534636800179066959L;

    public ClassLevelCaptionGenerator() {

    }

    @Override
    public String apply(Set<CharacterClass> option) {
        CollectionToStringConverter converter = new CollectionToStringConverter();
        converter.setDelimiter(" / ");
        return converter.convertToPresentation(option, new ValueContext());
    }

}
