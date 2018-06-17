package com.dungeonstory.ui.field;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.inputmask.InputMask;
import org.vaadin.inputmask.client.Alias;

import com.vaadin.ui.Component;

public class IntegerField
        extends AbstractNumberField<Integer> {

    private static final long serialVersionUID = -8755083088960852163L;

    private int digits = 10; //number of integer digits

    public IntegerField() {
        super();
        setSizeUndefined();
    }

    public IntegerField(String caption) {
        this();
        setCaption(caption);
    }
    
    @Override
    protected Component initContent() {
        //        InputMask regexInputMask = new InputMask("^([-+]?[1-9]\\d*|0)$");
        //        regexInputMask.setRegexMask(true);
        //        regexInputMask.extend(tf);
        InputMask numericInputMask = new InputMask(Alias.INTEGER);
        numericInputMask.setIntegerDigits(String.valueOf(digits));
        numericInputMask.setAllowMinus(allowNegative);
        numericInputMask.extend(tf);
        return super.initContent();
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

    public void setDigits(int digits) {
        this.digits = digits;
    }

}
