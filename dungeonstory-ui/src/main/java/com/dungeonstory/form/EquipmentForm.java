package com.dungeonstory.form;

import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Tool.ToolType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ArmorTypeService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockArmorTypeService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.util.field.DoubleField;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class EquipmentForm<T extends Equipment> extends DSAbstractForm<T> {

    private static final long serialVersionUID = -5986264153168207722L;

    private TextField                   name;
    private EnumComboBox<EquipmentType> type;
    private DSTextArea                   description;
    private DoubleField                 weight;
    private FormCheckBox                isPurchasable;
    private FormCheckBox                isSellable;
    private FormCheckBox                isMagical;
    private IntegerField                basePrice;

    // Armor fields
    private ComboBox<ArmorType> armorType;
    private IntegerField           armorClass;
    private IntegerField           magicalAcBonus;

    // Weapon fields
    private ComboBox<WeaponType> weaponType;
    private TextField               oneHandDamage;
    private TextField               twoHandDamage;
    private TextField               additionalDamage;
    private ComboBox<DamageType> additionalDamageType;
    private IntegerField            magicalBonus;

    // Tool fields
    private EnumComboBox<ToolType> toolType;

    private EquipmentType oldType = null;

    private DataService<ArmorType, Long>  armorTypeService;
    private DataService<WeaponType, Long> weaponTypeService;
    private DataService<DamageType, Long> damageTypeService;

    public EquipmentForm() {
        super((Class<T>) Equipment.class);
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
        return "Équipement";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("50%");
        type = new EnumComboBox<EquipmentType>(EquipmentType.class, "Type");
        description = new DSTextArea("Description").withFullWidth().withRows(10);
        weight = new DoubleField("Poids (en lbs)");
        isPurchasable = new FormCheckBox("Peut être acheté");
        isSellable = new FormCheckBox("Peut être vendu");
        isMagical = new FormCheckBox("Magique");
        basePrice = new IntegerField("Prix de base");

        armorType = new ComboBox<ArmorType>("Type d'armure", armorTypeService.findAll());
        armorClass = new IntegerField("Classe d'armure bonus");
        magicalAcBonus = new IntegerField("Classe d'armure magique bonus");

        weaponType = new ComboBox<WeaponType>("Type d'arme", weaponTypeService.findAll());
        oneHandDamage = new MTextField("Dommages à une main");
        twoHandDamage = new MTextField("Dommages à deux main");
        additionalDamage = new MTextField("Dommages additionnels");
        additionalDamageType = new ComboBox<DamageType>("Type dommages additionnels", damageTypeService.findAll());
        magicalBonus = new IntegerField("Bonus magique");
        
        toolType = new EnumComboBox<ToolType>(ToolType.class, "Type d'outil");

        isMagical.addValueChangeListener(this::isMagicalChange);
        type.addSelectionListener(this::typeChange);
        weaponType.addSelectionListener(this::weaponTypeChange);
        armorType.addSelectionListener(this::armorTypeChange);

        layout.addComponent(name);
        layout.addComponent(type);
        layout.addComponent(description);
        layout.addComponent(isPurchasable);
        layout.addComponent(isSellable);
        layout.addComponent(isMagical);

        layout.addComponents(armorType, armorClass, magicalAcBonus);
        layout.addComponents(weaponType, oneHandDamage, twoHandDamage, additionalDamage, additionalDamageType,
                magicalBonus);
        layout.addComponent(toolType);

        layout.addComponent(weight);
        layout.addComponent(basePrice);

        layout.addComponent(getToolbar());

        initEntity(type.getValue());

        return layout;
    }

    public void isMagicalChange(ValueChangeEvent event) {
        if (type == null) {
            magicalAcBonus.setValue(null);
            magicalAcBonus.setVisible(false);
            magicalBonus.setValue(null);
            magicalBonus.setVisible(false);
        } else {
            if (type.getValue() == EquipmentType.ARMOR) {
                magicalAcBonus.setValue(isMagical.getValue() ? magicalAcBonus.getValue() : null);
                magicalAcBonus.setVisible(isMagical.getValue());
            } else if (type.getValue() == EquipmentType.WEAPON) {
                magicalBonus.setValue(isMagical.getValue() ? magicalBonus.getValue() : null);
                magicalBonus.setVisible(isMagical.getValue());
            }
        }
    }

    public void typeChange(SingleSelectionEvent<EquipmentType> event) {
        EquipmentType type = event.getValue();
        initEntity(type);
        isMagicalChange(null);
    }

    public void armorTypeChange(SingleSelectionEvent<ArmorType> event) {
        ArmorType currentarmorType = event.getValue();
        if (currentarmorType != null) {
            description.setValue(currentarmorType.getDescription());
            armorClass.setValue(currentarmorType.getBaseArmorClass());
            weight.setValue(currentarmorType.getBaseWeight());
            basePrice.setValue(currentarmorType.getBasePrice());
        }
    }

    public void weaponTypeChange(SingleSelectionEvent<WeaponType> event) {
        WeaponType currentweaponType = event.getValue();
        if (currentweaponType != null) {
            if (currentweaponType.getHandleType() == HandleType.ONE_HANDED || currentweaponType.getHandleType() == HandleType.VERSATILE) {
                oneHandDamage.setVisible(true);
                oneHandDamage.setValue(currentweaponType.getOneHandBaseDamage());
                if (currentweaponType.getHandleType() == HandleType.ONE_HANDED) {
                    twoHandDamage.setVisible(false);
                }
            }
            if (currentweaponType.getHandleType() == HandleType.TWO_HANDED || currentweaponType.getHandleType() == HandleType.VERSATILE) {
                twoHandDamage.setVisible(true);
                twoHandDamage.setValue(currentweaponType.getTwoHandBaseDamage());
                if (currentweaponType.getHandleType() == HandleType.TWO_HANDED) {
                    oneHandDamage.setVisible(false);
                }
            }
            description.setValue(currentweaponType.getDescription());
            weight.setValue(currentweaponType.getBaseWeight());
            basePrice.setValue(currentweaponType.getBasePrice());
        }
    }

    @SuppressWarnings("unchecked")
    private void initEntity(EquipmentType type) {
        if (type == null) {
            showArmorFields(false);
            showWeaponFields(false);
            showToolFields(false);
        } else if (typeChanged(oldType, type)) {
            Equipment equip;
            try {
                equip = type.getEquipment();

                showArmorFields(type == EquipmentType.ARMOR);
                showWeaponFields(type == EquipmentType.WEAPON);
                showToolFields(type == EquipmentType.TOOL);

                equip.setName(name.getValue());
                equip.setType(type);
                equip.setDescription(description.getValue());
                equip.setIsPurchasable(isPurchasable.getValue());
                equip.setIsSellable(isSellable.getValue());
                setEntity((T) equip);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        oldType = type;
    }

    private void showArmorFields(boolean visible) {
        armorType.setVisible(visible);
        armorClass.setVisible(visible);
        magicalAcBonus.setVisible(visible);
        if (!visible) {
            armorType.clear();
            armorClass.setValue(0);
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

    private void showToolFields(boolean visible) {
        toolType.setVisible(visible);
        if (!visible) {
            toolType.clear();
        }
    }

    private boolean typeChanged(EquipmentType type1, EquipmentType type2) {
        if ((type1 == null && type2 != null) || (type1 != null && type2 == null)) {
            return true;
        }
        if (type1 == type2) {
            return false;
        }
        if (type2 == EquipmentType.ARMOR || type2 == EquipmentType.WEAPON || type2 == EquipmentType.TOOL) {
            return true;
        }
        if ((type1 == EquipmentType.ARMOR || type1 == EquipmentType.WEAPON || type1 == EquipmentType.TOOL)
                && (type2 != EquipmentType.ARMOR && type2 != EquipmentType.WEAPON && type2 != EquipmentType.TOOL)) {
            return true;
        }

        return false;
    }
}
