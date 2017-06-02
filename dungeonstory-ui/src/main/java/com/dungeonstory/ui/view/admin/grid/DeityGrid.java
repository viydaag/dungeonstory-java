package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.ui.converter.ByteArrayToString;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.renderer.ByteArrayImageRenderer;
import com.vaadin.data.ValueContext;

public class DeityGrid extends DSGrid<Deity> {

    private static final long serialVersionUID = 2771142614538863505L;

    public DeityGrid() {
        super();
        //        withProperties("name", "alignment", "domains", "shortDescription", "image");
        //        withColumnHeaders("Nom", "Alignement", "Domaines", "Description courte", "");
        //        
        //        getColumn("domains").setConverter(new CollectionToStringConverter());
        //        
        //        //        getColumn("image").setConverter(new ByteArrayToString());
        //        getColumn("image").setRenderer(new ByteArrayImageRenderer(), );
        //        
        //        addStyleName("gridwithpics128px");
        //        setCellStyleGenerator(cell -> "image".equals(cell.getPropertyId()) ? "imagecol" : null);
        
        
        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();
        ByteArrayToString byteArrayConverter = new ByteArrayToString();
        
        addColumn(Deity::getName).setCaption("Nom").setId("name");
        addColumn(Deity::getAlignment).setCaption("Alignement").setId("alignment");
        addColumn(deity -> collectionConverter.convertToPresentation(deity.getDomains(), new ValueContext())).setCaption("Domaines").setId("domains");
        addColumn(Deity::getShortDescription).setCaption("Description courte").setId("shortDescription");
        addColumn(deity -> byteArrayConverter.convertToPresentation(deity.getImage(), new ValueContext()), new ByteArrayImageRenderer())
                .setCaption("").setId("images");
    }

}
