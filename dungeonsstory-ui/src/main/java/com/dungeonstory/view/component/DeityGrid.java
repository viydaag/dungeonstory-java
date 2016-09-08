package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.util.converter.ByteArrayToString;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.renderer.ByteArrayImageRenderer;

public class DeityGrid extends BeanGrid<Deity> {

    private static final long serialVersionUID = 2771142614538863505L;

    public DeityGrid() {
        super(Deity.class);
        withColumns("name", "alignment", "domains", "symbol", "image");
        withHeaderCaption("Nom", "Alignement", "Domaines", "Symbole", "");
        
        getColumn("domains").setConverter(new CollectionToStringConverter());
        
        getColumn("image").setConverter(new ByteArrayToString());
        getColumn("image").setRenderer(new ByteArrayImageRenderer());
        
        setStyleName("gridwithpics128px");
        setCellStyleGenerator(cell -> "image".equals(cell.getPropertyId()) ? "imagecol" : null);
        
//        setHeightMode(HeightMode.ROW);
    }

}
