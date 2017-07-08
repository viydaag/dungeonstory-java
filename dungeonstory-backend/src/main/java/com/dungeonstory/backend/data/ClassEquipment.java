package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@IdClass(ClassEquipmentId.class)
@Table(name = "ClassEquipment")
public class ClassEquipment implements Serializable {

    private static final long serialVersionUID = -8514487793346228199L;

    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "classId")
    private DSClass classe;
    
    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;
    
    @Column(name = "quantity")
    private int quantity;
    
    public ClassEquipment() {
        super();
    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
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

}
