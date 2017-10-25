package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;

public class DoubleField
        extends AbstractNumberField<Double> {

    private static final long serialVersionUID = 2515914871455163294L;

    public DoubleField() {
        super();
        setSizeUndefined();
    }

    public DoubleField(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    protected boolean userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                value = Double.parseDouble(str);
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
        return "This is not a double";
    }

}
