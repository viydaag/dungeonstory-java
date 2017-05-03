package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Shop;

public class ShopGrid extends DSGrid<Shop> {

    private static final long serialVersionUID = 7923046661564398516L;

    public ShopGrid() {
        super();
        addColumn(Shop::getName).setCaption("Nom du magasin").setId("name");
        addColumn(Shop::getCity).setCaption("Ville").setId("city");
    }

}
