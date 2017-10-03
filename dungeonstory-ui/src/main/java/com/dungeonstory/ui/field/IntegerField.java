package com.dungeonstory.ui.field;

import com.vaadin.server.ErrorMessage;

public class IntegerField
        extends org.vaadin.viritin.fields.IntegerField {

    private static final long serialVersionUID = -8678287421037288138L;

    public IntegerField() {
        super();
    }

    public IntegerField(String caption) {
        super(caption);
    }

    @Override
    public void setComponentError(ErrorMessage componentError) {
        super.setComponentError(componentError);
        tf.setComponentError(componentError);
    }

}
