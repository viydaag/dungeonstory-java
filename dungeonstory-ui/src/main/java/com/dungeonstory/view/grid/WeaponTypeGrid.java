package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.WeaponType;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;

public class WeaponTypeGrid extends DSGrid<WeaponType> {

    private static final long serialVersionUID = -425928960446143041L;

    public WeaponTypeGrid() {
        super();
        //        withProperties("name", "proficiencyType", "handleType", "usageType", "oneHandBaseDamage",
        //                "twoHandBaseDamage", "damageType", "isReach", "isFinesse", "isLoading");
        //        withColumnHeaders("Nom", "Maitrise", "Manipulation", "Usage", "1 main", "2 mains",
        //                "Type de dommage", "Allonge", "Finesse", "Load");
        //
        //        Grid.Column reach = getColumn("isReach");
        //        reach.setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
        //
        //        Grid.Column finesse = getColumn("isFinesse");
        //        finesse.setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
        //
        //        Grid.Column loading = getColumn("isLoading");
        //        loading.setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
        //
        //        Grid.HeaderRow groupingHeader = prependHeaderRow();
        //        Grid.HeaderCell namesCell = groupingHeader.join(groupingHeader.getCell("oneHandBaseDamage"),
        //                groupingHeader.getCell("twoHandBaseDamage"));
        //        namesCell.setText("Dommage");

        StringToBooleanConverter converter = new StringToBooleanConverter("", VaadinIcons.CHECK_CIRCLE_O.getHtml(),
                VaadinIcons.CIRCLE_THIN.getHtml());
        addColumn(WeaponType::getName).setCaption("Nom").setId("name");
        addColumn(WeaponType::getProficiencyType).setCaption("Maitrise").setId("proficiencyType");
        addColumn(WeaponType::getHandleType).setCaption("Manipulation").setId("handleType");
        addColumn(WeaponType::getUsageType).setCaption("Usage").setId("usage");
        addColumn(WeaponType::getOneHandBaseDamage).setCaption("1 main").setId("oneHandBaseDamage");
        addColumn(WeaponType::getTwoHandBaseDamage).setCaption("2 mains").setId("twoHandBaseDamage");
        addColumn(WeaponType::getDamageType).setCaption("Type de dommage").setId("damageType");
        addColumn(weaponType -> converter.convertToPresentation(weaponType.getIsReach(), new ValueContext()), new HtmlRenderer())
                .setCaption("Allonge");
        addColumn(weaponType -> converter.convertToPresentation(weaponType.getIsFinesse(), new ValueContext()), new HtmlRenderer())
                .setCaption("Finesse");
        addColumn(weaponType -> converter.convertToPresentation(weaponType.getIsLoading(), new ValueContext()), new HtmlRenderer())
                .setCaption("Recharge");

        HeaderRow groupingHeader = prependHeaderRow();
        HeaderCell namesCell = groupingHeader.join(groupingHeader.getCell("oneHandBaseDamage"), groupingHeader.getCell("twoHandBaseDamage"));
        namesCell.setText("Dommage");

    }

}
