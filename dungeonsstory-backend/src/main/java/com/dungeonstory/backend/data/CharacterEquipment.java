package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@IdClass(CharacterEquipmentId.class)
@Table(name = "CharacterEquipment")
public class CharacterEquipment implements Serializable {

    private static final long serialVersionUID = 8452886623922049968L;

    @Id
    @Column(name = "characterId")
    private Long characterId;

    @Id
    @Column(name = "equipmentId")
    private Long equipmentId;

    @Min(value = 1)
    @Column(name = "quantity")
    private int quantity;

    @Min(value = 0)
    @Column(name = "sellableValue")
    private BigDecimal sellableValue;

    @ManyToOne
    @JoinColumn(name = "characterId", updatable = false, insertable = false)
    private Character character;

    @ManyToOne
    @JoinColumn(name = "equipmentId", updatable = false, insertable = false)
    private Equipment equipment;

    public CharacterEquipment() {
        super();
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
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

    public BigDecimal getSellableValue() {
        return sellableValue;
    }

    public void setSellableValue(BigDecimal sellableValue) {
        this.sellableValue = sellableValue;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

}
