package com.dungeonstory.ui.view.shop;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.rules.ShopRules;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.event.CharacterUpdatedEvent;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.fluent.ui.FButton;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

public class EquipmentItem
        extends CustomComponent {

    private static final long serialVersionUID = 8179307377449371715L;

    private CharacterEquipment item;

    //    private IntegerField sellQuantityField;
    private Label        valueLabel;
    //    private Label        totalValueLabel;
    //    private Label        stockQuantityLabel;

    private Button sellButton;
    //    private Button minusButton;
    //    private Button plusButton;





    public EquipmentItem(CharacterEquipment item, Shop shop) {
        this.item = item;

        Panel panel = new Panel();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth(100, Unit.PERCENTAGE);

        Label itemName = new DSLabel(this.item.getEquipment().getName()).withStyleName(ValoTheme.LABEL_SMALL);
        //        stockQuantityLabel = new DSLabel(String.valueOf(this.item.getQuantity())).withStyleName(DSTheme.TEXT_CENTER_ALIGNED,
        //                ValoTheme.LABEL_SMALL);
        //
        //        sellQuantityField = new DSIntegerField().withValue(0).withWidth("50px").withStyleName(ValoTheme.TEXTFIELD_SMALL);
        //        sellQuantityField.setAllowNegative(false);
        //        sellQuantityField.addValueChangeListener(event -> {
        //            adjustTotalValue();
        //            checkSellButtonAvailibility();
        //            adjustMinusButton();
        //            adjustPlusButton();
        //        });

        valueLabel = new DSLabel(String.valueOf(this.item.getSellableValue())).withStyleName(DSTheme.TEXT_CENTER_ALIGNED,
                ValoTheme.LABEL_SMALL);
        //        totalValueLabel = new DSLabel().withStyleName(DSTheme.TEXT_CENTER_ALIGNED, ValoTheme.LABEL_SMALL);

        itemName.addContextClickListener(event -> {
            if (event != null && event.getButton() == MouseButton.RIGHT) {
                showItemWindow();
            }
        });

        //        minusButton = new FButton(VaadinIcons.MINUS).withStyleName(ValoTheme.BUTTON_SMALL);
        //        minusButton.addClickListener(event -> {
        //            substract(sellQuantityField, 1);
        //            adjustTotalValue();
        //            //            checkSellButtonAvailibility();
        //            //            adjustMinusButton();
        //            //            adjustPlusButton();
        //        });

        //        plusButton = new FButton(VaadinIcons.PLUS).withStyleName(ValoTheme.BUTTON_SMALL);
        //        plusButton.addClickListener(event -> {
        //            add(sellQuantityField, 1);
        //            adjustTotalValue();
        //            //            checkSellButtonAvailibility();
        //            //            adjustMinusButton();
        //        });

        sellButton = new FButton("Vendre").withStyleName(ValoTheme.BUTTON_SMALL);
        sellButton.addClickListener(event -> {
            //            int quantity = sellQuantityField.getValue();
            ShopRules.sellItem(CurrentUser.get().getCharacter(), shop, item);
            EventBus.post(new CharacterUpdatedEvent());
            //            substract(stockQuantityLabel, quantity);
            fireEvent(new ItemSoldEvent(this, item));
        });

        //        adjustTotalValue();
        //        adjustMinusButton();
        //        adjustPlusButton();
        checkSellButtonAvailibility();

        //        CssLayout quantityLayout = new CssLayout(minusButton, sellQuantityField, plusButton);
        //        quantityLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        layout.addComponents(valueLabel, itemName, sellButton);
        layout.setComponentAlignment(sellButton, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(itemName, 4);
        layout.setExpandRatio(valueLabel, 1);
        //        layout.setExpandRatio(stockQuantityLabel, 1);
        //        layout.setExpandRatio(quantityLayout, 0);
        //        layout.setExpandRatio(totalValueLabel, 1);
        layout.setExpandRatio(sellButton, 0);
        panel.setContent(layout);

        setCompositionRoot(panel);

        //TODO : listener on user value changed for buyQuantityField 
    }

    //    private void adjustTotalValue() {
    //        if (sellQuantityField.getValue() != null) {
    //            totalValueLabel.setValue(
    //                    String.valueOf(calculateSellValue(sellQuantityField.getValue(), this.item.getSellableValue())));
    //        } else {
    //            totalValueLabel.setValue(String.valueOf(0));
    //        }
    //    }

    //    private void adjustMinusButton() {
    //        minusButton.setEnabled(sellQuantityField.getValue() != null && sellQuantityField.getValue() > 0);
    //    }
    //
    //    private void adjustPlusButton() {
    //        plusButton.setEnabled(sellQuantityField.getValue() == null
    //                || (stockQuantityLabel.getValue() != null && sellQuantityField.getValue() != null
    //                        && sellQuantityField.getValue() <= Integer.parseInt(stockQuantityLabel.getValue())));
    //    }

    //    private int calculateSellValue(int sellQuantity, int value) {
    //        return sellQuantity * value;
    //    }

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
        checkSellButtonAvailibility();
    }

    private void checkSellButtonAvailibility() {
        //        if (sellQuantityField.getValue() != null) {
        //            int total = sellQuantityField.getValue() * Integer.parseInt(valueLabel.getValue());
        //            sellButton.setEnabled(total > 0);
        //        } else {
        //            sellButton.setEnabled(false);
        //        }
        sellButton.setEnabled(this.item.getEquipment().getIsSellable());
    }

    //    @SuppressWarnings("unchecked")
    //    private void substract(Component c, int value) {
    //        if (c instanceof AbstractField) {
    //            ((AbstractField<Integer>) c).setValue(((AbstractField<Integer>) c).getValue() - value);
    //        } else if (c instanceof Label) {
    //            ((Label) c).setValue(String.valueOf(Integer.parseInt(((Label) c).getValue()) - value));
    //        }
    //    }
    //
    //    @SuppressWarnings("unchecked")
    //    private void add(Component c, int value) {
    //        if (c instanceof AbstractField) {
    //            ((AbstractField<Integer>) c).setValue(((AbstractField<Integer>) c).getValue() + value);
    //        } else if (c instanceof Label) {
    //            ((Label) c).setValue(String.valueOf(Integer.parseInt(((Label) c).getValue()) + value));
    //        }
    //    }

    public Registration addSellItemListener(SellItemListener listener) {
        return addListener(ItemSoldEvent.class, listener, SellItemListener.ITEM_SOLD_METHOD);
    }

    public class ItemSoldEvent
            extends Event {

        private static final long serialVersionUID = -2163666769951411798L;

        private final CharacterEquipment item;

        public ItemSoldEvent(Component source, CharacterEquipment item) {
            super(source);
            this.item = item;
        }

        public CharacterEquipment getItem() {
            return item;
        }

    }

    /**
     * Interface for listening for a {@link ItemSoldEvent} fired by a
     * {@link Component}.
     *
     */
    @FunctionalInterface
    public interface SellItemListener
            extends Serializable {

        public static final Method ITEM_SOLD_METHOD = ReflectTools.findMethod(SellItemListener.class, "itemSold",
                ItemSoldEvent.class);

        public void itemSold(ItemSoldEvent event);
    }

}
