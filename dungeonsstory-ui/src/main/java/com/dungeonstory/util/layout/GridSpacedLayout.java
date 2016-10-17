package com.dungeonstory.util.layout;

import com.dungeonstory.util.DSTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

public class GridSpacedLayout extends GridLayout {

    private static final long serialVersionUID = 7205667658951599842L;

    public GridSpacedLayout() {
        init();
    }

    public GridSpacedLayout(int columns, int rows) {
        super(columns, rows);
        init();
    }

    public GridSpacedLayout(int columns, int rows, Component... children) {
        super(columns, rows, children);
        init();
    }

    private void init() {
        setMargin(true);
        setSpacing(true);
        addStyleName(DSTheme.SCROLLABLE);
    }

}
