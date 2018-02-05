package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
//@IdClass(CharacterEquipmentId.class)
@Table(name = "CharacterEquipment")
public class CharacterEquipment
        extends AbstractTimestampEntity {

    private static final long serialVersionUID = 8452886623922049968L;

    public enum EquipedType {

        ARMOR(Armor.class, null),
        MAIN_WEAPON(Weapon.class, "Arme principale"),
        SECONDARY_WEAPON(Weapon.class, "Arme secondaire"),
        TWO_HAND_WEAPON(Weapon.class, "Arme à 2 mains"),
        RING_1(Ring.class, "Doigt 1"),
        RING_2(Ring.class, "Doigt 2"),
        AMULET(Amulet.class, null),
        BRACER(Bracer.class, null),
        BOOT(Boot.class, null),
        BELT(Belt.class, null),
        HELMET(Helmet.class, null),
        AMMUNITION(Ammunition.class, null);

        private Class<? extends Equipment> equipmentClass;
        private String                     name;

        private EquipedType(Class<? extends Equipment> equipmentClass, String name) {
            this.equipmentClass = equipmentClass;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    //    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "characterId")
    private Character character;

    //    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true)
    private EquipedType type;

    //    @Min(value = 1)
    //    @Digits(integer = 3, fraction = 0)
    //    @Column(name = "quantity")
    //    private int quantity;

    @Min(value = 0)
    @Digits(integer = 9, fraction = 0)
    @Column(name = "sellableValue")
    private int sellableValue;

    public CharacterEquipment() {
        super();
    }
    
    public CharacterEquipment(Character character, Equipment equipment, int quantity, int sellableValue) {
        super();
        this.character = character;
        this.equipment = equipment;
        //        this.quantity = quantity;
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

    //    public int getQuantity() {
    //        return quantity;
    //    }
    //
    //    public void setQuantity(int quantity) {
    //        this.quantity = quantity;
    //    }

    public boolean isEquiped() {
        return this.type != null;
    }

    public EquipedType getType() {
        return type;
    }

    public void setType(EquipedType type) {
        this.type = type;
    }

    public int getSellableValue() {
        return sellableValue;
    }

    public void setSellableValue(int sellableValue) {
        this.sellableValue = sellableValue;
    }

    //    public void addQuantity(int quantity) {
    //        this.quantity += quantity;
    //    }
    //
    //    public void substractQuantity(int quantity) {
    //        this.quantity -= quantity;
    //    }

}
