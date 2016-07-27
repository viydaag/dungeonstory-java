package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.samples.crud.BeanGrid;

public class ShopGrid extends BeanGrid<Shop> {

    private static final long serialVersionUID = 7923046661564398516L;

    public ShopGrid() {
		super(Shop.class);
		withColumns("name");
		withHeaderCaption("Nom");
	}

}
