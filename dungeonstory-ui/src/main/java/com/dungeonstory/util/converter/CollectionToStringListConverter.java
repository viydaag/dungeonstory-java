package com.dungeonstory.util.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.data.util.converter.Converter;

/**
 * A converter that allows displaying a collection as a html list (ul or ol).
 * 
 */
@SuppressWarnings("rawtypes")
public class CollectionToStringListConverter implements Converter<String, Collection> {

    private static final long serialVersionUID = 5950198194834411892L;

    private ListType          listType     = ListType.UNORDERED;
    private OrderedListType   orderedBullet;
    private UnorderedListType unorderedBullet;
    protected List<String>    listElements = new ArrayList<>();

    private final static String OPENING = "<";
    private final static String CLOSE   = ">";
    private final static String CLOSING = "</";

    public enum ListType {
        ORDERED("ol"), UNORDERED("ul");

        private String value;

        private ListType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum OrderedListType {
        NUMBER("1"),
        UPPERCASE_LETTER("A"),
        LOWERCASE_LETTER("a"),
        UPPERCASE_ROMAN_NUMBER("I"),
        LOWERCASE_ROMAN_NUMBER("i");

        private String value;

        private OrderedListType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum UnorderedListType {
        DISC("disc"), CIRCLE("circle"), SQUARE("square"), none("none");

        private String value;

        private UnorderedListType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Override
    public Collection convertToModel(String value, Class<? extends Collection> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        throw new UnsupportedOperationException("Can only convert from collection to string");
    }

    @Override
    public String convertToPresentation(Collection value, Class<? extends String> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        if (value == null) {
            return "";
        }
        addListItems(value);
        return getHtml();
    }

    public void addListItems(Collection<?> items) {
        items.stream().forEach(listItem -> {
            listElements.add("<li>" + listItem.toString() + "</li>");
        });
    }

    public String getHtml() {
        String openingTag = OPENING + listType.getValue() + CLOSE;
        if (listType == ListType.UNORDERED && unorderedBullet != null) {
            openingTag = String.format(OPENING + listType.getValue() + " style=\"list-style-type:%s\"" + CLOSE,
                    unorderedBullet.getValue());
        } else if (listType == ListType.ORDERED && orderedBullet != null) {
            openingTag = String.format(OPENING + listType.getValue() + " type=\"%s\"" + CLOSE,
                    orderedBullet.getValue());
        }
        return openingTag + StringUtils.join(listElements, "") + CLOSING + listType.getValue() + CLOSE;
    }

    @Override
    public Class<Collection> getModelType() {
        return Collection.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    public OrderedListType getOrderedBullet() {
        return orderedBullet;
    }

    public void setOrderedBullet(OrderedListType orderedBullet) {
        this.orderedBullet = orderedBullet;
    }

    public UnorderedListType getUnorderedBullet() {
        return unorderedBullet;
    }

    public void setUnorderedBullet(UnorderedListType unorderedBullet) {
        this.unorderedBullet = unorderedBullet;
    }

}