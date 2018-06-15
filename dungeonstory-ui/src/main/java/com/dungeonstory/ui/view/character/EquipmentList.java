package com.dungeonstory.ui.view.character;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Amulet;
import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.Belt;
import com.dungeonstory.backend.data.Boot;
import com.dungeonstory.backend.data.Bracer;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Helmet;
import com.dungeonstory.backend.data.Ring;
import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.data.enums.ArmorType;
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

    private ComboBox<Ring> ring1;

    private ComboBox<Ring> ring2;

    private ComboBox<Armor> shields;

    private ComboBox<Armor> armors;

    public enum Hand {
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

        ComboBox<Amulet> amulets = new ComboBox<>("Cou",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.AMULET)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Amulet) e)
                         .collect(Collectors.toList()));
        equipmentLayout.addComponent(amulets);

        armors = new ComboBox<>("Armure",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.ARMOR)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Armor) e)
                         .collect(Collectors.toList()));
        armors.addValueChangeListener(event -> armorChanged(event.getValue()));
        equipmentLayout.addComponent(armors);

        shields = new ComboBox<>("Bouclier",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.ARMOR)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Armor) e)
                         .filter(a -> a.getArmorType().getProficiencyType() == ArmorType.ProficiencyType.SHIELD)
                         .collect(Collectors.toList()));
        shields.addValueChangeListener(event -> shieldChanged(event.getValue()));
        equipmentLayout.addComponent(shields);

        mainWeapons = new ComboBox<>("Arme principale",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.WEAPON)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Weapon) e)
                         .collect(Collectors.toList()));
        mainWeapons.addValueChangeListener(event -> weaponChanged(event.getValue(), Hand.MAIN, event.isUserOriginated()));
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
                event -> weaponChanged(event.getValue(), Hand.SECONDARY, event.isUserOriginated()));
        equipmentLayout.addComponent(secondaryWeapons);

        handleType = new ComboBox<>("Usage d'arme");
        handleType.setItems(HandleType.ONE_HANDED, HandleType.TWO_HANDED);
        handleType.setEmptySelectionAllowed(false);
        handleType.addValueChangeListener(this::manageVersatileWeapon);
        equipmentLayout.addComponent(handleType);

        ComboBox<Bracer> bracers = new ComboBox<>("Gantelet",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.BRACER)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Bracer) e)
                         .collect(Collectors.toList()));
        equipmentLayout.addComponent(bracers);

        ring1 = new ComboBox<>("Anneau main droite",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.RING)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Ring) e)
                         .collect(Collectors.toList()));
        ring1.addValueChangeListener(event -> ringChanged(event.getValue(), Hand.MAIN, event.isUserOriginated()));
        equipmentLayout.addComponent(ring1);

        ring2 = new ComboBox<>("Anneau main gauche",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.RING)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Ring) e)
                         .collect(Collectors.toList()));
        ring2.addValueChangeListener(event -> ringChanged(event.getValue(), Hand.SECONDARY, event.isUserOriginated()));
        equipmentLayout.addComponent(ring2);

        ComboBox<Belt> belts = new ComboBox<>("Ceinturon",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.BELT)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Belt) e)
                         .collect(Collectors.toList()));
        equipmentLayout.addComponent(belts);

        ComboBox<Boot> boots = new ComboBox<>("Botte",
                character.getEquipment()
                         .stream()
                         .filter(e -> e.getEquipment().getType() == EquipmentType.BOOT)
                         .map(CharacterEquipment::getEquipment)
                         .map(e -> (Boot) e)
                         .collect(Collectors.toList()));
        equipmentLayout.addComponent(boots);

        layout.addComponents(equipmentStatLayout, equipmentLayout);
        setCompositionRoot(layout);

        armorChanged(armors.getValue());
    }

    private void shieldChanged(Armor value) {
        secondaryWeapons.setValue(null);
        weaponChanged(null, Hand.SECONDARY, true);
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

    private void weaponChanged(Weapon weapon, Hand hand, boolean userOriginated) {
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

            attackDice.addModifier(modifier);
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
    private void manageOneHandWeapon(Weapon weapon, Hand hand) {
        secondaryWeapons.setReadOnly(false);

        boolean canUseSameWeapon = true;
        if (weapon != null) {
//            canUseSameWeapon = character.getEquipment().stream().filter(e -> e.getEquipment().equals(weapon)).anyMatch(
//                    e -> e.getQuantity() > 1);
        }

        Stream<Weapon> availableWeaponStream = character.getEquipment()
                                                        .stream()
                                                        .filter(e -> e.getEquipment().getType() == EquipmentType.WEAPON)
                                                        .map(CharacterEquipment::getEquipment)
                                                        .map(e -> (Weapon) e);

        if (hand == Hand.MAIN) {

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

        } else if (hand == Hand.SECONDARY) {
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
     * Set both hands with same weapon and clear shield.
     * @param weapon : the weapon changed
     * @param hand : main or secondary hand on which the weapon was changed
     */
    private void manageTwoHandWeapon(Weapon weapon, Hand hand) {

        if (hand == Hand.MAIN) {
            secondaryWeapons.setValue(weapon);
            secondaryWeapons.setReadOnly(true);
        } else if (hand == Hand.SECONDARY) {
            mainWeapons.setValue(weapon);
            secondaryWeapons.setReadOnly(true);
        }
        shields.clear();
        shields.setReadOnly(true);
    }

    /**
     * Manage user choice (one or two hands) for versatile weapons.
     * @param event : value changed event for HandleType
     */
    private void manageVersatileWeapon(ValueChangeEvent<HandleType> event) {
        if (event.isUserOriginated()) {
            switch (event.getValue()) {
                case ONE_HANDED:
                    manageOneHandWeapon(mainWeapons.getValue(), Hand.MAIN);
                    break;
                case TWO_HANDED:
                    manageTwoHandWeapon(mainWeapons.getValue(), Hand.MAIN);
                case VERSATILE:
                default:
                    break;
            }
        }
    }

    private void ringChanged(Ring ring, Hand hand, boolean userOriginated) {
        if (userOriginated) {
            boolean canUseSameRing = true;
            if (ring != null) {
//                canUseSameRing = character.getEquipment().stream().filter(e -> e.getEquipment().equals(ring)).anyMatch(
//                        e -> e.getQuantity() > 1);
            }

            Stream<Ring> availableRingStream = character.getEquipment()
                                                        .stream()
                                                        .filter(e -> e.getEquipment().getType() == EquipmentType.RING)
                                                        .map(CharacterEquipment::getEquipment)
                                                        .map(e -> (Ring) e);

            if (hand == Hand.MAIN) {

                Ring backup = ring2.getValue();
                ring2.clear();
                if (canUseSameRing) {
                    ring2.setItems(availableRingStream.collect(Collectors.toList()));
                } else {
                    //reset items to remove the equipped ring from the other hand
                    ring2.setItems(availableRingStream.filter(r -> !r.equals(ring)).collect(Collectors.toList()));
                }
                ring2.setValue(backup);

            } else if (hand == Hand.SECONDARY) {

                Ring backup = ring1.getValue();
                ring1.clear();
                if (canUseSameRing) {
                    ring1.setItems(availableRingStream.collect(Collectors.toList()));
                } else {
                    //reset items to remove the equipped ring from the other hand
                    ring1.setItems(availableRingStream.filter(r -> !r.equals(ring)).collect(Collectors.toList()));
                }
                ring1.setValue(backup);
            }
        }
    }

}
