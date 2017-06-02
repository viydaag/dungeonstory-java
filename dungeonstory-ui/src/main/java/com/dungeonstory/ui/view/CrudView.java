package com.dungeonstory.ui.view;

import com.vaadin.navigator.View;

public interface CrudView<T> extends View {

    public void entrySaved(T entity);
    public void entryReset(T entity);
    public void entrySelected(T entity);
    public void deleteSelected(T entity);
}
