package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.util.converter.ByteArrayToString;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.renderer.ByteArrayImageRenderer;

public class DeityGrid extends DSGrid<Deity> {

    private static final long serialVersionUID = 2771142614538863505L;

    public DeityGrid() {
        super(Deity.class);
        withProperties("name", "alignment", "domains", "shortDescription", "image");
        withHeaderCaption("Nom", "Alignement", "Domaines", "Description courte", "");
        
        getColumn("domains").setConverter(new CollectionToStringConverter());
        
        //        getColumn("image").setConverter(new ByteArrayToString());
        getColumn("image").setRenderer(new ByteArrayImageRenderer(), new ByteArrayToString());
        
        addStyleName("gridwithpics128px");
        setCellStyleGenerator(cell -> "image".equals(cell.getPropertyId()) ? "imagecol" : null);
        
//        setHeightMode(HeightMode.ROW);
    }

}
