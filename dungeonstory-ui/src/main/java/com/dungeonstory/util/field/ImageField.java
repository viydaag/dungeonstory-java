package com.dungeonstory.util.field;

import java.io.File;
import java.util.Locale;

import com.dungeonstory.DSConstant;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Image;

public class ImageField extends CustomField<Image> {

    private static final long serialVersionUID = -1225423843643423836L;

    private Image  image;
    private String imagePath;

    public ImageField() {
        image = new Image();
        setConverter(new Converter<Image, String>() {

            private static final long serialVersionUID = 631581091435891378L;

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<Image> getPresentationType() {
                return Image.class;
            }

            @Override
            public String convertToModel(Image value, Class<? extends String> targetType, Locale locale)
                    throws com.vaadin.data.util.converter.Converter.ConversionException {
                FileResource source = (FileResource) value.getSource();
                String filename = source.getSourceFile().getAbsolutePath();
                int lastSlash = filename.lastIndexOf(File.separator);
                int second = filename.lastIndexOf(File.separator, lastSlash - 1);
                String path = filename.substring(second);
                if (imagePath == null) {
                    imagePath = path;
                }
                return path;
            }

            @Override
            public Image convertToPresentation(String value, Class<? extends Image> targetType, Locale locale)
                    throws com.vaadin.data.util.converter.Converter.ConversionException {
                File imageFile = new File(DSConstant.getImageDir() + value);
                FileResource resource = new FileResource(imageFile);
                image.setSource(resource);
                return image;
            }
        });
    }

    @Override
    protected Component initContent() {
        return getValue();
    }

    @Override
    public Class<? extends Image> getType() {
        return Image.class;
    }

    public void setImagePath(String imagePath) {
        if (imagePath != null && !imagePath.equals(this.imagePath)) {
            this.imagePath = imagePath;
            this.image = getConverter().convertToPresentation(imagePath, Image.class, null);
            setValue(new Image("", this.image.getSource()));
        }
    }

    public String getImagePath() {
        return imagePath;
    }

}
