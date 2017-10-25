package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;

public class IntegerField
        extends AbstractNumberField<Integer> {

    private static final long serialVersionUID = -8755083088960852163L;

    public IntegerField() {
        super();
        setSizeUndefined();
    }

    public IntegerField(String caption) {
        this();
        setCaption(caption);
    }
    
    @Override
    protected boolean userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            //            Pattern.matches("^([-+]?[1-9]\\d*|0)$", str)
            try {
                value = Integer.parseInt(str);
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
        return "This is not an integer";
    }

}
