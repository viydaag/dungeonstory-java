package com.dungeonstory.ui.field;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HasValueChangeMode;
import com.vaadin.ui.TextField;

public abstract class AbstractNumberField<T extends Number>
        extends CustomField<T>
        implements HasValueChangeMode, FieldEvents.FocusNotifier, FieldEvents.BlurNotifier {

    private static final long serialVersionUID = -206265972432461924L;

    protected T value;

    protected TextField tf;

    private boolean ignoreValueChange = false;
    private boolean aggressive        = true;
    private boolean maskEnabled       = true;
    
    protected String parsingErrorMessage = "This is not a number";

    protected ValueChangeListener<String> vcl = new ValueChangeListener<String>() {

        private static final long serialVersionUID = 5034199201545161061L;

        @Override
        public void valueChange(ValueChangeEvent<String> event) {
            if (!ignoreValueChange) {
                T old = getValue();
                String newValue = event.getValue();
                if (newValue != null) {
                    if (userInputToValue(newValue)) {
                        fireEvent(new ValueChangeEvent<T>(AbstractNumberField.this, old, true));
                    } else {
                        if (isAggressive()) {
                            //do not let invalid characters in aggressive mode
                            doSetValue(old);
                        } else {
                            setComponentError(new UserError(getParsingErrorMessage()));
                        }
                    }
                } else {
                    setValue(null);
                }
            }
        }

    };

    protected AbstractNumberField() {
        tf = new TextField(vcl);
        setValueChangeMode(ValueChangeMode.LAZY);
        setValueChangeTimeout(600);
    }

    protected abstract boolean userInputToValue(String str);
    
    protected String getParsingErrorMessage() {
        return parsingErrorMessage;
    }
    
    public void setParsingErrorMessage(String msg) {
        parsingErrorMessage = msg;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValueChangeMode(ValueChangeMode valueChangeMode) {
        tf.setValueChangeMode(valueChangeMode);
    }

    @Override
    public ValueChangeMode getValueChangeMode() {
        return tf.getValueChangeMode();
    }

    @Override
    public void setValueChangeTimeout(int timeout) {
        tf.setValueChangeTimeout(timeout);
    }

    @Override
    public int getValueChangeTimeout() {
        return tf.getValueChangeTimeout();
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    @Override
    protected Component initContent() {
        return tf;
    }

    @Override
    protected void doSetValue(T value) {
        this.value = value;
        ignoreValueChange = true;
        if (value == null) {
            tf.clear();
        } else {
            tf.setValue(valueToPresentation(value));
        }
        ignoreValueChange = false;
    }

    protected String valueToPresentation(T newValue) {
        return newValue.toString();
    }

    @Override
    public Registration addBlurListener(BlurListener listener) {
        return tf.addBlurListener(listener);
    }

    @Override
    public Registration addFocusListener(FocusListener listener) {
        return tf.addFocusListener(listener);
    }
    
    @Override
    public void setComponentError(ErrorMessage componentError) {
        super.setComponentError(componentError);
        tf.setComponentError(componentError);
    }
    
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        if (tf != null) {
            if (width != null && !width.equals("")) {
                tf.setWidth("100%");
            } else {
                tf.setWidth(null);
            }
        }
    }
    
    @Override
    public void setWidth(float width, Unit unit) {
        super.setWidth(width, unit);
        if (tf != null) {
            if (width != -1) {
                tf.setWidth("100%");
            } else {
                tf.setWidth(null);
            }
        }
    }

    public void setMaskEnabled(boolean enabled) {
        this.maskEnabled = enabled;
    }

    public boolean isMaskEnabled() {
        return this.maskEnabled;
    }

}
