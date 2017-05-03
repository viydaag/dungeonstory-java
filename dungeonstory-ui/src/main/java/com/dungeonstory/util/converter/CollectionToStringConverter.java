package com.dungeonstory.util.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;


/**
 * A converter that allows displaying a collection as a comma separated list of
 * strings.
 */
@SuppressWarnings("rawtypes")
public class CollectionToStringConverter implements
        Converter<String, Collection> {

    private static final long serialVersionUID = 2735322733987431192L;

    private String delimiter = ", ";

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public Result<Collection> convertToModel(String value, ValueContext context) {
        throw new UnsupportedOperationException("Can only convert from collection to string");
    }

    @Override
    public String convertToPresentation(Collection value, ValueContext context) {
        return (String) value.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

}
