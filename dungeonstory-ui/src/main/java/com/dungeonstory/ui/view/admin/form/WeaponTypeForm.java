package com.dungeonstory.ui.view.admin.form;

import org.vaadin.viritin.fields.IntegerField;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.field.DoubleField;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.shared.Registration;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class WeaponTypeForm extends DSAbstractForm<WeaponType> {

    private static final long serialVersionUID = 7328613287024515502L;

    private TextField                     name;
    private TextArea                      description;
    private EnumComboBox<ProficiencyType> proficiencyType;
    private EnumComboBox<SizeType>        sizeType;
    private EnumComboBox<HandleType>      handleType;
    private EnumComboBox<UsageType>       usageType;
    private EnumComboBox<RangeType>       rangeType;
    private TextField                     oneHandBaseDamage;
    private TextField                     twoHandBaseDamage;
    private ComboBox<DamageType>          damageType;
    private FormCheckBox                  isReach;
    private FormCheckBox                  isFinesse;
    private FormCheckBox                  isLoading;
    private DoubleField                   baseWeight;
    private IntegerField                  basePrice;

    private Registration usageListener;

    public WeaponTypeForm() {
        super(WeaponType.class);
    }

    @Override
    public String toString() {
        return "Types d'arme";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new TextField("Nom");
        description = new FTextArea("Description").withFullWidth();
        proficiencyType = new EnumComboBox<>(ProficiencyType.class, "Type de maitrise");
        sizeType = new EnumComboBox<>(SizeType.class, "Taille");
        handleType = new EnumComboBox<>(HandleType.class, "Type");
        usageType = new EnumComboBox<>(UsageType.class, "Type d'usage");
        rangeType = new EnumComboBox<RangeType>(RangeType.class, "Type de portée");
        oneHandBaseDamage = new TextField("Dommage à 1 main");
        twoHandBaseDamage = new TextField("Dommage à 2 mains");
        isReach = new FormCheckBox("Allonge");
        isFinesse = new FormCheckBox("Finesse (choix dextérité ou force)");
        isLoading = new FormCheckBox("Chargement requis");
        baseWeight = new DoubleField("Poids de base (lbs)");
        basePrice = new IntegerField("Prix de base");

        damageType = new ComboBox<DamageType>("Type de dommage", Services.getDamageTypeService().findAll());

        handleType.addSelectionListener(this::handleTypeValueChange);

        usageListener = usageType.addSelectionListener(this::usageTypeValueChange);

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(proficiencyType);
        layout.addComponent(sizeType);
        layout.addComponent(handleType);
        layout.addComponent(usageType);
        layout.addComponent(rangeType);
        layout.addComponent(oneHandBaseDamage);
        layout.addComponent(twoHandBaseDamage);
        layout.addComponent(damageType);
        layout.addComponent(isReach);
        layout.addComponent(isFinesse);
        layout.addComponent(isLoading);
        layout.addComponent(baseWeight);
        layout.addComponent(basePrice);
        layout.addComponent(getToolbar());

        return layout;
    }

    public void usageTypeValueChange(SingleSelectionEvent<UsageType> event) {
        if (event != null && event.getValue() != null) {
            initRangeType(event.getValue());
        }
    }

    public void handleTypeValueChange(SingleSelectionEvent<HandleType> event) {
        if (event != null && event.getValue() != null) {
            switch (event.getValue()) {
                case ONE_HANDED:
                    oneHandBaseDamage.setVisible(true);
                    twoHandBaseDamage.setVisible(false);
                    twoHandBaseDamage.setValue(oneHandBaseDamage.getEmptyValue());
                    break;
                case TWO_HANDED:
                    oneHandBaseDamage.setVisible(false);
                    oneHandBaseDamage.setValue(oneHandBaseDamage.getEmptyValue());
                    twoHandBaseDamage.setVisible(true);
                    break;
                case VERSATILE:
                    oneHandBaseDamage.setVisible(true);
                    twoHandBaseDamage.setVisible(true);
                    break;
                default:
                    oneHandBaseDamage.setVisible(false);
                    oneHandBaseDamage.setValue(oneHandBaseDamage.getEmptyValue());
                    twoHandBaseDamage.setVisible(false);
                    twoHandBaseDamage.setValue(oneHandBaseDamage.getEmptyValue());
                    break;
            }
        }
    }

    @Override
    public void beforeSetEntity(WeaponType entity) {

        if (usageType != null) {
            usageListener.remove();
        }
    }

    @Override
    public void afterSetEntity() {
        usageType.addSelectionListener(this::usageTypeValueChange);
        initRangeType(usageType.getValue());
    }

    private void initRangeType(UsageType usage) {
        if (usage != null) {
            switch (usage) {
                case RANGE:
                    rangeType.setVisible(true);
                    rangeType.setReadOnly(false);
                    isReach.setVisible(false);
                    isReach.setValue(false);
                    isLoading.setVisible(true);
                    break;
                case MELEE_RANGE:
                    rangeType.setVisible(true);
                    rangeType.setValue(RangeType.THROWN);
                    rangeType.setReadOnly(true);
                    isLoading.setVisible(false);
                    isLoading.setValue(false);
                    break;
                case MELEE:
                default:
                    rangeType.setReadOnly(false);
                    rangeType.setValue(null);
                    rangeType.setVisible(false);
                    isLoading.setVisible(false);
                    isLoading.setValue(false);
                    break;
            }
        }
    }

}
