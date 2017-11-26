package com.dungeonstory.ui.field;

import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.fluent.api.FluentCustomField;

public class DSLongField
        extends LongField
        implements FluentCustomField<DSLongField, Long> {

    private static final long serialVersionUID = 9115288909675382359L;

    public DSLongField() {
        super();
    }

    public DSLongField(String caption) {
        super(caption);
    }

    @Override
    protected String getParsingErrorMessage() {
        Messages msg = Messages.getInstance();
        return msg.getMessage("longField.validation.message");
    }

    public DSLongField withDigits(int digits) {
        setDigits(digits);
        return this;
    }

}
