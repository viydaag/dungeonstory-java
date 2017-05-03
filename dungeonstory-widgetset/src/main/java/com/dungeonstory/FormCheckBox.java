package com.dungeonstory;

import com.vaadin.ui.CheckBox;

public class FormCheckBox extends CheckBox {

    private static final long serialVersionUID = -2729743057327152435L;

    /**
     * Creates a new checkbox.
     */
    public FormCheckBox() {
        super();
    }

    /**
     * Creates a new checkbox with a set caption.
     *
     * @param caption
     *            the Checkbox caption.
     */
    public FormCheckBox(final String caption) {
        super(caption);
    }

    /**
     * Creates a new checkbox with a caption and a set initial state.
     *
     * @param caption
     *            the caption of the checkbox
     * @param initialState
     *            the initial state of the checkbox
     */
    public FormCheckBox(final String caption, final boolean initialState) {
        super(caption, initialState);
    }

}
