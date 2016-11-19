package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassEquipmentId implements Serializable {
    
    private static final long serialVersionUID = 6769677611311800806L;

    private Long classe;

    private Long equipment;

    public ClassEquipmentId() {
        super();
    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classe) {
        this.classe = classe;
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
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
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
        if (!(obj instanceof ClassEquipmentId)) {
            return false;
        }
        ClassEquipmentId other = (ClassEquipmentId) obj;
        if (equipment == null) {
            if (other.equipment != null) {
                return false;
            }
        } else if (!equipment.equals(other.equipment)) {
            return false;
        }
        if (classe == null) {
            if (other.classe != null) {
                return false;
            }
        } else if (!classe.equals(other.classe)) {
            return false;
        }
        return true;
    }

}
