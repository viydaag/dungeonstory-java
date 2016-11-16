package com.dungeonstory.util.layout;

import com.dungeonstory.util.DSTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public class HorizontalSpacedLayout extends HorizontalLayout {

    private static final long serialVersionUID = 3615570224237914057L;

    public HorizontalSpacedLayout() {
        setMargin(true);
        setSpacing(true);
        addStyleName(DSTheme.SCROLLABLE);
    }

    public HorizontalSpacedLayout(Component... children) {
        super(children);
        setMargin(true);
        setSpacing(true);
        addStyleName(DSTheme.SCROLLABLE);
    }


}
