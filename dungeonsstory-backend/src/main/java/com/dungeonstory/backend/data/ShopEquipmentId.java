package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ShopEquipmentId implements Serializable {
    
    private static final long serialVersionUID = -2402263208904335944L;

    private Long shopId;

    private Long equipmentId;

    public ShopEquipmentId() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipmentId == null) ? 0 : equipmentId.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
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
        if (equipmentId == null) {
            if (other.equipmentId != null) {
                return false;
            }
        } else if (!equipmentId.equals(other.equipmentId)) {
            return false;
        }
        if (shopId == null) {
            if (other.shopId != null) {
                return false;
            }
        } else if (!shopId.equals(other.shopId)) {
            return false;
        }
        return true;
    }

}
