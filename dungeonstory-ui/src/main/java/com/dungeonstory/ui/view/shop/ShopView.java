package com.dungeonstory.ui.view.shop;

import java.util.Iterator;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.Refresher;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.fluent.ui.FVerticalLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = ShopView.URI, displayName = "")
public class ShopView
        extends VerticalLayout
        implements View, Refresher {

    private static final long serialVersionUID = -1712432045147930397L;

    public static final String URI = "shop";

    private Shop shop;

    private VerticalLayout buyLayout;
    private DSLabel        goldValue;

    public ShopView() {
        buyLayout = new FVerticalLayout().withMargin(false).withSpacing(false);
        buyLayout.setWidth(50, Unit.PERCENTAGE);
        setMargin(false);
    }

    @Override
    public void enter(ViewChangeEvent event) {

        Button backButton = new Button("Retour");
        backButton.addClickListener(click -> EventBus.post(new NavigationEvent(ShopListView.URI)));

        Label shopName = new Label();
        DSLabel goldLabel = new DSLabel("Or :");
        goldValue = new DSLabel();
        long gold = 0;

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            return;
        } else {
            shop = Services.getShopService().read(Long.valueOf(event.getParameters()));
            shopName.setValue(shop.getName());
            gold = CurrentUser.get().getCharacter().getGold();
            goldValue.setValue(String.valueOf(gold));
        }
        FHorizontalLayout goldLayout = new FHorizontalLayout(goldLabel, goldValue);

        addComponents(backButton, shopName, goldLayout, buyLayout);

        Panel buyHeaderPanel = new Panel();
        FHorizontalLayout buyHeaderLayout = new FHorizontalLayout().withDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label itemName = new DSLabel("Item");
        Label stockQuantityLabel = new DSLabel("Stock").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new DSLabel("Qte achat").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label unitPriceLabel = new DSLabel("Prix unit.");
        Label totalPriceLabel = new DSLabel("Prix total").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label minusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label plusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);

        HorizontalLayout quantityLayout = new HorizontalLayout(minusLabel, buyQuantityLabel, plusLabel);

        buyHeaderLayout.setWidth(100, Unit.PERCENTAGE);
        buyHeaderLayout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, quantityLayout, totalPriceLabel, buyLabel);
        buyHeaderLayout.setComponentAlignment(buyLabel, Alignment.MIDDLE_RIGHT);
        buyHeaderLayout.setExpandRatio(itemName, 4);
        buyHeaderLayout.setExpandRatio(unitPriceLabel, 1);
        buyHeaderLayout.setExpandRatio(stockQuantityLabel, 1);
        buyHeaderLayout.setExpandRatio(quantityLayout, 0);
        buyHeaderLayout.setExpandRatio(totalPriceLabel, 1);
        buyHeaderLayout.setExpandRatio(buyLabel, 2);
        buyHeaderPanel.setContent(buyHeaderLayout);
        buyLayout.addComponent(buyHeaderPanel);

        for (ShopEquipment shopEquipment : shop.getShopEquipments()) {
            ShopItem item = new ShopItem(shopEquipment, gold, this);
            buyLayout.addComponent(item);
        }

    }

    @Override
    public void refresh() {
        Iterator<Component> iterator = buyLayout.iterator();
        while (iterator.hasNext()) {
            Component c = iterator.next();
            if (c instanceof ShopItem) {
                ShopItem item = (ShopItem) c;
                long remainingGold = CurrentUser.get().getCharacter().getGold();
                goldValue.setValue(String.valueOf(remainingGold));
                item.refresh(remainingGold);
            }
        }

    }

}
