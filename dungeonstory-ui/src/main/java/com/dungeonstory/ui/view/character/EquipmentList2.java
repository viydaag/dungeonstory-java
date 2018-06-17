package com.dungeonstory.ui.view.character;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.CharacterEquipment.EquipedType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.data.enums.ArmorType;
import com.dungeonstory.backend.data.enums.ArmorType.ProficiencyType;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.rules.Dice;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.GridContextClickEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import com.vaadin.ui.dnd.DragSourceExtension;
import com.vaadin.ui.dnd.DropTargetExtension;

public class EquipmentList2 extends CustomComponent implements HasCharacter {

    private static final long serialVersionUID = 2446586900699091062L;

    private Character character;

    private DSLabel acLabel;
    private DSLabel attackLabel;

    private Grid<CharacterEquipment> equipmentGrid;
    private List<CharacterEquipment> equipmentList;

    private CharacterEquipmentLayout equipedMainWeapon;
    private CharacterEquipmentLayout equipedSecondaryWeapon;
    private CharacterEquipmentLayout equipedTwoHandWeapon;

    private CharacterEquipmentLayout equipedHelmet;
    private CharacterEquipmentLayout equipedArmor;
    private CharacterEquipmentLayout equipedShield;

    private CharacterEquipmentLayout equipedBracer;
    private CharacterEquipmentLayout equipedBelt;
    private CharacterEquipmentLayout equipedBoot;

    public enum Hand {
        MAIN, SECONDARY, TWO
    }

    public EquipmentList2(Character character) {

        this.character = character;

        FHorizontalLayout hLayout = new FHorizontalLayout().withFullWidth();
        VerticalLayout layout = new VerticalLayout();

        acLabel = new DSLabel().withCaption("CA");
        attackLabel = new DSLabel().withCaption("Attaque");
        HorizontalLayout equipmentStatLayout = new HorizontalLayout(acLabel, attackLabel);

        equipmentList = character.getEquipment().stream().filter(equip -> !equip.isEquiped()).collect(
                Collectors.toList());
        equipmentGrid = new Grid<CharacterEquipment>("Équipement", equipmentList);
        equipmentGrid.setSelectionMode(SelectionMode.NONE);
        equipmentGrid.addColumn(e -> e.getEquipment().getName());
        equipmentGrid.setWidth("100%");
        equipmentGrid.addContextClickListener(event -> {
            if (event != null && event.getButton() == MouseButton.RIGHT) {
                CharacterEquipment item = ((GridContextClickEvent<CharacterEquipment>) event).getItem();
                if (item != null) {
                    showEquipmentWindow(item.getEquipment());
                }
            }
        });

        createDragSource(equipmentGrid);
        createDropSource(equipmentGrid);

        layout.addComponents(equipmentStatLayout, equipmentGrid);

        VerticalLayout equipedLayout = new VerticalLayout();

        //Armor
        equipedArmor = createDragAndDropEquimentSlot(EquipedType.ARMOR, isArmor());

        //Helmet
        equipedHelmet = createDragAndDropEquimentSlot(EquipedType.HELMET, isHelmet());

        //Shield
        equipedShield = createDragAndDropEquimentSlot(EquipedType.SHIELD, isShield());

        VerticalLayout equipedArmors = new VerticalLayout(equipedHelmet, equipedArmor, equipedShield);
        equipedArmors.setMargin(false);

        Panel armorPanel = new Panel("Armure", equipedArmors);
        equipedLayout.addComponent(armorPanel);

        //Weapons
        equipedMainWeapon = createDragAndDropEquimentSlot(EquipedType.MAIN_WEAPON, isOneHandMainWeapon());
        equipedSecondaryWeapon = createDragAndDropEquimentSlot(EquipedType.SECONDARY_WEAPON, isOneHandSecondaryWeapon());
        equipedTwoHandWeapon = createDragAndDropEquimentSlot(EquipedType.TWO_HAND_WEAPON, isTwoHandWeapon());

        VerticalLayout equipedWeapons = new VerticalLayout(equipedMainWeapon, equipedSecondaryWeapon,
                equipedTwoHandWeapon);
        equipedWeapons.setMargin(false);

        Panel weaponPanel = new Panel("Armes", equipedWeapons);
        equipedLayout.addComponent(weaponPanel);

        //Bracer
        equipedBracer = createDragAndDropEquimentSlot(EquipedType.BRACER, isBracer());
        Panel bracerPanel = new Panel("Gants/Bracelet", equipedBracer);
        equipedLayout.addComponent(bracerPanel);
        
        //Jewels
        CharacterEquipmentLayout equipedAmulet = createDragAndDropEquimentSlot(EquipedType.AMULET, isAmulet());
        CharacterEquipmentLayout equipedRing1 = createDragAndDropEquimentSlot(EquipedType.RING_1, isRing());
        CharacterEquipmentLayout equipedRing2 = createDragAndDropEquimentSlot(EquipedType.RING_2, isRing());

        VerticalLayout equipedJewels = new VerticalLayout(equipedAmulet, equipedRing1, equipedRing2);
        equipedJewels.setMargin(false);

        Panel jewelPanel = new Panel("Bijoux", equipedJewels);
        equipedLayout.addComponent(jewelPanel);
        
        //Belt
        equipedBelt = createDragAndDropEquimentSlot(EquipedType.BELT, isBelt());
        Panel beltPanel = new Panel("Ceinturon", equipedBelt);
        equipedLayout.addComponent(beltPanel);

        //Boots
        equipedBoot = createDragAndDropEquimentSlot(EquipedType.BOOT, isBoot());
        Panel bootPanel = new Panel("Botte", equipedBoot);
        equipedLayout.addComponent(bootPanel);

        hLayout.addComponents(layout, equipedLayout);
        setCompositionRoot(hLayout);

        refreshAC();
        refreshAttackLabel();

    }

