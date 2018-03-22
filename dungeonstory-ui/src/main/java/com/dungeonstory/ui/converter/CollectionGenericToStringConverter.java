package com.dungeonstory.ui.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import com.dungeonstory.ui.captionGenerator.ToStringCaptionGenerator;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.ItemCaptionGenerator;

/**
 * A converter that allows displaying a collection as a comma separated list of
 * strings.
 */
public class CollectionGenericToStringConverter<T> implements Converter<String, Collection<T>> {

    private static final long serialVersionUID = 2735322733987431192L;

    private String delimiter = ", ";
    private ItemCaptionGenerator<T> captionGenerator = new ToStringCaptionGenerator<T>();
    
    public CollectionGenericToStringConverter() {

    }

    public CollectionGenericToStringConverter(String delimiter) {
        this.delimiter = delimiter;
    }
    
    public CollectionGenericToStringConverter(ItemCaptionGenerator<T> captionGenerator) {
        this.captionGenerator = captionGenerator;
    }

    public CollectionGenericToStringConverter(String delimiter, ItemCaptionGenerator<T> captionGenerator) {
        this.delimiter = delimiter;
        this.captionGenerator = captionGenerator;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public Result<Collection<T>> convertToModel(String value, ValueContext context) {
        throw new UnsupportedOperationException("Can only convert from collection to string");
    }

    @Override
    public String convertToPresentation(Collection<T> value, ValueContext context) {
        return value.stream().map(o -> captionGenerator.apply(o)).collect(Collectors.joining(delimiter));
    }

}
