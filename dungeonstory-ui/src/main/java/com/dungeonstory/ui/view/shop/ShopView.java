package com.dungeonstory.ui.view.shop;

import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.shop.EquipmentItem.ItemSoldEvent;
import com.dungeonstory.ui.view.shop.ShopItem.ItemBoughtEvent;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.fluent.ui.FVerticalLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = ShopView.URI, displayName = "")
public class ShopView
        extends VerticalLayout
        implements View, EquipmentItem.SellItemListener, ShopItem.BuyItemListener {

    private static final long serialVersionUID = -1712432045147930397L;

    public static final String URI = "shop";

    private Shop shop;

    private HorizontalLayout shopLayout;

    private VerticalLayout buyLayout;
    private VerticalLayout sellLayout;

    private DSLabel        goldValue;

    public ShopView() {
        buyLayout = new FVerticalLayout().withMargin(false).withSpacing(false);
        sellLayout = new FVerticalLayout().withMargin(false).withSpacing(false);
        //        buyLayout.setWidth(50, Unit.PERCENTAGE);
        setMargin(false);

        shopLayout = new FHorizontalLayout(buyLayout, sellLayout).withWidth(100, Unit.PERCENTAGE);
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

        addComponents(backButton, shopName, goldLayout, shopLayout);

        buildBuyPanel(gold);
        buildSellPanel();

    }

    private void buildBuyPanel(long gold) {
        Panel buyHeaderPanel = new Panel();
        FHorizontalLayout buyHeaderLayout = new FHorizontalLayout().withDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label itemName = new DSLabel("Item");
        Label stockQuantityLabel = new DSLabel("Stock").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new DSLabel("Quantité achat").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
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
            ShopItem item = new ShopItem(shopEquipment, gold);
            item.addBuyItemListener(this);
            buyLayout.addComponent(item);
        }

    }

    private void buildSellPanel() {
        Panel sellHeaderPanel = new Panel();
        FHorizontalLayout sellHeaderLayout = new FHorizontalLayout().withDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label itemName = new DSLabel("Item");
        Label stockQuantityLabel = new DSLabel("Stock").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new DSLabel("Quantité vente").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label unitPriceLabel = new DSLabel("Valeur unit.");
        Label totalPriceLabel = new DSLabel("Valeur total").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label minusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label plusLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyLabel = new DSLabel("").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);

        HorizontalLayout quantityLayout = new HorizontalLayout(minusLabel, buyQuantityLabel, plusLabel);

        sellHeaderLayout.setWidth(100, Unit.PERCENTAGE);
        sellHeaderLayout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, quantityLayout, totalPriceLabel, buyLabel);
        sellHeaderLayout.setComponentAlignment(buyLabel, Alignment.MIDDLE_RIGHT);
        sellHeaderLayout.setExpandRatio(itemName, 4);
        sellHeaderLayout.setExpandRatio(unitPriceLabel, 1);
        sellHeaderLayout.setExpandRatio(stockQuantityLabel, 1);
        sellHeaderLayout.setExpandRatio(quantityLayout, 0);
        sellHeaderLayout.setExpandRatio(totalPriceLabel, 1);
        sellHeaderLayout.setExpandRatio(buyLabel, 2);
        sellHeaderPanel.setContent(sellHeaderLayout);
        sellLayout.addComponent(sellHeaderPanel);

        for (CharacterEquipment equipment : CurrentUser.get().getCharacter().getEquipment()) {
            EquipmentItem item = new EquipmentItem(equipment, shop);
            item.addSellItemListener(this);
            sellLayout.addComponent(item);
        }

    }

    //    @Override
    //    public void refresh() {
    //        //        Iterator<Component> iterator = buyLayout.iterator();
    //        //        while (iterator.hasNext()) {
    //        //            Component c = iterator.next();
    //        //            if (c instanceof ShopItem) {
    //        //                ShopItem item = (ShopItem) c;
    //        //                long remainingGold = CurrentUser.get().getCharacter().getGold();
    //        //                goldValue.setValue(String.valueOf(remainingGold));
    //        //                item.refresh(remainingGold);
    //        //            }
    //        //        }
    //        long remainingGold = CurrentUser.get().getCharacter().getGold();
    //        goldValue.setValue(String.valueOf(remainingGold));
    //
    //        buyLayout.removeAllComponents();
    //        buildBuyPanel(remainingGold);
    //
    //    }

    @Override
    public void itemSold(ItemSoldEvent event) {
        refreshAllItems();
    }

    @Override
    public void itemBought(ItemBoughtEvent event) {
        refreshAllItems();
    }

    private void refreshAllItems() {
        long remainingGold = CurrentUser.get().getCharacter().getGold();
        goldValue.setValue(String.valueOf(remainingGold));

        sellLayout.removeAllComponents();
        buildSellPanel();

        buyLayout.removeAllComponents();
        buildBuyPanel(remainingGold);
    }

}
