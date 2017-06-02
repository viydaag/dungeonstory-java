package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.fields.AbstractNumberField;

public class DoubleField extends AbstractNumberField<DoubleField, Double> {

    private static final long serialVersionUID = -4745616387521535321L;
    
    public DoubleField() {
        setSizeUndefined();
    }

    public DoubleField(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    protected void userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            value = Double.parseDouble(str);
        } else {
            value = null;
        }
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    protected void configureHtmlElement() {
        super.configureHtmlElement();
        s.setJavaScriptEventHandler("keypress", "function(e) {var c = viritin.getChar(e); return c==null || /^[-\\d\\n\\t\\r\\.\\,]+$/.test(c);}");
        s.setProperty("step", "0.1");
    }

    @Override
    public void clear() {
        super.clear();
    }

}
