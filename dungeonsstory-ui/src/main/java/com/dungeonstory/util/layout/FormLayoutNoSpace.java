package com.dungeonstory.util.layout;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class FormLayoutNoSpace extends FormLayout {

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
