package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.util.renderer.ModifierRenderer;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

public class ArmorTypeGrid extends DSGrid<ArmorType> {

    private static final long serialVersionUID = -425928960446143041L;

    public ArmorTypeGrid() {
        super(ArmorType.class);
        withProperties("name", "proficiencyType", "baseArmorClass", "maxDexBonus", "stealthDisavantage", "minStrength");
        withColumnHeaders("Nom", "Maitrise", "Classe d'armure", "Bonus max dextérité", "Désavantage furtivité", "Force minimum");
        
        getColumn("maxDexBonus").setRenderer(new ModifierRenderer());
        getColumn("baseArmorClass").setRenderer(new ModifierRenderer());
        
        Grid.Column stealthDisavantage = getColumn("stealthDisavantage");
        stealthDisavantage.setRenderer(new HtmlRenderer(),
            new StringToBooleanConverter(
                FontAwesome.CHECK_CIRCLE_O.getHtml(),
                FontAwesome.CIRCLE_O.getHtml()));

    }

}