    private CharacterEquipmentLayout createDragAndDropEquimentSlot(EquipedType equipedType, Predicate<CharacterEquipment> test) {
        Supplier<CharacterEquipment> supplier = () -> this.character.getEquipment()
                .stream()
                .filter(eq -> eq.isEquiped() && eq.getType() == equipedType)
                .findFirst()
                .orElse(null);
        CharacterEquipmentLayout slot = new CharacterEquipmentLayout(equipedType, new VerticalLayout(), test, supplier);
        slot.refresh();
        createDropSource(slot);
        return slot;
    }

    private void createDropSource(Grid<CharacterEquipment> grid) {
        GridDropTarget<CharacterEquipment> dropTarget = new GridDropTarget<>(grid, DropMode.BETWEEN);
        dropTarget.setDropEffect(DropEffect.MOVE);

        dropTarget.addGridDropListener(event -> {
            // Accepting dragged items from another Grid in the same UI
            event.getDragSourceComponent().ifPresent(source -> {
                if (source instanceof Label) {
                    Label equipLabel = (Label) source;
                    CharacterEquipment data = (CharacterEquipment) equipLabel.getData();

                    //unequip the item
                    character.getEquipment().stream().filter(eq -> eq.equals(data)).findFirst().ifPresent(
                            eq -> eq.setType(null));

                    // Add dragged items to the target Grid
                    updateCharacter();
                    refreshEquipmentGrid();

                    //remove label from its source layout
                    CharacterEquipmentLayout container = equipLabel.findAncestor(CharacterEquipmentLayout.class);
                    if (container != null) {
                        container.refresh();
                    }

                    removeSpecific(data.getEquipment());
                }
            });
        });
    }

