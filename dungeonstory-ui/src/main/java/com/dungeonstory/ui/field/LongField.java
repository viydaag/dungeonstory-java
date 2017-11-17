package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.inputmask.InputMask;
import org.vaadin.inputmask.client.Alias;

import com.vaadin.ui.Component;

public class LongField
        extends AbstractNumberField<Long> {

    private static final long serialVersionUID = -285601313783489470L;

    private int digits = 18; //number of integer digits

    public LongField() {
        super();
        setSizeUndefined();
    }

    public LongField(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        InputMask numericInputMask = new InputMask(Alias.INTEGER);
        numericInputMask.setIntegerDigits(String.valueOf(digits));
        numericInputMask.extend(tf);
        return super.initContent();
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

    public void setDigits(int digits) {
        this.digits = digits;
    }

}
