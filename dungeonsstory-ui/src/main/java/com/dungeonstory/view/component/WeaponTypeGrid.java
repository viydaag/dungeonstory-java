package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.WeaponType;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

public class WeaponTypeGrid extends BeanGrid<WeaponType> {

	private static final long serialVersionUID = -425928960446143041L;

	public WeaponTypeGrid() {
		super(WeaponType.class);
		withColumns("name", "proficiencyType", "handleType", "usageType", "rangeType", "oneHandBaseDamage", "twoHandBaseDamage", "damageType", "isReach", "isFinesse", "isLoading");
		withHeaderCaption("Nom", "Compétence", "Manipulation", "Usage", "Portée", "1 main", "2 mains", "Type de dommage", "Portée longue", "Finesse", "Chargement");
		
		Grid.Column reach = getColumn("isReach");
		reach.setRenderer(new HtmlRenderer(),
		    new StringToBooleanConverter(
		        FontAwesome.CHECK_CIRCLE_O.getHtml(),
		        FontAwesome.CIRCLE_O.getHtml()));
		
		Grid.Column finesse = getColumn("isFinesse");
		finesse.setRenderer(new HtmlRenderer(),
            new StringToBooleanConverter(
                FontAwesome.CHECK_CIRCLE_O.getHtml(),
                FontAwesome.CIRCLE_O.getHtml()));
        
        Grid.Column loading = getColumn("isLoading");
        loading.setRenderer(new HtmlRenderer(),
            new StringToBooleanConverter(
                FontAwesome.CHECK_CIRCLE_O.getHtml(),
                FontAwesome.CIRCLE_O.getHtml()));
        
        Grid.HeaderRow groupingHeader = prependHeaderRow();
        Grid.HeaderCell namesCell = groupingHeader.join(
            groupingHeader.getCell("oneHandBaseDamage"),
            groupingHeader.getCell("twoHandBaseDamage"));
        namesCell.setText("Dommage");
        
	}

}
