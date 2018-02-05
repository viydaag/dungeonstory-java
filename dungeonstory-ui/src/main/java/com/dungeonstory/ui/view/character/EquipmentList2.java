package com.dungeonstory.ui.view.character;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.CharacterEquipment.EquipedType;
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
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSLabel;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import com.vaadin.ui.dnd.DragSourceExtension;
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

    private List<CharacterEquipment> equipmentList;

    private CharacterEquipmentLayout equipedMainWeapon;
    private CharacterEquipmentLayout equipedSecondaryWeapon;
    private CharacterEquipmentLayout equipedTwoHandWeapon;

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

        equipmentList = character.getEquipment().stream().filter(equip -> !equip.isEquiped()).collect(Collectors.toList());
        equipmentGrid = new Grid<CharacterEquipment>("Équipement", equipmentList);
        equipmentGrid.addColumn(e -> e.getEquipment().getName());
        equipmentGrid.setWidth("100%");

        createDragSource(equipmentGrid);
        createDropSource(equipmentGrid);

        layout.addComponents(equipmentStatLayout, equipmentGrid);

        VerticalLayout equipedLayout = new VerticalLayout();

        //Armor
        CharacterEquipmentLayout equipedArmor = new CharacterEquipmentLayout(EquipedType.ARMOR, new VerticalLayout(),
                isArmor());
        character.getEquipment()
                 .stream()
                 .filter(eq -> eq.isEquiped() && eq.getType() == EquipedType.ARMOR)
                 .findFirst()
                 .ifPresent(eq -> equipedArmor.setEquipment(eq));

        createDropSource(equipedArmor);

        Panel armorPanel = new Panel("Armure", equipedArmor);
        equipedLayout.addComponent(armorPanel);

        //Weapons
        equipedMainWeapon = new CharacterEquipmentLayout(EquipedType.MAIN_WEAPON, new VerticalLayout(),
                isOneHandMainWeapon());
        equipedSecondaryWeapon = new CharacterEquipmentLayout(EquipedType.SECONDARY_WEAPON, new VerticalLayout(),
                isOneHandSecondaryWeapon());
        equipedTwoHandWeapon = new CharacterEquipmentLayout(EquipedType.TWO_HAND_WEAPON, new VerticalLayout(),
                isTwoHandWeapon());
        VerticalLayout equipedWeapons = new VerticalLayout(equipedMainWeapon, equipedSecondaryWeapon,
                equipedTwoHandWeapon);
        character.getEquipment()
                 .stream()
                 .filter(eq -> eq.isEquiped() && eq.getType() == EquipedType.MAIN_WEAPON)
                 .findFirst()
                 .ifPresent(eq -> equipedMainWeapon.setEquipment(eq));
        character.getEquipment()
                 .stream()
                 .filter(eq -> eq.isEquiped() && eq.getType() == EquipedType.SECONDARY_WEAPON)
                 .findFirst()
                 .ifPresent(eq -> equipedSecondaryWeapon.setEquipment(eq));
        character.getEquipment()
                 .stream()
                 .filter(eq -> eq.isEquiped() && eq.getType() == EquipedType.TWO_HAND_WEAPON)
                 .findFirst()
                 .ifPresent(eq -> equipedTwoHandWeapon.setEquipment(eq));

        createDropSource(equipedMainWeapon);
        createDropSource(equipedSecondaryWeapon);
        createDropSource(equipedTwoHandWeapon);

        Panel weaponPanel = new Panel("Armes", equipedWeapons);
        equipedLayout.addComponent(weaponPanel);

        hLayout.addComponents(layout, equipedLayout);
        setCompositionRoot(hLayout);

    }

    private void createDropSource(Grid<CharacterEquipment> grid) {
        GridDropTarget<CharacterEquipment> dropTarget = new GridDropTarget<>(grid, DropMode.BETWEEN);
        dropTarget.setDropEffect(DropEffect.MOVE);

        // do not show drop target between rows when grid has been sorted
        //        dropTarget.setDropAllowedOnSortedGridRows(false);

        dropTarget.addGridDropListener(event -> {
            // Accepting dragged items from another Grid in the same UI
            event.getDragSourceComponent().ifPresent(source -> {
                if (source instanceof Label) {
                    Label equipLabel = (Label) source;
                    CharacterEquipment data = (CharacterEquipment) equipLabel.getData();

                    //unequip the item
                    character.getEquipment().stream().filter(eq -> eq.equals(data)).findFirst().ifPresent(eq -> eq.setType(null));

                    // Calculate the target row's index
                    //                    int index = equipmentList.size();
                    //                    if (event.getDropTargetRow().isPresent()) {
                    //                        index = equipmentList.indexOf(event.getDropTargetRow().get()) + (
                    //                            event.getDropLocation() == DropLocation.BELOW ? 1 : 0);
                    //                    }

                    //remove label from its source layout
                    CharacterEquipmentLayout container = equipLabel.findAncestor(CharacterEquipmentLayout.class);
                    if (container != null) {
                        container.removeEquipment();
                    }

                    // Add dragged items to the target Grid
                    Services.getCharacterService().update(character);
                    refreshEquipmentGrid();

                    // Show the dropped data
                    Notification.show("Dropped row data: " + event.getDataTransferText());
                }
            });
        });
    }

    private void createDropSource(CharacterEquipmentLayout dropSource) {
        // make the layout accept drops
        DropTargetExtension<CharacterEquipmentLayout> dropTarget = new DropTargetExtension<>(dropSource);

        // the drop effect must match the allowed effect in the drag source for a successful drop
        dropTarget.setDropEffect(DropEffect.MOVE);

        // Cards can be dropped onto others with smaller value
        //        dropTarget.setDropCriterion("equipmentType", EquipmentType.HELMET.toString());

        // catch the drops
        dropTarget.addDropListener(event -> {
            // if the drag source is in the same UI as the target
            event.getDragSourceExtension().ifPresent(source -> {
                if (source instanceof GridDragSource) {
                    // move the grid row to a new label to the layout
                    event.getDragData()
                         .ifPresent(
                                 data -> handleEquipmentChanged(dropSource, ((List<CharacterEquipment>) data).get(0)));
                }
                else {
                    //from label -> exchange equipment
                    event.getDragSourceComponent().ifPresent(dragSourceComponent -> {
                        if (dragSourceComponent instanceof Label) {
                        
                            Label equipLabel = (Label) dragSourceComponent;
                            CharacterEquipment newEquipment = (CharacterEquipment) equipLabel.getData();
                            CharacterEquipment oldEquipment = dropSource.getEquipment();

                            CharacterEquipmentLayout oldContainer = equipLabel.findAncestor(
                                    CharacterEquipmentLayout.class);

                            if ((oldContainer.getAcceptCriteria() == null
                                    || oldContainer.getAcceptCriteria().test(oldEquipment))
                                    && (dropSource.getAcceptCriteria() == null
                                            || dropSource.getAcceptCriteria().test(newEquipment))) {

                                if (oldContainer != null) {
                                    oldContainer.setEquipment(oldEquipment);
                                }

                                dropSource.setEquipment(newEquipment);
                            }

                            Services.getCharacterService().update(character);
                        }
                    });
                }

                // handle possible server side drag data, if the drag source was in the same UI
                //                event.getDragData().ifPresent(data -> handleMyDragData((MyObject) data));
            });
            
        });
    }

    private void handleEquipmentChanged(CharacterEquipmentLayout layout, CharacterEquipment data) {
        if (layout.getAcceptCriteria() == null || layout.getAcceptCriteria().test(data)) {
            
            if (layout.isEquiped()) {
                CharacterEquipment oldEquip = layout.getEquipment();
                character.getEquipment().stream().filter(eq -> eq.equals(oldEquip)).findFirst().ifPresent(eq -> eq.setType(null));
            }

            layout.setEquipment(data);

            //            Label equipmentLabel = new Label(data.getEquipment().getName());
            //            equipmentLabel.setData(data);
            //            if (layout.getType().getName() != null) {
            //                equipmentLabel.setCaption(layout.getType().getName());
            //            }
            //
            //            if (limit == 1) {
            //
            //                if (layout.getComponentCount() > 0) {
            //                    //replace equiped equipement
            //                    Label old = (Label) layout.getComponent(0);
            //                    CharacterEquipment oldEquip = (CharacterEquipment) old.getData();
            //                    layout.replaceComponent(old, equipmentLabel);
            //                    oldEquip.setType(null); //unequiped
            //                    character.getEquipment().stream().filter(eq -> eq.equals(oldEquip)).findFirst().ifPresent(
            //                            eq -> eq.setType(null));
            //                } else {
            //                    layout.addComponent(equipmentLabel);
            //                }
            //
            //                data.setType(layout.getType());
            //                createDragSource(equipmentLabel);
            //            }

            if (data.getEquipment().getType() == EquipmentType.ARMOR) {
                armorChanged((Armor) data.getEquipment());
            }

            character.getEquipment().stream().filter(eq -> eq.equals(data)).findFirst().ifPresent(
                    eq -> eq.setType(layout.getType()));
            Services.getCharacterService().update(character);
            refreshEquipmentGrid();
        }
    }

    private void refreshEquipmentGrid() {
        equipmentList.clear();
        equipmentList.addAll(character.getEquipment()
                                      .stream()
                                      .filter(equip -> !equip.isEquiped())
                                      .sorted((e1, e2) -> e1.getEquipment().getName().compareTo(e2.getEquipment().getName()))
                                      .collect(Collectors.toList()));
        equipmentGrid.getDataProvider().refreshAll();
    }

    private void createDragSource(Grid<?> source) {
        GridDragSource<?> dragSource = new GridDragSource<>(source);

        // set allowed effects
        dragSource.setEffectAllowed(EffectAllowed.MOVE);

        dragSource.addGridDragStartListener(event -> dragSource.setDragData(event.getDraggedItems()));
        dragSource.addGridDragEndListener(event -> dragSource.setDragData(null));
    }
    
    private void createDragSource(Label label) {
        DragSourceExtension<Label> dragSource = new DragSourceExtension<>(label);

        // set allowed effects
        dragSource.setEffectAllowed(EffectAllowed.MOVE);

        dragSource.addDragStartListener(event -> dragSource.setDragData(label.getData()));
        dragSource.addDragEndListener(event -> dragSource.setDragData(null));
    }

    private void shieldChanged(Armor value) {
        equipedSecondaryWeapon.removeEquipment();
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
        //        if (weapon != null) {
        //            canUseSameWeapon = character.getEquipment().stream().filter(e -> e.getEquipment().equals(weapon)).anyMatch(
        //                    e -> e.getQuantity() > 1);
        //        }

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
            //            if (ring != null) {
            //                canUseSameRing = character.getEquipment().stream().filter(e -> e.getEquipment().equals(ring)).anyMatch(
            //                        e -> e.getQuantity() > 1);
            //            }

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
    
    public static Predicate<CharacterEquipment> isOneHandMainWeapon() {
        return ce -> {
            if (ce.getEquipment().getType() == EquipmentType.WEAPON) {
                Weapon w = (Weapon) ce.getEquipment();
                return w.getWeaponType().getHandleType() == HandleType.ONE_HANDED
                        || w.getWeaponType().getHandleType() == HandleType.VERSATILE;
            }
            return false;
        };
    }

    public static Predicate<CharacterEquipment> isArmor() {
        return ce -> ce.getEquipment().getType() == EquipmentType.ARMOR;
    }

    public static Predicate<CharacterEquipment> isOneHandSecondaryWeapon() {
        return ce -> {
            //TODO handle null ce
            if (ce.getEquipment().getType() == EquipmentType.WEAPON) {
                Weapon w = (Weapon) ce.getEquipment();
                return w.getWeaponType().getSizeType() == SizeType.LIGHT //TODO : unless a special feat lets other weapon
                        && (w.getWeaponType().getHandleType() == HandleType.ONE_HANDED
                                || w.getWeaponType().getHandleType() == HandleType.VERSATILE);
            }
            return false;
        };
    }

    public static Predicate<CharacterEquipment> isTwoHandWeapon() {
        return ce -> {
            if (ce.getEquipment().getType() == EquipmentType.WEAPON) {
                Weapon w = (Weapon) ce.getEquipment();
                return w.getWeaponType().getHandleType() == HandleType.TWO_HANDED;
            }
            return false;
        };
    }

    class CharacterEquipmentLayout
            extends CustomComponent {
        
        private static final long serialVersionUID = -6167944474147894309L;
        private Layout             layout;
        private EquipedType type;
        private CharacterEquipment equipment;
        private Label              equipmentLabel;
        private Predicate<CharacterEquipment> acceptCriteria;

        public CharacterEquipmentLayout(EquipedType type, Layout layout, Predicate<CharacterEquipment> acceptCriteria) {
            this.type = type;
            this.layout = layout;
            this.acceptCriteria = acceptCriteria;
            this.equipmentLabel = new Label();
            if (type.getName() != null) {
                this.equipmentLabel.setCaption(type.getName());
            }
            createDragSource(equipmentLabel);
            this.layout.addComponent(equipmentLabel);
            setCompositionRoot(layout);
        }

        public EquipedType getType() {
            return type;
        }

        public CharacterEquipment getEquipment() {
            return equipment;
        }

        public void setEquipment(CharacterEquipment data) {

            equipmentLabel.setValue(data.getEquipment().getName());
            equipmentLabel.setData(data);
            if (type.getName() != null) {
                equipmentLabel.setCaption(type.getName());
            }

            this.equipment = data;
            this.equipment.setType(getType());
        }
        
        public void removeEquipment() {
            equipmentLabel.setValue("");
            equipment = null;
        }

        public boolean isEquiped() {
            return this.getEquipment() != null;
        }

        public Predicate<CharacterEquipment> getAcceptCriteria() {
            return acceptCriteria;
        }
    }

}
