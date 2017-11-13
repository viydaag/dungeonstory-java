package com.dungeonstory.backend.data;

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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Equipment")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Equipment extends AbstractTimestampEntity {

    private static final long serialVersionUID = 1146171037304994442L;

    public enum EquipmentType {
    	
        ARMOR(Armor.class, "Armure"),
        WEAPON(Weapon.class, "Arme"),
        RING(Ring.class, "Anneau"),
        AMULET(Amulet.class, "Amulette"),
        BRACER(Bracer.class, "Bracelet"),
        BOOT(Boot.class, "Botte"),
        BELT(Belt.class, "Ceinturon"),
        HELMET(Helmet.class, "Heaume"),
        AMMUNITION(Ammunition.class, "Munition"),
        FOCUS(Focus.class, "Focus de sort"),
        TOOL(Tool.class, "Outil"),
        GEAR(Gear.class, "Équipement"),
        COMPONENT(SpellComponent.class, "Composant");
    	
    	private Class<? extends Equipment> equipmentClass;
        private String                     name;
    	
        private EquipmentType(Class<? extends Equipment> equipmentClass, String name) {
    		this.equipmentClass = equipmentClass;
            this.name = name;
    	}

		public final Equipment getEquipment() throws InstantiationException, IllegalAccessException {
			return equipmentClass.newInstance();
		}

        public String getName() {
            return name;
        }
    }

    @NotNull
    @Size(min = 1)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @DecimalMin("0.0")
    @Column(name = "weight", nullable = false)
    private Double weight = 0.0;

    @Column(name = "isMagical", nullable = false)
    private boolean isMagical;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EquipmentType type;

    @Column(name = "isPurchasable", nullable = false)
    private boolean isPurchasable = true;

    @Column(name = "isSellable", nullable = false)
    private boolean isSellable = true;
    
    @Min(value = 1)
    @Digits(integer = 9, fraction = 0)
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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
