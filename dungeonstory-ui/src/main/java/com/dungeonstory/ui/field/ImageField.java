package com.dungeonstory.ui.field;

import java.io.File;

import com.dungeonstory.ui.util.DSConstant;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Image;

public class ImageField extends CustomField<String> {

    private static final long serialVersionUID = -1225423843643423836L;

    private Image  image;
    private String imagePath;

    public ImageField() {
        image = new Image();
    }

    @Override
    protected Component initContent() {
        return image;
    }

    @Override
    public String getValue() {
        return imagePath;
    }

    @Override
    protected void doSetValue(String value) {
        if (value != null && !value.equals(this.imagePath)) {
            this.imagePath = value;
            File imageFile = new File(DSConstant.getImageDir() + value);
            FileResource resource = new FileResource(imageFile);
            image.setSource(resource);
        }
    }



}
