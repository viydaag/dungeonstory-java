package com.dungeonstory.view.shop;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.DSTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class ShopItem extends CustomComponent {

    private static final long serialVersionUID = 8179307377449371715L;
    
    private ShopEquipment item;

    public ShopItem(ShopEquipment item) {
        this.item = item;
        
        Panel panel = new Panel();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth(100, Unit.PERCENTAGE);
        
        Label itemName = new MLabel(this.item.getEquipment().getName());
        Label stockQuantityLabel = new MLabel(String.valueOf(this.item.getQuantity())).withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label buyQuantityLabel = new MLabel("0").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        Label unitPriceLabel = new MLabel(String.valueOf(this.item.getUnitPrice()));
        Label totalPriceLabel = new MLabel().withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
        
        itemName.addContextClickListener(event -> {
            if (event != null && event.getButton() == MouseButton.RIGHT) {
                showItemWindow();
            }
        });
        
        Button minusButton = new Button(VaadinIcons.MINUS);
        minusButton.addClickListener(event -> {
            int stockQuantity = Integer.parseInt(stockQuantityLabel.getValue());
            int buyQuantity = Integer.parseInt(buyQuantityLabel.getValue());
            stockQuantity++;
            buyQuantity--;
            stockQuantityLabel.setValue(String.valueOf(stockQuantity));
            buyQuantityLabel.setValue(String.valueOf(buyQuantity));
            totalPriceLabel.setValue(String.valueOf(calculateBuyPrice(buyQuantity, this.item.getUnitPrice())));
        });
        
        Button plusButton = new Button(VaadinIcons.PLUS);
        plusButton.addClickListener(event -> {
            int stockQuantity = Integer.parseInt(stockQuantityLabel.getValue());
            int buyQuantity = Integer.parseInt(buyQuantityLabel.getValue());
            stockQuantity--;
            buyQuantity++;
            stockQuantityLabel.setValue(String.valueOf(stockQuantity));
            buyQuantityLabel.setValue(String.valueOf(buyQuantity));
            totalPriceLabel.setValue(String.valueOf(calculateBuyPrice(buyQuantity, this.item.getUnitPrice())));
        });
        
        Button buyButton = new Button("Acheter");
        buyButton.addClickListener(event -> {
            int quantity = Integer.parseInt(buyQuantityLabel.getValue());
            this.item.setQuantity(quantity);
        });
        
        layout.addComponents(unitPriceLabel, itemName, stockQuantityLabel, minusButton, buyQuantityLabel, plusButton, totalPriceLabel, buyButton);
        layout.setComponentAlignment(buyButton, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(itemName, 4);
        layout.setExpandRatio(unitPriceLabel, 1);
        layout.setExpandRatio(stockQuantityLabel, 1);
        layout.setExpandRatio(minusButton, 1);
        layout.setExpandRatio(buyQuantityLabel, 1);
        layout.setExpandRatio(plusButton, 1);
        layout.setExpandRatio(totalPriceLabel, 1);
        layout.setExpandRatio(buyButton, 2);
        panel.setContent(layout);
        
        setCompositionRoot(panel);
    }
    
    private int calculateBuyPrice(int buyQuantity, int price) {
        return buyQuantity * price;
    }
    
    private void showItemWindow() {
        Messages messages = Messages.getInstance();
        Window window = new Window(this.item.getEquipment().getName());
        window.setModal(true);
        window.setWidth("60%");

        FormLayout layout = new FormLayout();
        MLabel description = new MLabel("Description", this.item.getEquipment().getDescription());
        layout.addComponents(description);

        //TODO : other useful info

        window.setContent(layout);
        UI.getCurrent().addWindow(window);
    }
    

}
