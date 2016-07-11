package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.samples.crud.BeanGrid;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

public class WeaponTypeGrid extends BeanGrid<WeaponType> {

	private static final long serialVersionUID = -425928960446143041L;

	public WeaponTypeGrid() {
		super(WeaponType.class);
		withColumns("name", "proficiencyType", "handleType", "usageType", "rangeType", "baseDamage", "damageType", "isReach");
		withHeaderCaption("Nom", "Type de compétence", "Type", "Type d'usage", "Type de portée", "Dommage de base", "Type de dommage", "Portée longue");
		
		Grid.Column reach = getColumn("isReach");
		reach.setRenderer(new HtmlRenderer(),
		    new StringToBooleanConverter(
		        FontAwesome.CHECK_CIRCLE_O.getHtml(),
		        FontAwesome.CIRCLE_O.getHtml()));
	}

}
