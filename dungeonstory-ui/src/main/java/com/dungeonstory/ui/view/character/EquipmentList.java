package com.dungeonstory.ui.view.character;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Helmet;
import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.rules.Dice;
import com.dungeonstory.ui.component.DSLabel;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class EquipmentList
        extends CustomComponent {

    private static final long serialVersionUID = 2446586900699091062L;

    private Character character;

    private DSLabel acLabel;
    private DSLabel attackLabel;

    private ComboBox<Weapon> mainWeapons;
    private ComboBox<Weapon> secondaryWeapons;

    private ComboBox<WeaponType.HandleType> handleType;

    public enum WeaponHand {
        MAIN, SECONDARY
    }

    public EquipmentList(Character character) {

        this.character = character;

        VerticalLayout layout = new VerticalLayout();

        acLabel = new DSLabel().withCaption("CA");
        attackLabel = new DSLabel().withCaption("Attaque");
        HorizontalLayout equipmentStatLayout = new HorizontalLayout(acLabel, attackLabel);

        FormLayout equipmentLayout = new FormLayout();

        ComboBox<Helmet> helmets = new ComboBox<>("Tête",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.HELMET)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Helmet) e)
                         .collect(Collectors.toList()));
        equipmentLayout.addComponent(helmets);

        ComboBox<Armor> armors = new ComboBox<>("Armure",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.ARMOR)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Armor) e)
                         .collect(Collectors.toList()));
        armors.addValueChangeListener(event -> armorChanged(event.getValue()));
        equipmentLayout.addComponent(armors);

        mainWeapons = new ComboBox<>("Arme principale",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.WEAPON)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Weapon) e)
                         .collect(Collectors.toList()));
        mainWeapons.addValueChangeListener(event -> weaponChanged(event.getValue(), WeaponHand.MAIN, event.isUserOriginated()));
        equipmentLayout.addComponent(mainWeapons);

        secondaryWeapons = new ComboBox<>("Arme secondaire (2e main)",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.WEAPON)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Weapon) e)
                         .filter(w -> w.getWeaponType().getSizeType() == SizeType.LIGHT)
                         .collect(Collectors.toList()));
        secondaryWeapons.addValueChangeListener(
                event -> weaponChanged(event.getValue(), WeaponHand.SECONDARY, event.isUserOriginated()));
        equipmentLayout.addComponent(secondaryWeapons);

        handleType = new ComboBox<>("Usage d'arme");
        handleType.setItems(HandleType.ONE_HANDED, HandleType.TWO_HANDED);
        handleType.setEmptySelectionAllowed(false);
        handleType.addValueChangeListener(this::manageVersatileWeapon);
        equipmentLayout.addComponent(handleType);

        layout.addComponents(equipmentStatLayout, equipmentLayout);
        setCompositionRoot(layout);

        armorChanged(armors.getValue());
    }

    private void armorChanged(Armor armor) {
        int ac = 10;
        if (armor == null) {
            //dex modifier
            ac += ModifierUtil.getAbilityModifier(character.getDexterity());
        } else {
            //armor
            ac += armor.getArmorClass();
            if (armor.getArmorType().getMaxDexBonus() != ArmorType.NO_MAX_DEX_BONUS) {
                ac += Math.min(ModifierUtil.getAbilityModifier(character.getDexterity()), armor.getArmorType().getMaxDexBonus());
            } else {
                ac += ModifierUtil.getAbilityModifier(character.getDexterity());
            }
        }
        acLabel.setValue(ac);
    }

    private void weaponChanged(Weapon weapon, WeaponHand hand, boolean userOriginated) {
        if (userOriginated) {

            //manage handle
            if (weapon == null || weapon.getWeaponType().getHandleType() == HandleType.ONE_HANDED) {
                handleType.setValue(HandleType.ONE_HANDED);
                handleType.setReadOnly(true);
                manageOneHandWeapon(weapon, hand);
            } else if (weapon.getWeaponType().getHandleType() == HandleType.TWO_HANDED) {
                handleType.setValue(HandleType.TWO_HANDED);
                handleType.setReadOnly(true);
                manageTwoHandWeapon(weapon, hand);
            } else if (weapon.getWeaponType().getHandleType() == HandleType.VERSATILE) {
                handleType.setReadOnly(false); //user will choose the value
                if (handleType.getValue() == handleType.getEmptyValue() || handleType.getValue() == HandleType.ONE_HANDED) {
                    handleType.setValue(HandleType.ONE_HANDED);
                    manageOneHandWeapon(weapon, hand);
                } else if (handleType.getValue() == HandleType.TWO_HANDED) {
                    manageTwoHandWeapon(weapon, hand);
                }
            }

            refreshAttackLabel();
        }
    }

    private void refreshAttackLabel() {

        Weapon weapon1 = mainWeapons.getValue();
        Weapon weapon2 = secondaryWeapons.getValue();
        if (weapon1 == null && weapon2 == null) {
            //no weapon
            attackLabel.setValue("1");
            //TODO : check if class has no weapon damage
        } else {
            //weapon
            Dice attackDice = null;
            Dice attackDice2 = null;

            //damage
            if (handleType.getValue() == HandleType.ONE_HANDED) {
                attackDice = new Dice(weapon1.getOneHandDamage());
                if (weapon2 != null) {
                    attackDice2 = new Dice(weapon2.getOneHandDamage());
                }
            } else {
                attackDice = new Dice(weapon1.getTwoHandDamage());
            }

            //modifier
            int modifier = 0;
            if (weapon1.getWeaponType().getUsageType() == UsageType.MELEE) {
                if (weapon1.getWeaponType().getIsFinesse()) {
                    modifier = Math.max(ModifierUtil.getPositiveAbilityModifier(character.getDexterity()),
                            ModifierUtil.getPositiveAbilityModifier(character.getStrength()));
                } else {
                    modifier = ModifierUtil.getPositiveAbilityModifier(character.getStrength());
                }
            } else if (weapon1.getWeaponType().getUsageType() == UsageType.RANGE) {
                if (weapon1.getWeaponType().getRangeType() == RangeType.AMMUNITION) {
                    modifier = ModifierUtil.getPositiveAbilityModifier(character.getDexterity());
                } else if (weapon1.getWeaponType().getRangeType() == RangeType.THROWN) {
                    modifier = ModifierUtil.getPositiveAbilityModifier(character.getStrength());
                }
            }

            attackDice.setExtra(modifier);
            String value = attackDice.toString();
            if (attackDice2 != null) {
                value += " / " + attackDice2.toString();
            }
            attackLabel.setValue(value);

        }
    }

    /**
     * Manage one-hand weapon change.
     * @param weapon : the weapon changed
     * @param hand : main or secondary hand on which the weapon was changed
     */
    private void manageOneHandWeapon(Weapon weapon, WeaponHand hand) {
        secondaryWeapons.setReadOnly(false);


        boolean canUseSameWeapon = true;
        if (weapon != null) {
            canUseSameWeapon = character.getEquipment().stream().filter(e -> e.getEquipment().equals(weapon)).anyMatch(
                    e -> e.getQuantity() > 1);
        }

        Stream<Weapon> availableWeaponStream = character.getEquipment()
                                                        .stream()
                                                        .filter(e -> e.getEquipment().getType() == EquipmentType.WEAPON)
                                                        .map(CharacterEquipment::getEquipment)
                                                        .map(e -> (Weapon) e);

        if (hand == WeaponHand.MAIN) {

            //if a two-handed weapon was selected and the right hand changes for one-handed weapon, clear the left hand and make it available
            if (secondaryWeapons.getValue() != null
                    && (secondaryWeapons.getValue().getWeaponType().getHandleType() == HandleType.TWO_HANDED
                            || secondaryWeapons.getValue().getWeaponType().getHandleType() == HandleType.VERSATILE)) {
                secondaryWeapons.setValue(secondaryWeapons.getEmptyValue());
            }

            Weapon backup = secondaryWeapons.getValue();
            secondaryWeapons.clear();
            Stream<Weapon> secondaryAvailableWeaponStream = availableWeaponStream.filter(
                    w -> w.getWeaponType().getSizeType() == SizeType.LIGHT);
            //TODO : unless a special feat lets other weapon
            if (canUseSameWeapon) {
                secondaryWeapons.setItems(secondaryAvailableWeaponStream.collect(Collectors.toList()));
            } else {
                //reset items to remove the equipped weapon from the other hand
                secondaryWeapons.setItems(
                        secondaryAvailableWeaponStream.filter(w -> !w.equals(weapon)).collect(Collectors.toList()));
            }

            //in case the main hand is cleared, clear also the other hand
            if (weapon != null) {
                secondaryWeapons.setValue(backup);
            }

        } else if (hand == WeaponHand.SECONDARY) {
            Weapon backup = mainWeapons.getValue();
            mainWeapons.clear();
            if (canUseSameWeapon) {
                mainWeapons.setItems(availableWeaponStream.collect(Collectors.toList()));
            } else {
                //reset items to remove the equipped weapon from the other hand
                mainWeapons.setItems(availableWeaponStream.filter(w -> !w.equals(weapon)).collect(Collectors.toList()));
            }
            mainWeapons.setValue(backup);
        }
    }

    /**
     * Manage two-hand weapon change.
     * @param weapon : the weapon changed
     * @param hand : main or secondary hand on which the weapon was changed
     */
    private void manageTwoHandWeapon(Weapon weapon, WeaponHand hand) {

        if (hand == WeaponHand.MAIN) {
            secondaryWeapons.setValue(weapon);
            secondaryWeapons.setReadOnly(true);
        } else if (hand == WeaponHand.SECONDARY) {
            mainWeapons.setValue(weapon);
            secondaryWeapons.setReadOnly(true);
        }
        //TODO remove shield as well
    }

    /**
     * Manage user choice (one or two hands) for versatile weapons.
     * @param event : value changed event for HandleType
     */
    private void manageVersatileWeapon(ValueChangeEvent<HandleType> event) {
        if (event.isUserOriginated()) {
            switch (event.getValue()) {
                case ONE_HANDED:
                    manageOneHandWeapon(mainWeapons.getValue(), WeaponHand.MAIN);
                    break;
                case TWO_HANDED:
                    manageTwoHandWeapon(mainWeapons.getValue(), WeaponHand.MAIN);
                case VERSATILE:
                default:
                    break;
            }
        }
    }

}
