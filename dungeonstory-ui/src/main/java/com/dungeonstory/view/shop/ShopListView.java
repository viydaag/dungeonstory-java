package com.dungeonstory.view.shop;

import java.util.List;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.service.impl.ShopService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.ViewConfig.CreateMode;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = ShopListView.URI, displayName = "Magasins", createMode = CreateMode.EAGER_INIT)
public class ShopListView extends VerticalLayout implements View {

    private static final long serialVersionUID = -7089021010337428023L;

    public static final String URI = "shopList";

    protected DSGrid<Shop> grid;
    protected ShopService  service;

    private List<Shop> shopList;

    public ShopListView() {
        grid = new DSGrid<>(Shop.class);
        service = ShopService.getInstance();

        grid.addSelectionListener(
                selection -> {
                    if (grid.asSingleSelect().getValue() != null) {
                        EventBus.post(new NavigationEvent(ShopView.URI + "/" + grid.asSingleSelect().getValue().getId()));
                        grid.deselectAll();
                    }
                });

        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        if (shopList == null || shopList.isEmpty()) {
            shopList = service.findAll();
        }
        
        grid.setItems(shopList);
    }

}
