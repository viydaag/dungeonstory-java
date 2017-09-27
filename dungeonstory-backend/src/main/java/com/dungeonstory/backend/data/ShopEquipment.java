package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ShopEquipmentId.class)
@Table(name = "ShopEquipment")
public class ShopEquipment implements Serializable {

	private static final long serialVersionUID = 6542587794911854312L;

	@Id
    @ManyToOne
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;
    
    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipmentId", nullable = false)
    private Equipment equipment;
    
    @Min(value = 1, message = "La quantité doit être au minimum 1")
    @Column(name = "quantity")
    private int quantity = 1;
    
    @Min(value = 0, message = "La valeur doit être positive")
    @Column(name = "unitPrice")
    private int unitPrice;
    
    public ShopEquipment() {
        super();
    }

    public ShopEquipment(Shop shop, Equipment equipment, int quantity, int unitPrice) {
        super();
        this.shop = shop;
        this.equipment = equipment;
        this.quantity = quantity;
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

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void substractQuantity(int quantity) {
        this.quantity -= quantity;
    }

}
