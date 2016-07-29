package com.dungeonstory.form;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ArmorTypeService;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class EquipmentForm<T extends Equipment> extends DSAbstractForm<T> {

    private static final long serialVersionUID = -5986264153168207722L;

    private TextField                 name;
    private EnumSelect<EquipmentType> type;
    private TextArea                  description;
    private FormCheckBox              isPurchasable;
    private FormCheckBox              isSellable;

    //Armor fields
    private TypedSelect<ArmorType> armorType;

    private EquipmentType                oldType          = null;
    private DataService<ArmorType, Long> armorTypeService = ArmorTypeService.getInstance();

    public EquipmentForm() {
        super();
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
        isPurchasable = new FormCheckBox("Peut être acheté");
        isSellable = new FormCheckBox("Peut être vendu");

        armorType = new TypedSelect<ArmorType>("Type d'armure", armorTypeService.findAll());

        type.addMValueChangeListener(new MValueChangeListener<EquipmentType>() {

            private static final long serialVersionUID = -4907705123852819323L;

            @Override
            public void valueChange(MValueChangeEvent<EquipmentType> event) {
                EquipmentType type = event.getValue();
                initEntity(type);
            }

        });

        layout.addComponent(name);
        layout.addComponent(type);
        layout.addComponent(description);
        layout.addComponent(isPurchasable);
        layout.addComponent(isSellable);
        layout.addComponent(armorType);
        layout.addComponent(getToolbar());

        initEntity(type.getValue());

        return layout;
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
                    equip = new Armor();
                    showArmorFields(true);
                    showWeaponFields(false);
                    break;
                case WEAPON:
                    equip = new Weapon();
                    showArmorFields(false);
                    showWeaponFields(true);
                    break;
                default:
                    equip = new Equipment();
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
        if (!visible) {
            armorType.clear();
        }
    }

    private void showWeaponFields(boolean visible) {

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
