package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Equipment")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Equipment extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 1146171037304994442L;

    public enum EquipmentType {
    	
    	ARMOR(new Armor()), 
    	WEAPON(new Weapon()), 
    	RING(new Ring()), 
    	AMULET(new Amulet()), 
    	BRACER(new Bracer()), 
    	BOOT(new Boot()), 
    	BELT(new Belt()), 
    	TOOL(new Tool()),
    	GEAR(new Gear());
    	
    	private Equipment equipment;
    	
    	private EquipmentType(Equipment equipment) {
    		this.equipment = equipment;
    	}

		public final Equipment getEquipment() {
			return equipment;
		}
        
    }

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Min(value = 0)
    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "isMagical", nullable = false)
    private boolean isMagical;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EquipmentType type;

    @Column(name = "isPurchasable", nullable = false)
    private boolean isPurchasable;

    @Column(name = "isSellable", nullable = false)
    private boolean isSellable;
    
    @Min(value = 1)
    @Column(name = "basePrice", nullable = false)
    private int basePrice = 1;
    
    @OneToMany(mappedBy = "equipment")
    private List<ShopEquipment> shopEquipments;

    public Equipment() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isMagical() {
        return isMagical;
    }

    public void setMagical(boolean isMagical) {
        this.isMagical = isMagical;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public boolean getIsPurchasable() {
        return isPurchasable;
    }

    public void setIsPurchasable(boolean isPurchasable) {
        this.isPurchasable = isPurchasable;
    }

    public boolean getIsSellable() {
        return isSellable;
    }

    public void setIsSellable(boolean isSellable) {
        this.isSellable = isSellable;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public List<ShopEquipment> getShopEquipments() {
        return shopEquipments;
    }

    public void setShopEquipments(List<ShopEquipment> shopEquipments) {
        this.shopEquipments = shopEquipments;
    }

    @Override
    public String toString() {
        return getName();
    }
}
