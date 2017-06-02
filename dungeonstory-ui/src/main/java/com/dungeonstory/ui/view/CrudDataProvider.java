package com.dungeonstory.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.util.Sort;
import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.service.DataService;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.data.sort.SortDirection;

public class CrudDataProvider<T extends Entity> extends AbstractBackEndDataProvider<T, String> {

    private static final long serialVersionUID = 2043736475414313964L;

    private DataService<T, Long> service;

    /** Text filter that can be changed separately. */
    private String filterText = "";

    /** Property on which filter is applied */
    private String filterProperty;


    public CrudDataProvider(DataService<T, Long> service) {
        this(service, null);
    }

    public CrudDataProvider(DataService<T, Long> service, String filterProperty) {
        this.service = service;
        this.filterProperty = filterProperty;
    }

    @Override
    protected Stream<T> fetchFromBackEnd(Query<T, String> query) {
        // The index of the first item to load
        int offset = query.getOffset();

        // The number of items to load
        int limit = query.getLimit();

        List<Sort> sortOrders = new ArrayList<>();
        query.getSortOrders().forEach(queryOrder -> {
            Sort sort = service.createSort(
                    // The name of the sorted property
                    queryOrder.getSorted(),
                    // The sort direction for this property
                    queryOrder.getDirection() == SortDirection.DESCENDING);
            sortOrders.add(sort);
        });

        List<T> list = new ArrayList<>();
        if (StringUtils.isNoneBlank(filterProperty) && StringUtils.isNoneBlank(filterText)) {
            list = service.findAllByLikePaged(filterProperty, filterText, offset, limit);
        } else {
            list = service.findAllPagedOrderBy(offset, limit, sortOrders);
        }

        return list.stream();
    }

    @Override
    protected int sizeInBackEnd(Query<T, String> query) {
        return (int) fetchFromBackEnd(query).count();
    }

    public T get(Long id) {
        if (id == null) {
            return null;
        }
        return service.read(id);
    }

    public boolean refresh(T entity) {
        boolean isNew = false;
        if (entity != null) {
            if (entity.getId() == null) {
                isNew = true;
            } else {
                service.refresh(entity);
            }
        }
        return isNew;
    }

    public void save(T entity) {
        boolean newEntity = entity.getId() == null;

        service.saveOrUpdate(entity);
        if (newEntity) {
            refreshAll();
        } else {
            refreshItem(entity);
        }
    }

    public void delete(T entity) {
        service.delete(entity);
        refreshAll();
    }

    /**
     * Sets the filter to use for the this data provider and refreshes data.
     * <p>
     * Filter is compared for product name, availability and category.
     * 
     * @param filterText
     *           the text to filter by, never null
     */
    public void setFilter(String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null");
        if (StringUtils.isBlank(filterProperty) || Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim();

        refreshAll();
    }

    public String getFilterProperty() {
        return filterProperty;
    }

    public void setFilterProperty(String filterProperty) {
        this.filterProperty = filterProperty;
    }

}
