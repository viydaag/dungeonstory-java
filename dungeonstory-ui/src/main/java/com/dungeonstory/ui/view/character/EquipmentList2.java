package com.dungeonstory.ui.view.character;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Ring;
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
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.dnd.DropTargetExtension;

public class EquipmentList2
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

    private Grid<CharacterEquipment> equipmentGrid;

    public enum Hand {
        MAIN, SECONDARY
    }

    public EquipmentList2(Character character) {

        this.character = character;

        FHorizontalLayout hLayout = new FHorizontalLayout().withFullWidth();
        VerticalLayout layout = new VerticalLayout();

        acLabel = new DSLabel().withCaption("CA");
        attackLabel = new DSLabel().withCaption("Attaque");
        HorizontalLayout equipmentStatLayout = new HorizontalLayout(acLabel, attackLabel);

        equipmentGrid = new Grid<>("Équipement",
                character.getEquipment()
                         .stream()
                         .collect(Collectors.toList()));
        equipmentGrid.addColumn(e -> e.getEquipment().getName());
        equipmentGrid.addColumn(e -> e.getQuantity());

        createDragSource(equipmentGrid);

        layout.addComponents(equipmentStatLayout, equipmentGrid);

        VerticalLayout equipedLayout = new VerticalLayout();

        VerticalLayout equipedArmor = new VerticalLayout();

        // make the layout accept drops
        DropTargetExtension<VerticalLayout> dropTarget = new DropTargetExtension<>(equipedArmor);

        // the drop effect must match the allowed effect in the drag source for a successful drop
        dropTarget.setDropEffect(DropEffect.MOVE);

        // Cards can be dropped onto others with smaller value
        //        dropTarget.setDropCriterion("equipmentType", EquipmentType.HELMET.toString());

        // catch the drops
        dropTarget.addDropListener(event -> {
            // if the drag source is in the same UI as the target
            Optional<AbstractComponent> theDragSource = event.getDragSourceComponent();
            if (theDragSource.isPresent()) {
                // move the label to the layout
                event.getDragData()
                     .ifPresent(data -> handleWearing(equipedArmor, EquipmentType.ARMOR,
                             ((List<CharacterEquipment>) data).get(0)));

                // handle possible server side drag data, if the drag source was in the same UI
                //                event.getDragData().ifPresent(data -> handleMyDragData((MyObject) data));
            }
        });

        Panel armorPanel = new Panel("Armure", equipedArmor);
        equipedLayout.addComponent(armorPanel);

        hLayout.addComponents(layout, equipedLayout);
        setCompositionRoot(hLayout);

    }

    private void handleWearing(VerticalLayout layout, EquipmentType type, CharacterEquipment data) {
        if (type == data.getEquipment().getType()) {
            layout.addComponent(new Label(data.getEquipment().getName()));

            //substract 1 from available equipment
            Collection<CharacterEquipment> currentEquipment = ((ListDataProvider<CharacterEquipment>) equipmentGrid.getDataProvider()).getItems();
            currentEquipment.stream().filter(ce -> ce.equals(data)).findFirst().ifPresent(item -> {
                item.substractQuantity(1);
                if (item.getQuantity() <= 0) {
                    currentEquipment.remove(item);
                }
            });

            equipmentGrid.setItems(currentEquipment);
        }
    }

    private void createDragSource(Grid<?> source) {
        GridDragSource<?> dragSource = new GridDragSource(source);

        // set allowed effects
        dragSource.setEffectAllowed(EffectAllowed.MOVE);

        dragSource.addGridDragStartListener(event -> dragSource.setDragData(event.getDraggedItems()));
        dragSource.addGridDragEndListener(event -> dragSource.setDragData(null));
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
    private void manageOneHandWeapon(Weapon weapon, Hand hand) {
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
                canUseSameRing = character.getEquipment().stream().filter(e -> e.getEquipment().equals(ring)).anyMatch(
                        e -> e.getQuantity() > 1);
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
