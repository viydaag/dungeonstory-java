package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ShopEquipmentId.class)
@Table(name = "ShopEquipment")
public class ShopEquipment {

    @Id
    @Column(name = "shopId")
    private Long shopId;

    @Id
    @Column(name = "equipmentId")
    private Long equipmentId;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "unitPrice")
    private int unitPrice;
    
    @ManyToOne
    @JoinColumn(name = "shopId", updatable = false, insertable = false)
    private Shop shop;
    
    @ManyToOne
    @JoinColumn(name = "equipmentId", updatable = false, insertable = false)
    private Equipment equipment;
    
    public ShopEquipment() {
        super();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

}
