package com.dungeonstory.view.shop;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.impl.ShopService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = ShopView.URI, displayName = "")
public class ShopView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = -1712432045147930397L;

    public static final String URI = "shop";

    protected ShopService service;
    private Shop          shop;
    
    private VerticalLayout buyLayout;

    public ShopView() {
        service = ShopService.getInstance();
        buyLayout = new VerticalLayout();
        buyLayout.setWidth(50, Unit.PERCENTAGE);
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
        
        addComponents(backButton, shopName, buyLayout);
        
        Panel buyHeaderPanel = new Panel();
        HorizontalSpacedLayout buyHeaderLayout = new HorizontalSpacedLayout();
        Label itemName = new MLabel("Item");
        Label stockQuantityLabel = new MLabel("Stock").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new MLabel("Qte achat").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label unitPriceLabel = new MLabel("Prix unitaire");
        Label totalPriceLabel = new MLabel("Prix total").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label minusLabel = new MLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label plusLabel = new MLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyLabel = new MLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        buyHeaderLayout.setWidth(100, Unit.PERCENTAGE);
        buyHeaderLayout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, minusLabel, buyQuantityLabel, plusLabel, totalPriceLabel, buyLabel);
        buyHeaderLayout.setComponentAlignment(buyLabel, Alignment.MIDDLE_RIGHT);
        buyHeaderLayout.setExpandRatio(itemName, 4);
        buyHeaderLayout.setExpandRatio(unitPriceLabel, 1);
        buyHeaderLayout.setExpandRatio(stockQuantityLabel, 1);
        buyHeaderLayout.setExpandRatio(minusLabel, 1);
        buyHeaderLayout.setExpandRatio(buyQuantityLabel, 1);
        buyHeaderLayout.setExpandRatio(plusLabel, 1);
        buyHeaderLayout.setExpandRatio(totalPriceLabel, 1);
        buyHeaderLayout.setExpandRatio(buyLabel, 2);
        buyHeaderPanel.setContent(buyHeaderLayout);
        buyLayout.addComponent(buyHeaderPanel);
        
        for (ShopEquipment shopEquipment : shop.getShopEquipments()) {
            ShopItem item = new ShopItem(shopEquipment);
            buyLayout.addComponent(item);
        }

    }

}
