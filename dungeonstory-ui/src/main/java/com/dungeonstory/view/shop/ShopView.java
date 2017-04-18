package com.dungeonstory.view.shop;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.service.impl.ShopService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

@ViewConfig(uri = ShopView.URI, displayName = "")
public class ShopView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = -1712432045147930397L;

    public static final String URI = "shop";

    protected ShopService service;
    private Shop          shop;

    public ShopView() {
        service = ShopService.getInstance();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        
        Button backButton = new Button("Retour");
        backButton.addClickListener(click -> EventBus.post(new NavigationEvent(ShopListView.URI)));
        
        Label shopName = new Label();

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            return;
        } else {
            shop = service.read(Long.valueOf(event.getParameters()));
            shopName.setValue(shop.getName());
        }
        
        addComponents(backButton, shopName);

    }

}
