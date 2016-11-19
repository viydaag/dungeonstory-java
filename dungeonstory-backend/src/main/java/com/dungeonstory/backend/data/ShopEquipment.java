package com.dungeonstory.backend.data;

import java.io.Serializable;

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
public class ShopEquipment implements Serializable {

	private static final long serialVersionUID = 6542587794911854312L;

	@Id
    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "unitPrice")
    private int unitPrice;
    
    public ShopEquipment() {
        super();
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

}
