package com.dungeonstory.ui.event;

public class ShopItemValueChangedEvent {

    private int quantity;
    private int unitPrice;

    public ShopItemValueChangedEvent(int quantity, int unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

}
