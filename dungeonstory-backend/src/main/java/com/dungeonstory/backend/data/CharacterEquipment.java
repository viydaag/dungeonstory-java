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

@Entity
@IdClass(CharacterEquipmentId.class)
@Table(name = "CharacterEquipment")
public class CharacterEquipment implements Serializable {

    private static final long serialVersionUID = 8452886623922049968L;

    @Id
    @ManyToOne
    @JoinColumn(name = "characterId")
    private Character character;

    @Id
    @ManyToOne
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;
    
    @Min(value = 1)
    @Column(name = "quantity")
    private int quantity;

    @Min(value = 0)
    @Column(name = "sellableValue")
    private int sellableValue;

    public CharacterEquipment() {
        super();
    }
    
    public CharacterEquipment(Character character, Equipment equipment, int quantity, int sellableValue) {
        super();
        this.character = character;
        this.equipment = equipment;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellableValue() {
        return sellableValue;
    }

    public void setSellableValue(int sellableValue) {
        this.sellableValue = sellableValue;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void substractQuantity(int quantity) {
        this.quantity -= quantity;
    }

}
