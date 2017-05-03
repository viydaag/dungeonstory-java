package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.util.renderer.ModifierRenderer;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.renderers.HtmlRenderer;

public class ArmorTypeGrid extends DSGrid<ArmorType> {

    private static final long serialVersionUID = -425928960446143041L;

    public ArmorTypeGrid() {
        super();
        //        withProperties("name", "proficiencyType", "baseArmorClass", "maxDexBonus", "stealthDisavantage", "minStrength");
        //        withColumnHeaders("Nom", "Maitrise", "Classe d'armure", "Bonus max dextérité", "Désavantage furtivité", "Force minimum");
        //        
        //        getColumn("maxDexBonus").setRenderer(new ModifierRenderer());
        //        getColumn("baseArmorClass").setRenderer(new ModifierRenderer());
        //        
        //        Grid.Column stealthDisavantage = getColumn("stealthDisavantage");
        //        stealthDisavantage.setRenderer(new HtmlRenderer(),
        //            new StringToBooleanConverter(
        //                FontAwesome.CHECK_CIRCLE_O.getHtml(),
        //                FontAwesome.CIRCLE_O.getHtml()));
        
        StringToBooleanConverter converter = new StringToBooleanConverter("", VaadinIcons.CHECK_CIRCLE_O.getHtml(),
                VaadinIcons.CIRCLE_THIN.getHtml());
        addColumn(ArmorType::getName).setCaption("Nom").setId("name");
        addColumn(ArmorType::getProficiencyType).setCaption("Maitrise").setId("proficiencyType");
        addColumn(ArmorType::getBaseArmorClass, new ModifierRenderer()).setCaption("Classe d'armure").setId("baseArmorClass");
        addColumn(ArmorType::getMaxDexBonus, new ModifierRenderer()).setCaption("Bonus max dextérité").setId("maxDexBonus");
        addColumn(armorType -> converter.convertToPresentation(armorType.getStealthDisavantage(), new ValueContext()), new HtmlRenderer())
                .setCaption("Désavantage furtivité");
        addColumn(ArmorType::getMinStrength).setCaption("Force minimum").setId("minStrength");

    }

}
