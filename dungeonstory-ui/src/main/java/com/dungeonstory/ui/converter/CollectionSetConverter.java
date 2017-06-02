package com.dungeonstory.ui.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class CollectionSetConverter<T> implements Converter<Collection<T>, Set<T>> {

    private static final long serialVersionUID = -5849356426898359063L;

    @Override
    public Result<Set<T>> convertToModel(Collection<T> value, ValueContext context) {
        Set<T> set = new HashSet<>(value);
        return Result.ok(set);
    }

    @Override
    public Collection<T> convertToPresentation(Set<T> value, ValueContext context) {
        return value;
    }

    

}
