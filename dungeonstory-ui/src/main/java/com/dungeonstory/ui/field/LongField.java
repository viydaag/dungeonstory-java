package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;

public class LongField
        extends AbstractNumberField<Long> {

    private static final long serialVersionUID = -285601313783489470L;

    public LongField() {
        super();
        setSizeUndefined();
    }

    public LongField(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    protected boolean userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                value = Long.parseLong(str);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            value = null;
        }
        return true;
    }

    @Override
    protected String getParsingErrorMessage() {
        return "This is not a long";
    }

}