    private void createDropSource(CharacterEquipmentLayout dropSource) {
        // make the layout accept drops
        DropTargetExtension<CharacterEquipmentLayout> dropTarget = new DropTargetExtension<>(dropSource);

        // the drop effect must match the allowed effect in the drag source for a successful drop
        dropTarget.setDropEffect(DropEffect.MOVE);

        // catch the drops
        dropTarget.addDropListener(event -> {
            // if the drag source is in the same UI as the target
            event.getDragSourceExtension().ifPresent(source -> {
                if (source instanceof GridDragSource) {
                    // move the grid row to a new label to the layout
                    event.getDragData().ifPresent(
                            data -> handleEquipmentChanged(dropSource, ((List<CharacterEquipment>) data).get(0)));
                } else {
                    //from label -> exchange equipment
                    event.getDragSourceComponent().ifPresent(dragSourceComponent -> {
                        if (dragSourceComponent instanceof Label) {

                            Label equipLabel = (Label) dragSourceComponent;
                            CharacterEquipment newEquipment = (CharacterEquipment) equipLabel.getData();
                            CharacterEquipment oldEquipment = dropSource.getEquipment();

                            CharacterEquipmentLayout oldContainer = equipLabel
                                    .findAncestor(CharacterEquipmentLayout.class);

                            boolean equipHasChanged = false;

                            if (!dropSource.isEquiped() && oldContainer != null) {
                                //drop source is empty, just drop it if accepted
                                if (dropSource.getAcceptCriteria() == null
                                        || dropSource.getAcceptCriteria().test(newEquipment)) {
                                    newEquipment.setType(dropSource.getType());
                                    equipHasChanged = true;
                                }
                            } else {
                                //drop source is occupied, exchange values
                                if (oldContainer != null
                                        && (oldContainer.getAcceptCriteria() == null
                                                || oldContainer.getAcceptCriteria().test(oldEquipment))
                                        && (dropSource.getAcceptCriteria() == null
                                                || dropSource.getAcceptCriteria().test(newEquipment))) {

                                    oldEquipment.setType(oldContainer.getType());
                                    newEquipment.setType(dropSource.getType());
                                    equipHasChanged = true;
                                }
                            }

                            if (equipHasChanged) {
                                updateCharacter();
                                oldContainer.refresh();
                                dropSource.refresh();
                            }
                        }
                    });
                }

            });

        });
    }

    private void handleEquipmentChanged(CharacterEquipmentLayout layout, CharacterEquipment data) {
        if (layout.getAcceptCriteria() == null || layout.getAcceptCriteria().test(data)) {

            //unequip current item
            if (layout.isEquiped()) {
                CharacterEquipment oldEquip = layout.getEquipment();
                character.getEquipment().stream().filter(eq -> eq.equals(oldEquip)).findFirst().ifPresent(
                        eq -> eq.setType(null));
            }

            character.getEquipment().stream().filter(eq -> eq.equals(data)).findFirst().ifPresent(
                    eq -> eq.setType(layout.getType()));
            updateCharacter();
            refreshEquipmentGrid();

            layout.refresh();

            handleSpecific(layout, data.getEquipment());
        }
    }

    private void updateCharacter() {
        this.character = Services.getCharacterService().update(this.character);
    }
    
    @Override
    public void setCharacter(Character character) {
        this.character = character;
    }

    private void handleSpecific(CharacterEquipmentLayout layout, Equipment eq) {
        if (eq.getType() == EquipmentType.ARMOR) {
            if (layout.equals(equipedShield)) {
                shieldChanged();
            } else {
                armorChanged();
            }
        } else if (eq.getType() == EquipmentType.WEAPON) {
            if (layout.equals(equipedMainWeapon)) {
                weaponChanged((Weapon) eq, Hand.MAIN);
            } else if (layout.equals(equipedSecondaryWeapon)) {
                weaponChanged((Weapon) eq, Hand.SECONDARY);
            } else if (layout.equals(equipedTwoHandWeapon)) {
                weaponChanged((Weapon) eq, Hand.TWO);
            }
        }
    }

