package com.dungeonstory.ui.converter;

import java.util.Collection;

import com.dungeonstory.backend.repository.DescriptiveEntity;

/**
 * A converter that allows displaying a collection as a html list (ul or ol).
 * 
 */
public class DescriptiveEntityCollectionToStringListConverter<C extends Collection<?>> extends CollectionToStringListConverter<C> {

    private static final long serialVersionUID = 8736787817810180855L;

    @Override
    public void addListItems(Collection<?> items) {
        items.stream().forEach(listItem -> {
            if (listItem instanceof DescriptiveEntity) {
                listElements.add("<li><span title=\"" + ((DescriptiveEntity) listItem).getDescription() + "\">"
                        + listItem.toString() + "</span></li>");
            } else {
                listElements.add("<li>" + listItem.toString() + "</li>");
            }
        });
    }

}
