package com.dungeonstory.ui.view.shop;


import java.io.Serializable;
import java.lang.reflect.Method;

import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.rules.ShopRules;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.event.CharacterUpdatedEvent;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.field.DSIntegerField;
import com.dungeonstory.ui.field.IntegerField;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.fluent.ui.FButton;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.Registration;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

public class ShopItem extends CustomComponent {

    private static final long serialVersionUID = 8179307377449371715L;
    
    private ShopEquipment item;
    private long          remainingGold;

    private IntegerField buyQuantityField;
    private Label        unitPriceLabel;
    private Button       buyButton;

    private FButton minusButton;

    private Label totalPriceLabel;

    public ShopItem(ShopEquipment item, long remainingGold) {
        this.item = item;
        this.remainingGold = remainingGold;
        
        Panel panel = new Panel();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth(100, Unit.PERCENTAGE);
        
        Label itemName = new DSLabel(this.item.getEquipment().getName()).withStyleName(ValoTheme.LABEL_SMALL);
        Label stockQuantityLabel = new DSLabel(String.valueOf(this.item.getQuantity())).withStyleName(DSTheme.TEXT_CENTER_ALIGNED, ValoTheme.LABEL_SMALL);

        buyQuantityField = new DSIntegerField().withValue(0).withWidth("50px").withStyleName(ValoTheme.TEXTFIELD_SMALL);
        buyQuantityField.setAllowNegative(false);
        buyQuantityField.addValueChangeListener(event -> {
            adjustTotalPrice();
            checkBuyButtonAvailibility();
            adjustMinusButton();
        });

        unitPriceLabel = new DSLabel(String.valueOf(this.item.getUnitPrice())).withStyleName(DSTheme.TEXT_CENTER_ALIGNED, ValoTheme.LABEL_SMALL);
        totalPriceLabel = new DSLabel().withStyleName(DSTheme.TEXT_CENTER_ALIGNED, ValoTheme.LABEL_SMALL);
        
        itemName.addContextClickListener(event -> {
            if (event != null && event.getButton() == MouseButton.RIGHT) {
                showItemWindow();
            }
        });
        
        minusButton = new FButton(VaadinIcons.MINUS).withStyleName(ValoTheme.BUTTON_SMALL);
        minusButton.addClickListener(event -> {
            //            add(stockQuantityLabel, 1);
            substract(buyQuantityField, 1);
            adjustTotalPrice();
            checkBuyButtonAvailibility();
            adjustMinusButton();
        });
        
        FButton plusButton = new FButton(VaadinIcons.PLUS).withStyleName(ValoTheme.BUTTON_SMALL);
        plusButton.addClickListener(event -> {
            add(buyQuantityField, 1);
            adjustTotalPrice();
            checkBuyButtonAvailibility();
            adjustMinusButton();
        });
        
        buyButton = new Button("Acheter");
        buyButton.addStyleName(ValoTheme.BUTTON_SMALL);
        buyButton.addClickListener(event -> {
            int quantity = buyQuantityField.getValue();
            ShopRules.buyItem(CurrentUser.get().getCharacter(), item.getShop(), item, quantity);
            EventBus.post(new CharacterUpdatedEvent());
            substract(stockQuantityLabel, quantity);
            fireEvent(new ItemBoughtEvent(this, item));
            //            refresher.refresh();
        });
        
        adjustTotalPrice();
        adjustMinusButton();
        checkBuyButtonAvailibility();
        
        CssLayout quantityLayout = new CssLayout(minusButton, buyQuantityField, plusButton);
        quantityLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        layout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, quantityLayout, totalPriceLabel, buyButton);
        layout.setComponentAlignment(buyButton, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(itemName, 4);
        layout.setExpandRatio(unitPriceLabel, 1);
        layout.setExpandRatio(stockQuantityLabel, 1);
        layout.setExpandRatio(quantityLayout, 0);
        layout.setExpandRatio(totalPriceLabel, 1);
        layout.setExpandRatio(buyButton, 0);
        panel.setContent(layout);
        
        setCompositionRoot(panel);

        //TODO : listener on user value changed for buyQuantityField 
    }

    private void adjustTotalPrice() {
        if (buyQuantityField.getValue() != null) {
            totalPriceLabel.setValue(String.valueOf(calculateBuyPrice(buyQuantityField.getValue(), this.item.getUnitPrice())));
        } else {
            totalPriceLabel.setValue(String.valueOf(0));
        }
    }

    private void adjustMinusButton() {
        minusButton.setEnabled(buyQuantityField.getValue() != null && buyQuantityField.getValue() > 0);
    }
    
    private int calculateBuyPrice(int buyQuantity, int price) {
        return buyQuantity * price;
    }
    
    private void showItemWindow() {
        //        Messages messages = Messages.getInstance();
        Window window = new Window(this.item.getEquipment().getName());
        window.setModal(true);
        window.setWidth("60%");

        FormLayout layout = new FormLayout();
        DSLabel description = new DSLabel("Description", this.item.getEquipment().getDescription());
        layout.addComponents(description);

        //TODO : other useful info

        window.setContent(layout);
        UI.getCurrent().addWindow(window);
    }
    
    public void refresh(long remainingGold) {
        this.remainingGold = remainingGold;
        checkBuyButtonAvailibility();
    }

    private void checkBuyButtonAvailibility() {
        if (buyQuantityField.getValue() != null) {
            int total = buyQuantityField.getValue() * Integer.parseInt(unitPriceLabel.getValue());
            buyButton.setEnabled(total > 0 && total <= this.remainingGold);
        } else {
            buyButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
	private void substract(Component c, int value) {
        if (c instanceof AbstractField) {
            ((AbstractField<Integer>) c).setValue(((AbstractField<Integer>) c).getValue() - value);
        } else if (c instanceof Label) {
            ((Label) c).setValue(String.valueOf(Integer.parseInt(((Label) c).getValue()) - value));
        }
    }

    @SuppressWarnings("unchecked")
	private void add(Component c, int value) {
        if (c instanceof AbstractField) {
            ((AbstractField<Integer>) c).setValue(((AbstractField<Integer>) c).getValue() + value);
        } else if (c instanceof Label) {
            ((Label) c).setValue(String.valueOf(Integer.parseInt(((Label) c).getValue()) + value));
        }
    }
    
    public Registration addBuyItemListener(BuyItemListener listener) {
        return addListener(ItemBoughtEvent.class, listener, BuyItemListener.ITEM_BOUGHT_METHOD);
    }

    public class ItemBoughtEvent
            extends Event {

        private static final long serialVersionUID = -2459515843182821358L;

        private final ShopEquipment item;

        public ItemBoughtEvent(Component source, ShopEquipment item) {
            super(source);
            this.item = item;
        }

        public ShopEquipment getItem() {
            return item;
        }

    }

    /**
     * Interface for listening for a {@link ItemBoughtEvent} fired by a
     * {@link Component}.
     *
     */
    @FunctionalInterface
    public interface BuyItemListener
            extends Serializable {

        public static final Method ITEM_BOUGHT_METHOD = ReflectTools.findMethod(BuyItemListener.class, "itemBought",
                ItemBoughtEvent.class);

        public void itemBought(ItemBoughtEvent event);
    }

}