    private void removeSpecific(Equipment eq) {
        if (eq.getType() == EquipmentType.ARMOR) {
            refreshAC();
        } else if (eq.getType() == EquipmentType.WEAPON) {
            refreshAttackLabel();
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

    private void shieldChanged() {
        if (equipedSecondaryWeapon.isEquiped() || equipedTwoHandWeapon.isEquiped()) {

            //unequip the secondary weapon and two-hand weapon
            character.getEquipment()
                    .stream()
                    .filter(eq -> eq.isEquiped())
                    .filter(eq -> eq.getType() == EquipedType.SECONDARY_WEAPON
                            || eq.getType() == EquipedType.TWO_HAND_WEAPON)
                    .forEach(eq -> eq.setType(null));

            updateCharacter();
            refreshEquipmentGrid();
            equipedSecondaryWeapon.refresh();
            equipedTwoHandWeapon.refresh();
            refreshAttackLabel();
        }

        refreshAC();
    }

    private void armorChanged() {
        refreshAC();
    }

    private void refreshAC() {
        int ac = 10;
        if (!equipedArmor.isEquiped()) {
            //dex modifier
            ac += ModifierUtil.getAbilityModifier(character.getDexterity());
        } else {
            //armor
            Armor armor = (Armor) equipedArmor.getEquipment().getEquipment();
            ac += armor.getArmorClass();
            if (armor.getArmorType().getMaxDexBonus() != ArmorType.NO_MAX_DEX_BONUS) {
                ac += Math.min(ModifierUtil.getAbilityModifier(character.getDexterity()),
                        armor.getArmorType().getMaxDexBonus());
            } else {
                ac += ModifierUtil.getAbilityModifier(character.getDexterity());
            }
        }
        if (equipedShield.isEquiped()) {
            Armor shield = (Armor) equipedShield.getEquipment().getEquipment();
            ac += shield.getArmorClass();
        }
        acLabel.setValue(ac);
    }

    private void weaponChanged(Weapon weapon, Hand hand) {

        //manage handle
        if (weapon == null || weapon.getWeaponType().getHandleType() == HandleType.ONE_HANDED) {
            manageOneHandWeapon(hand);
        } else if (weapon.getWeaponType().getHandleType() == HandleType.TWO_HANDED) {
            manageTwoHandWeapon();
        } else if (weapon.getWeaponType().getHandleType() == HandleType.VERSATILE) {
            if (hand == Hand.MAIN || hand == Hand.SECONDARY) {
                manageOneHandWeapon(hand);
            } else if (hand == Hand.TWO) {
                manageTwoHandWeapon();
            }
        }

        refreshAttackLabel();
        refreshAC();
    }

    private void refreshAttackLabel() {

        Weapon mainWeapon = null;
        Weapon secWeapon = null;
        Weapon twoHandWeapon = null;
        Weapon weaponWithModifier = null;

        if (equipedMainWeapon.isEquiped()) {
            mainWeapon = (Weapon) equipedMainWeapon.getEquipment().getEquipment();
            weaponWithModifier = mainWeapon;
        }

        if (equipedSecondaryWeapon.isEquiped()) {
            secWeapon = (Weapon) equipedSecondaryWeapon.getEquipment().getEquipment();
        }

        if (equipedTwoHandWeapon.isEquiped()) {
            twoHandWeapon = (Weapon) equipedTwoHandWeapon.getEquipment().getEquipment();
            weaponWithModifier = twoHandWeapon;
        }

        if (mainWeapon == null && secWeapon == null && twoHandWeapon == null) {
            //no weapon
            attackLabel.setValue("1");
            //TODO : check if class has no weapon damage
        } else {
            //weapon
            Dice attackDice = null;
            Dice attackDice2 = null;

            //damage
            if (mainWeapon != null) {
                attackDice = new Dice(mainWeapon.getOneHandDamage());
                if (secWeapon != null) {
                    attackDice2 = new Dice(secWeapon.getOneHandDamage());
                }
            } else if (secWeapon != null) {
                //in case the secondary weapon if left alone
                attackDice = new Dice(secWeapon.getOneHandDamage());
            } else if (twoHandWeapon != null) {
                attackDice = new Dice(twoHandWeapon.getTwoHandDamage());
            }

            //damage modifier
            int modifier = 0;
            if (weaponWithModifier != null) {
                if (weaponWithModifier.getWeaponType().getUsageType() == UsageType.MELEE) {
                    if (weaponWithModifier.getWeaponType().getIsFinesse()) {
                        modifier = Math.max(ModifierUtil.getPositiveAbilityModifier(character.getDexterity()),
                                ModifierUtil.getPositiveAbilityModifier(character.getStrength()));
                    } else {
                        modifier = ModifierUtil.getPositiveAbilityModifier(character.getStrength());
                    }
                } else if (weaponWithModifier.getWeaponType().getUsageType() == UsageType.RANGE) {
                    if (weaponWithModifier.getWeaponType().getRangeType() == RangeType.AMMUNITION) {
                        modifier = ModifierUtil.getPositiveAbilityModifier(character.getDexterity());
                    } else if (weaponWithModifier.getWeaponType().getRangeType() == RangeType.THROWN) {
                        modifier = ModifierUtil.getPositiveAbilityModifier(character.getStrength());
                    }
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
     * @param hand 
     * @param weapon : the weapon changed
     * @param hand : main or secondary hand on which the weapon was changed
     */
    private void manageOneHandWeapon(Hand hand) {

        //if a two-handed weapon was selected and the main hand changes for one-handed weapon, clear the two-hand
        if (equipedTwoHandWeapon.isEquiped()) {

            //unequip the item
            character.getEquipment()
                    .stream()
                    .filter(eq -> eq.isEquiped())
                    .filter(eq -> eq.getType() == EquipedType.TWO_HAND_WEAPON)
                    .findFirst()
                    .ifPresent(eq -> eq.setType(null));

            updateCharacter();
            refreshEquipmentGrid();
            equipedTwoHandWeapon.refresh();
        }

        if (hand == Hand.SECONDARY && equipedShield.isEquiped()) {

            //unequip the shield
            character.getEquipment()
                    .stream()
                    .filter(eq -> eq.isEquiped())
                    .filter(eq -> eq.getType() == EquipedType.SHIELD)
                    .findFirst()
                    .ifPresent(eq -> eq.setType(null));

            updateCharacter();
            refreshEquipmentGrid();
            equipedShield.refresh();
        }
    }

    /**
     * Manage two-hand weapon change.
     * Clear both hands and shield.
     * @param weapon : the weapon changed
     * @param hand : main or secondary hand on which the weapon was changed
     */
    private void manageTwoHandWeapon() {

        //if a main or secondary weapon was selected and the weapon changes for two-handed weapon, clear the main and secondary
        if (equipedMainWeapon.isEquiped() || equipedSecondaryWeapon.isEquiped()) {

            //unequip the items
            character.getEquipment()
                    .stream()
                    .filter(eq -> eq.isEquiped())
                    .filter(eq -> eq.getType() == EquipedType.MAIN_WEAPON
                            || eq.getType() == EquipedType.SECONDARY_WEAPON)
                    .forEach(eq -> eq.setType(null));

            updateCharacter();
            refreshEquipmentGrid();

            equipedMainWeapon.refresh();
            equipedSecondaryWeapon.refresh();
        }

        if (equipedShield.isEquiped()) {

            //unequip the shield
            character.getEquipment()
                    .stream()
                    .filter(eq -> eq.isEquiped())
                    .filter(eq -> eq.getType() == EquipedType.SHIELD)
                    .findFirst()
                    .ifPresent(eq -> eq.setType(null));

            updateCharacter();
            refreshEquipmentGrid();
            equipedShield.refresh();
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

    public static Predicate<CharacterEquipment> isHelmet() {
        return ce -> ce.getEquipment().getType() == EquipmentType.HELMET;
    }

    public static Predicate<CharacterEquipment> isBoot() {
        return ce -> ce.getEquipment().getType() == EquipmentType.BOOT;
    }

    public static Predicate<CharacterEquipment> isBracer() {
        return ce -> ce.getEquipment().getType() == EquipmentType.BRACER;
    }
    
    public static Predicate<CharacterEquipment> isRing() {
        return ce -> ce.getEquipment().getType() == EquipmentType.RING;
    }
    
    public static Predicate<CharacterEquipment> isAmulet() {
        return ce -> ce.getEquipment().getType() == EquipmentType.AMULET;
    }
    
    public static Predicate<CharacterEquipment> isBelt() {
        return ce -> ce.getEquipment().getType() == EquipmentType.BELT;
    }
    
    public static Predicate<CharacterEquipment> isAmmunition() {
        return ce -> ce.getEquipment().getType() == EquipmentType.AMMUNITION;
    }

    public static Predicate<CharacterEquipment> isShield() {
        return ce -> {
            if (ce.getEquipment().getType() == EquipmentType.ARMOR) {
                Armor shield = (Armor) ce.getEquipment();
                return shield.getArmorType().getProficiencyType() == ProficiencyType.SHIELD;
            }
            return false;
        };
    }

    public Predicate<CharacterEquipment> isOneHandSecondaryWeapon() {
        return ce -> {
            if (!equipedMainWeapon.isEquiped()) {
                Notification.show("Une arme principale doit être équipée en premier", Type.WARNING_MESSAGE);
            } else if (ce.getEquipment().getType() == EquipmentType.WEAPON) {
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
                return w.getWeaponType().getHandleType() == HandleType.TWO_HANDED
                        || w.getWeaponType().getHandleType() == HandleType.VERSATILE;
            }
            return false;
        };
    }

    private void showEquipmentWindow(Equipment equipment) {
        //        Messages messages = Messages.getInstance();
        Window window = new Window(equipment.getName());
        window.setModal(true);
        window.setWidth("60%");

        FormLayout layout = new FormLayout();
        DSLabel description = new DSLabel("Description", equipment.getDescription());
        layout.addComponents(description);

        //TODO : other useful info

        window.setContent(layout);
        UI.getCurrent().addWindow(window);
    }

    class CharacterEquipmentLayout extends CustomComponent {

        private static final long serialVersionUID = -6167944474147894309L;

        private AbstractOrderedLayout         layout;
        private EquipedType                   type;
        private CharacterEquipment            equipment;
        private Label                         equipmentLabel;
        private Predicate<CharacterEquipment> acceptCriteria;
        private Supplier<CharacterEquipment>  supplier;

        public CharacterEquipmentLayout(EquipedType type, AbstractOrderedLayout layout,
                Predicate<CharacterEquipment> acceptCriteria, Supplier<CharacterEquipment> supplier) {
            this.type = type;
            this.layout = layout;
            this.acceptCriteria = acceptCriteria;
            this.supplier = supplier;
            this.equipmentLabel = new Label();
            if (type.getName() != null) {
                this.equipmentLabel.setCaption(type.getName());
                this.equipmentLabel.addStyleName(DSTheme.CAPTION_BOLD);
            }
            createDragSource(equipmentLabel);
            this.layout.addComponent(equipmentLabel);
            this.layout.setMargin(new MarginInfo(false, true));

            this.equipmentLabel.addContextClickListener(event -> {
                if (event != null && event.getButton() == MouseButton.RIGHT) {
                    if (this.equipment != null) {
                        showEquipmentWindow(this.equipment.getEquipment());
                    }
                }
            });

            setCompositionRoot(layout);
        }

        public EquipedType getType() {
            return type;
        }

        public CharacterEquipment getEquipment() {
            return equipment;
        }

        //        public void setEquipment(CharacterEquipment data) {
        //            Objects.requireNonNull(data);
        //            equipmentLabel.setValue(data.getEquipment().getName());
        //            equipmentLabel.setData(data);
        //            if (type.getName() != null) {
        //                equipmentLabel.setCaption(type.getName());
        //            }
        //
        //            this.equipment = data;
        //            this.equipment.setType(getType());
        //        }

        //        public void removeEquipment() {
        //            equipmentLabel.setValue("");
        //            equipment = null;
        //        }

        public void refresh() {
            equipment = supplier.get();
            if (equipment != null) {
                equipmentLabel.setValue(equipment.getEquipment().getName());
                equipmentLabel.setData(equipment);
            } else {
                equipmentLabel.setValue("");
                equipmentLabel.setData(null);
            }
        }

        public boolean isEquiped() {
            return this.getEquipment() != null;
        }

        public Predicate<CharacterEquipment> getAcceptCriteria() {
            return acceptCriteria;
        }

        private void createDragSource(Label label) {
            DragSourceExtension<Label> dragSource = new DragSourceExtension<>(label);

            // set allowed effects
            dragSource.setEffectAllowed(EffectAllowed.MOVE);

            dragSource.addDragStartListener(event -> dragSource.setDragData(label.getData()));
            dragSource.addDragEndListener(event -> dragSource.setDragData(null));
        }

    }

}
