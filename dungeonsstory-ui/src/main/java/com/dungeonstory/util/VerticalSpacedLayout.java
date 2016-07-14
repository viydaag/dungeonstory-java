package com.dungeonstory.util;

import com.vaadin.ui.VerticalLayout;

/**
 * Vertical layout with spacing and margin on by default
 */
public class VerticalSpacedLayout extends VerticalLayout {

    private static final long serialVersionUID = -4252014787205844669L;

    public VerticalSpacedLayout() {
        setMargin(true);
        setSpacing(true);
        addStyleName(DSTheme.SCROLLABLE);
    }
}
