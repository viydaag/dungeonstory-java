package com.dungeonstory.ui.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

/**
 * A converter that allows displaying a collection as a html list (ul or ol).
 * 
 */
@SuppressWarnings("rawtypes")
public class CollectionToStringListConverter<C extends Collection> implements Converter<String, C> {

    private static final long serialVersionUID = 5950198194834411892L;

    private ListType          listType     = ListType.UNORDERED;
    private OrderedListType   orderedBullet;
    private UnorderedListType unorderedBullet = UnorderedListType.NONE;
    protected List<String>    listElements = new ArrayList<>();
    private int               nbColumns    = 1;

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
        NUMBER("1"), UPPERCASE_LETTER("A"), LOWERCASE_LETTER("a"), UPPERCASE_ROMAN_NUMBER("I"), LOWERCASE_ROMAN_NUMBER("i");

        private String value;

        private OrderedListType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum UnorderedListType {
        DISC("disc"), CIRCLE("circle"), SQUARE("square"), NONE("none");

        private String value;

        private UnorderedListType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Override
    public Result<C> convertToModel(String value, ValueContext context) {
        throw new UnsupportedOperationException("Can only convert from collection to string");
    }

    @Override
    public String convertToPresentation(C value, ValueContext context) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        listElements.clear();
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

        openingTag = OPENING + listType.getValue() + buildStyle() + CLOSE;
        //        } else if (listType == ListType.ORDERED && orderedBullet != null) {
        //            openingTag = String.format(OPENING + listType.getValue() + " type=\"%s\"" + CLOSE, orderedBullet.getValue());
        //        }
        return openingTag + StringUtils.join(listElements, "") + CLOSING + listType.getValue() + CLOSE;
    }

    private String buildStyle() {
        String style = "";
        String ubullet = "";
        String obullet = "";
        String columns = "";

        //Actually, spliting columns does not display bullets correctly, so better hide then when multiple columns.
        if (getNbColumns() > 1) {
            columns = String.format("column-count:%d; ", getNbColumns());
            unorderedBullet = UnorderedListType.NONE;
        }

        if (listType == ListType.UNORDERED && unorderedBullet != null) {
            ubullet = String.format("list-style-type:%s; ", unorderedBullet.getValue());
        } else if (listType == ListType.ORDERED && orderedBullet != null) {
            obullet = String.format(" type=\"%s\"", orderedBullet.getValue());
        }

        if (StringUtils.isNotEmpty(ubullet)) {
            style += ubullet;
        }
        if (StringUtils.isNotEmpty(columns)) {
            style += columns;
        }

        if (StringUtils.isNoneEmpty(style)) {
            style = " style=\"" + style + "\"";
        }

        if (StringUtils.isNotEmpty(obullet)) {
            style = obullet + style;
        }

        return style;
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

    public int getNbColumns() {
        return nbColumns;
    }

    public void setNbColumns(int nbColumns) {
        this.nbColumns = nbColumns;
    }

}
