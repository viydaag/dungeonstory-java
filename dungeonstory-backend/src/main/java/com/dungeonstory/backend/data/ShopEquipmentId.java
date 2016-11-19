package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ShopEquipmentId implements Serializable {
    
    private static final long serialVersionUID = -2402263208904335944L;

    private Long shop;

    private Long equipment;

    public ShopEquipmentId() {
        super();
    }

    public Long getShop() {
        return shop;
    }

    public void setShop(Long shopId) {
        this.shop = shopId;
    }

    public Long getEquipment() {
        return equipment;
    }

    public void setEquipment(Long equipmentId) {
        this.equipment = equipmentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
        result = prime * result + ((shop == null) ? 0 : shop.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ShopEquipmentId)) {
            return false;
        }
        ShopEquipmentId other = (ShopEquipmentId) obj;
        if (equipment == null) {
            if (other.equipment != null) {
                return false;
            }
        } else if (!equipment.equals(other.equipment)) {
            return false;
        }
        if (shop == null) {
            if (other.shop != null) {
                return false;
            }
        } else if (!shop.equals(other.shop)) {
            return false;
        }
        return true;
    }

}
