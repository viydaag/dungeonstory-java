package com.dungeonstory.form;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ArmorTypeService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockArmorTypeService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.util.field.DoubleField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class EquipmentForm<T extends Equipment> extends DSAbstractForm<T> {

    private static final long serialVersionUID = -5986264153168207722L;

    private TextField                 name;
    private EnumSelect<EquipmentType> type;
    private TextArea                  description;
    private DoubleField               weight;
    private FormCheckBox              isPurchasable;
    private FormCheckBox              isSellable;
    private FormCheckBox              isMagical;

    // Armor fields
    private TypedSelect<ArmorType> armorType;
    private IntegerField           acBonus;
    private IntegerField           magicalAcBonus;

    // Weapon fields
    private TypedSelect<WeaponType> weaponType;
    private TextField               oneHandDamage;
    private TextField               twoHandDamage;
    private TextField               additionalDamage;
    private TypedSelect<DamageType> additionalDamageType;
    private IntegerField            magicalBonus;

    private EquipmentType oldType = null;

    private DataService<ArmorType, Long>  armorTypeService;
    private DataService<WeaponType, Long> weaponTypeService;
    private DataService<DamageType, Long> damageTypeService;

    public EquipmentForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            armorTypeService = MockArmorTypeService.getInstance();
            weaponTypeService = MockWeaponTypeService.getInstance();
            damageTypeService = MockDamageTypeService.getInstance();
        } else {
            armorTypeService = ArmorTypeService.getInstance();
            weaponTypeService = WeaponTypeService.getInstance();
            damageTypeService = DamageTypeService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Équipements";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        type = new EnumSelect<EquipmentType>("Type");
        description = new MTextArea("Description").withFullWidth();
        weight = new DoubleField("Poids (en lbs)");
        isPurchasable = new FormCheckBox("Peut être acheté");
        isSellable = new FormCheckBox("Peut être vendu");
        isMagical = new FormCheckBox("Magique");

        armorType = new TypedSelect<ArmorType>("Type d'armure", armorTypeService.findAll());
        acBonus = new IntegerField("Classe d'armure bonus");
        magicalAcBonus = new IntegerField("Classe d'armure magique bonus");

        weaponType = new TypedSelect<WeaponType>("Type d'arme", weaponTypeService.findAll());
        oneHandDamage = new MTextField("Dommages à une main");
        twoHandDamage = new MTextField("Dommages à deux main");
        additionalDamage = new MTextField("Dommages additionnels");
        additionalDamageType = new TypedSelect<DamageType>("Type dommages additionnels", damageTypeService.findAll());
        magicalBonus = new IntegerField("Bonus magique");

        type.addMValueChangeListener(createTypeChangeListener());
        weaponType.addMValueChangeListener(createWeaponTypeChangeListener());
        armorType.addMValueChangeListener(createArmorTypeChangeListener());

        layout.addComponent(name);
        layout.addComponent(type);
        layout.addComponent(description);
        layout.addComponent(isPurchasable);
        layout.addComponent(isSellable);
        layout.addComponent(isMagical);

        layout.addComponents(armorType, acBonus, magicalAcBonus);
        layout.addComponents(weaponType, oneHandDamage, twoHandDamage, additionalDamage, additionalDamageType,
                magicalAcBonus);

        layout.addComponent(weight);

        layout.addComponent(getToolbar());

        initEntity(type.getValue());

        return layout;
    }

    private MValueChangeListener<EquipmentType> createTypeChangeListener() {
        return new MValueChangeListener<EquipmentType>() {

            private static final long serialVersionUID = -4907705123852819323L;

            @Override
            public void valueChange(MValueChangeEvent<EquipmentType> event) {
                EquipmentType type = event.getValue();
                initEntity(type);
            }
        };
    }

    private MValueChangeListener<ArmorType> createArmorTypeChangeListener() {
        return new MValueChangeListener<ArmorType>() {

            private static final long serialVersionUID = 4768217107409472231L;

            @Override
            public void valueChange(MValueChangeEvent<ArmorType> event) {
                ArmorType currentarmorType = event.getValue();
                if (currentarmorType != null) {
                    weight.setValue((double) currentarmorType.getBaseWeight());
                }
            }
        };
    }

    private MValueChangeListener<WeaponType> createWeaponTypeChangeListener() {
        return new MValueChangeListener<WeaponType>() {

            private static final long serialVersionUID = 4768217107409472231L;

            @Override
            public void valueChange(MValueChangeEvent<WeaponType> event) {
                WeaponType currentweaponType = event.getValue();
                if (currentweaponType != null) {
                    if (currentweaponType.getHandleType() == HandleType.ONE_HANDED
                            || currentweaponType.getHandleType() == HandleType.VERSATILE) {
                        oneHandDamage.setVisible(true);
                        oneHandDamage.setValue(currentweaponType.getOneHandBaseDamage());
                        if (currentweaponType.getHandleType() == HandleType.ONE_HANDED) {
                            twoHandDamage.setVisible(false);
                        }
                    }
                    if (currentweaponType.getHandleType() == HandleType.TWO_HANDED
                            || currentweaponType.getHandleType() == HandleType.VERSATILE) {
                        twoHandDamage.setVisible(true);
                        twoHandDamage.setValue(currentweaponType.getTwoHandBaseDamage());
                        if (currentweaponType.getHandleType() == HandleType.TWO_HANDED) {
                            oneHandDamage.setVisible(false);
                        }
                    }
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    private void initEntity(EquipmentType type) {
        if (type == null) {
            showArmorFields(false);
            showWeaponFields(false);
        } else if (typeChanged(oldType, type)) {
            Equipment equip = null;
            switch (type) {
                case ARMOR:
                    equip = type.getEquipment();
                    showArmorFields(true);
                    showWeaponFields(false);
                    break;
                case WEAPON:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(true);
                    break;
                case BRACER:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                case AMULET:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                case BELT:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                case RING:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                case BOOT:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                case TOOL:
                    equip = type.getEquipment();
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
                default:
                    showArmorFields(false);
                    showWeaponFields(false);
                    break;
            }
            equip.setName(name.getValue());
            equip.setType(type);
            equip.setDescription(description.getValue());
            equip.setIsPurchasable(isPurchasable.getValue());
            equip.setIsSellable(isSellable.getValue());
            setEntity((T) equip);
        }
        oldType = type;
    }

    private void showArmorFields(boolean visible) {
        armorType.setVisible(visible);
        acBonus.setVisible(visible);
        magicalAcBonus.setVisible(visible);
        if (!visible) {
            armorType.clear();
            acBonus.setValue(0);
            magicalAcBonus.setValue(0);
        }
    }

    private void showWeaponFields(boolean visible) {
        weaponType.setVisible(visible);
        oneHandDamage.setVisible(visible);
        twoHandDamage.setVisible(visible);
        additionalDamage.setVisible(visible);
        additionalDamageType.setVisible(visible);
        magicalBonus.setVisible(visible);
        if (!visible) {
            weaponType.clear();
            oneHandDamage.clear();
            twoHandDamage.clear();
            additionalDamage.clear();
            additionalDamageType.clear();
            magicalBonus.setValue(0);
        }
    }

    private boolean typeChanged(EquipmentType type1, EquipmentType type2) {
        if ((type1 == null && type2 != null) || (type1 != null && type2 == null)) {
            return true;
        }
        if (type1 == EquipmentType.ARMOR && type2 != EquipmentType.ARMOR) {
            return true;
        }
        if (type1 == EquipmentType.WEAPON && type2 != EquipmentType.WEAPON) {
            return true;
        }
        if (type2 == EquipmentType.ARMOR && type1 != EquipmentType.ARMOR) {
            return true;
        }
        if (type2 == EquipmentType.WEAPON && type1 != EquipmentType.WEAPON) {
            return true;
        }
        return false;
    }
}
