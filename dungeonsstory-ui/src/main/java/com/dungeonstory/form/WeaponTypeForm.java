package com.dungeonstory.form;

import org.vaadin.viritin.fields.CaptionGenerator;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.FormCheckBox;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.util.field.DoubleField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class WeaponTypeForm extends DSAbstractForm<WeaponType> {

    private static final long serialVersionUID = 7328613287024515502L;

    private TextField                   name;
    private TextArea                    description;
    private EnumSelect<ProficiencyType> proficiencyType;
    private EnumSelect<SizeType>        sizeType;
    private EnumSelect<HandleType>      handleType;
    private EnumSelect<UsageType>       usageType;
    private EnumSelect<RangeType>       rangeType;
    private TextField                   oneHandBaseDamage;
    private TextField                   twoHandBaseDamage;
    private TypedSelect<DamageType>     damageType;
    private FormCheckBox                isReach;
    private FormCheckBox                isFinesse;
    private FormCheckBox                isLoading;
    private DoubleField                 baseWeight;
    private IntegerField                basePrice;

    private DataService<DamageType, Long> damageTypeService = DamageTypeService.getInstance();

    MValueChangeListener<UsageType> usageListener;

    @Override
    public String toString() {
        return "Types d'arme";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();
        proficiencyType = new EnumSelect<ProficiencyType>("Type de compétence");
        sizeType = new EnumSelect<SizeType>("Taille");
        handleType = new EnumSelect<HandleType>("Type");
        usageType = new EnumSelect<UsageType>("Type d'usage");
        rangeType = new EnumSelect<RangeType>("Type de portée");
        oneHandBaseDamage = new MTextField("Dommage à 1 main");
        twoHandBaseDamage = new MTextField("Dommage à 2 mains");
        isReach = new FormCheckBox("Portée longue");
        isFinesse = new FormCheckBox("Finesse (choix dextérité ou force)");
        isLoading = new FormCheckBox("Chargement requis");
        baseWeight = new DoubleField("Poids de base (lbs)");
        basePrice = new IntegerField("Prix de base");

        damageType = new TypedSelect<DamageType>("Type de dommage", damageTypeService.findAll());
        damageType.setCaptionGenerator(new CaptionGenerator<DamageType>() {

            private static final long serialVersionUID = 9011176307449121578L;

            @Override
            public String getCaption(DamageType option) {
                return option.getName();
            }
        });

        handleType.addMValueChangeListener(createHandleTypeValueChangeListener());

        usageListener = createUsageTypeValueChangeListener();
        usageType.addMValueChangeListener(usageListener);

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

    private MValueChangeListener<UsageType> createUsageTypeValueChangeListener() {
        return new MValueChangeListener<UsageType>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(MValueChangeEvent<UsageType> event) {
                if (event != null && event.getValue() != null) {
                    initRangeType(event.getValue());
                }
            }
        };
    }

    private MValueChangeListener<HandleType> createHandleTypeValueChangeListener() {
        return new MValueChangeListener<HandleType>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(MValueChangeEvent<HandleType> event) {
                if (event != null && event.getValue() != null) {
                    switch (event.getValue()) {
                        case ONE_HANDED:
                            oneHandBaseDamage.setVisible(true);
                            twoHandBaseDamage.setVisible(false);
                            twoHandBaseDamage.setValue(null);
                            break;
                        case TWO_HANDED:
                            oneHandBaseDamage.setVisible(false);
                            oneHandBaseDamage.setValue(null);
                            twoHandBaseDamage.setVisible(true);
                            break;
                        case VERSATILE:
                            oneHandBaseDamage.setVisible(true);
                            twoHandBaseDamage.setVisible(true);
                            break;
                        default:
                            oneHandBaseDamage.setVisible(false);
                            oneHandBaseDamage.setValue(null);
                            twoHandBaseDamage.setVisible(false);
                            twoHandBaseDamage.setValue(null);
                            break;
                    }
                }
            }
        };
    }

    @Override
    public void beforeSetEntity() {

        //prevent the binding to cause read-only exception while setting the value
        if (rangeType != null) {
            rangeType.setReadOnly(false);
        }
        if (usageType != null) {
            usageType.removeMValueChangeListener(usageListener);
        }
    }

    @Override
    public void afterSetEntity() {
        usageType.addMValueChangeListener(usageListener);
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
                    rangeType.setReadOnly(false);
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
