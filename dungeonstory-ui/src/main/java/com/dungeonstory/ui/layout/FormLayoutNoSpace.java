package com.dungeonstory.ui.layout;

import com.vaadin.fluent.ui.FFormLayout;
import com.vaadin.ui.Component;

public class FormLayoutNoSpace
        extends FFormLayout {

    private static final long serialVersionUID = -5128706580463856826L;

    public FormLayoutNoSpace() {
        super();
        init();
    }

    public FormLayoutNoSpace(Component... children) {
        super(children);
        init();
    }
    
    private void init() {
        setMargin(false);
        setSpacing(false);
    }

}
