package com.dungeonstory.samples.crud;

import java.util.Collection;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.ui.Grid;

public class BeanGrid<T> extends Grid {

	private static final long serialVersionUID = -1281570940876676655L;

	public BeanGrid(Class<T> beanClass) {
		setSizeFull();

        setSelectionMode(SelectionMode.SINGLE);

        BeanItemContainer<T> container = new BeanItemContainer<T>(beanClass);
        setContainerDataSource(container);
	}
	
	protected BeanItemContainer<T> getContainer() {
        return (BeanItemContainer<T>) super.getContainerDataSource();
    }

    @Override
    public T getSelectedRow() throws IllegalStateException {
        return (T) super.getSelectedRow();
    }

    public void setData(Collection<T> data) {
        getContainer().removeAllItems();
        getContainer().addAll(data);
    }

    public void refresh(T bean) {
        // We avoid updating the whole table through the backend here so we can
        // get a partial update for the grid
        BeanItem<T> item = getContainer().getItem(bean);
        if (item != null) {
            // Updated product
            MethodProperty<T> p = (MethodProperty<T>) item.getItemProperty("id");
            p.fireValueChange();
        } else {
            // New product
            getContainer().addBean(bean);
        }
    }

    public void remove(T bean) {
        getContainer().removeItem(bean);
    }


}
