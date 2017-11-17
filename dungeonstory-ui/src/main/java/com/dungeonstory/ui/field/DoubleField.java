package com.dungeonstory.ui.field;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.inputmask.InputMask;
import org.vaadin.inputmask.client.Alias;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class DoubleField
        extends AbstractNumberField<Double> {

    private static final long serialVersionUID = 2515914871455163294L;

    private int digits    = 10; //number of integer digits
    private int fractions = 1;  //number of fractional digits (after the decimal point)

    public DoubleField() {
        super();
        setSizeUndefined();
    }

    public DoubleField(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        if (isMaskEnabled()) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(UI.getCurrent().getLocale());
            InputMask numericInputMask = new InputMask(Alias.DECIMAL);
            numericInputMask.setIntegerDigits(String.valueOf(digits));
            numericInputMask.setDigits(String.valueOf(fractions));
            numericInputMask.setRadixPoint(String.valueOf(symbols.getDecimalSeparator()));
            numericInputMask.extend(tf);
        }
        return tf;
    }

    @Override
    protected boolean userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                NumberFormat format = NumberFormat.getInstance(UI.getCurrent().getLocale());
                Number number = format.parse(str);
                value = number.doubleValue();
            } catch (ParseException e) {
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

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public void setFractions(int fractions) {
        this.fractions = fractions;
    }

}
