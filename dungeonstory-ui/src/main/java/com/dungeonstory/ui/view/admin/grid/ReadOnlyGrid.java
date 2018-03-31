package com.dungeonstory.ui.view.admin.grid;

import com.vaadin.ui.Grid;

public class ReadOnlyGrid<T> extends Grid<T> {

    private static final long serialVersionUID = 3120381338351028606L;

    public ReadOnlyGrid() {
        super();
        init();
    }

    /**
     * Constructor used to include all bean properties as columns.
     * @param typeOfRows
     */
    public ReadOnlyGrid(Class<T> typeOfRows) {
        super(typeOfRows);
        init();
    }

    private void init() {
        setWidth(100, Unit.PERCENTAGE);
        setSelectionMode(SelectionMode.NONE);
    }

}
