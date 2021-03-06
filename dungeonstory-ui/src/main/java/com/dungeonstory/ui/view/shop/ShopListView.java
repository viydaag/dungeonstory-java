package com.dungeonstory.ui.view.shop;

import java.util.List;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.ShopDataService;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.util.ViewConfig.CreateMode;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = ShopListView.URI, displayName = "Magasins", createMode = CreateMode.EAGER_INIT)
public class ShopListView extends VerticalLayout implements View {

    private static final long serialVersionUID = -7089021010337428023L;

    public static final String URI = "shopList";

    protected DSGrid<Shop> grid;
    protected ShopDataService  service;

    private List<Shop> shopList;

    public ShopListView() {
        grid = new DSGrid<>();
        service = Services.getShopService();

        grid.addColumn(Shop::getName);
        grid.addColumn(Shop::getCity);

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
