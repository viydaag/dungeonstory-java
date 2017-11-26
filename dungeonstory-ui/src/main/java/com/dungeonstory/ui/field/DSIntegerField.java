package com.dungeonstory.ui.field;

import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.fluent.api.FluentCustomField;

public class DSIntegerField
        extends IntegerField
        implements FluentCustomField<DSIntegerField, Integer> {

    private static final long serialVersionUID = -4920429406231299901L;

    public DSIntegerField() {
        super();
    }

    public DSIntegerField(String caption) {
        super(caption);
    }

    @Override
    protected String getParsingErrorMessage() {
        Messages msg = Messages.getInstance();
        return msg.getMessage("integerField.validation.message");
    }

    public DSIntegerField withDigits(int digits) {
        setDigits(digits);
        return this;
    }

}
