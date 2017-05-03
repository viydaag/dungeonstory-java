package com.dungeonstory.util.field;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.fields.AbstractNumberField;

import com.vaadin.event.FieldEvents;

public class LongField extends AbstractNumberField<LongField, Long> {

    private static final long serialVersionUID = -285601313783489470L;
    private Long value;

    public LongField() {
        setSizeUndefined();
    }

    public LongField(String caption) {
        setCaption(caption);
    }

    @Override
    protected void userInputToValue(String str) {
        if (StringUtils.isNotBlank(str)) {
            value = Long.parseLong(str);
        } else {
            value = null;
        }
    }

    @Override
    public LongField withBlurListener(FieldEvents.BlurListener listener) {
        return (LongField) super.withBlurListener(listener);
    }

    @Override
    public LongField withFocusListener(FieldEvents.FocusListener listener) {
        return (LongField) super.withFocusListener(listener);
    }

    @Override
    public Long getValue() {
        return value;
    }

}
