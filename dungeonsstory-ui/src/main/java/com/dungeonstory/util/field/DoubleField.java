package com.dungeonstory.util.field;

import org.vaadin.viritin.fields.AbstractNumberField;

public class DoubleField extends AbstractNumberField<Double> {

    private static final long serialVersionUID = -4745616387521535321L;

    public DoubleField() {

    }

    public DoubleField(String caption) {
        setCaption(caption);
    }

    @Override
    protected void userInputToValue(String str) {
        setValue(Double.parseDouble(str));
    }

    @Override
    public Class<? extends Double> getType() {
        return Double.class;
    }

}
