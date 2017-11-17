package com.dungeonstory.ui.field;

import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.fluent.api.FluentCustomField;

public class DSDoubleField
        extends DoubleField implements FluentCustomField<DSDoubleField, Double> {

    private static final long serialVersionUID = -6700892379590007567L;

    public DSDoubleField() {
        super();
    }

    public DSDoubleField(String caption) {
        super(caption);
    }
    
    @Override
    protected String getParsingErrorMessage() {
        Messages msg = Messages.getInstance();
        return msg.getMessage("doubleField.validation.message");
    }

    public DSDoubleField withDigits(int digits) {
        setDigits(digits);
        return this;
    }

    public DSDoubleField withFractions(int fractions) {
        setFractions(fractions);
        return this;
    }

}
