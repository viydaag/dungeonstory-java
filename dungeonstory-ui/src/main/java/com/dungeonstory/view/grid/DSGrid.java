package com.dungeonstory.view.grid;

import com.vaadin.ui.Grid;

public class DSGrid<T> extends Grid<T> {

    private static final long serialVersionUID = -6837101208026738252L;

    public DSGrid() {
        super();
        init();
    }

    /**
     * Constructor used to include all bean properties as columns.
     * @param typeOfRows
     */
    public DSGrid(Class<T> typeOfRows) {
        super(typeOfRows);
        init();
    }

    private void init() {
        setWidth(100, Unit.PERCENTAGE);
        setSelectionMode(SelectionMode.SINGLE);
    }

}
