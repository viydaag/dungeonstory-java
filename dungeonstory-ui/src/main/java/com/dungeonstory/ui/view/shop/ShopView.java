package com.dungeonstory.ui.view.shop;

import java.util.Iterator;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.ShopDataService;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.Refresher;
import com.dungeonstory.ui.util.ViewConfig;
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

    protected ShopDataService service;
    private Shop              shop;

    private VerticalLayout buyLayout;
    private DSLabel goldLabel;

    public ShopView() {
        service = Services.getShopService();
        buyLayout = new VerticalLayout();
        buyLayout.setWidth(50, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeEvent event) {

        Button backButton = new Button("Retour");
        backButton.addClickListener(click -> EventBus.post(new NavigationEvent(ShopListView.URI)));

        Label shopName = new Label();
        goldLabel = new DSLabel().withCaption("Or :");
        long gold = 0;

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            return;
        } else {
            shop = service.read(Long.valueOf(event.getParameters()));
            shopName.setValue(shop.getName());
            gold = CurrentUser.get().getCharacter().getGold();
            goldLabel.setValue(String.valueOf(gold));
        }

        addComponents(backButton, shopName, goldLabel, buyLayout);

        Panel buyHeaderPanel = new Panel();
        HorizontalLayout buyHeaderLayout = new HorizontalLayout();
        Label itemName = new DSLabel("Item");
        Label stockQuantityLabel = new DSLabel("Stock").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new DSLabel("Qte achat").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label unitPriceLabel = new DSLabel("Prix unitaire");
        Label totalPriceLabel = new DSLabel("Prix total").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label minusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label plusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        buyHeaderLayout.setWidth(100, Unit.PERCENTAGE);
        buyHeaderLayout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, minusLabel, buyQuantityLabel,
                plusLabel, totalPriceLabel, buyLabel);
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
                goldLabel.setValue(String.valueOf(remainingGold));
                item.refresh(remainingGold);
            }
        }

    }

}
