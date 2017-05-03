package com.dungeonstory.util.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class CollectionListConverter<T> implements Converter<Collection<T>, List<T>> {

    private static final long serialVersionUID = -5849356426898359063L;

    @Override
    public Result<List<T>> convertToModel(Collection<T> value, ValueContext context) {
        List<T> list = new ArrayList<>(value);
        return Result.ok(list);
    }

    @Override
    public Collection<T> convertToPresentation(List<T> value, ValueContext context) {
        return value;
    }

    

}
