package com.dungeonstory.view.grid;

import java.util.List;

import org.vaadin.viritin.LazyList.CountProvider;
import org.vaadin.viritin.LazyList.PagingProvider;
import org.vaadin.viritin.SortableLazyList.SortablePagingProvider;
import org.vaadin.viritin.grid.MGrid;

public class DSGrid<T> extends MGrid<T> {

    private static final long serialVersionUID = -6837101208026738252L;

    public DSGrid(Class<T> typeOfRows) {
        super(typeOfRows);
        init();
    }

    public DSGrid(List<T> listOfEntities) {
        super(listOfEntities);
        init();
    }

    public DSGrid(PagingProvider<T> pageProvider, CountProvider countProvider) {
        super(pageProvider, countProvider);
        init();
    }

    public DSGrid(PagingProvider<T> pageProvider, CountProvider countProvider, int pageSize) {
        super(pageProvider, countProvider, pageSize);
        init();
    }

    public DSGrid(SortablePagingProvider<T> pageProvider, CountProvider countProvider) {
        super(pageProvider, countProvider);
        init();
    }

    public DSGrid(SortablePagingProvider<T> pageProvider, CountProvider countProvider, int pageSize) {
        super(pageProvider, countProvider, pageSize);
        init();
    }

    private void init() {
        setSizeFull();
        setSelectionMode(SelectionMode.SINGLE);
    }

}
