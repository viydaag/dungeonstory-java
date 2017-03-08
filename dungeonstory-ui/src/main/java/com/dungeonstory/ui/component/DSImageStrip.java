package com.dungeonstory.ui.component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.peter.imagestrip.ImageStrip;

import com.dungeonstory.DSConstant;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.util.ImageFilter;
import com.vaadin.server.FileResource;

public class DSImageStrip extends ImageStrip {

    private static final long serialVersionUID = 405468776884409065L;

    private Map<ImageStrip.Image, String> imageMap;
    
    public DSImageStrip(Gender gender, Alignment alignment) {
        super(alignment);

        imageMap = new HashMap<ImageStrip.Image, String>();

        setHeight(200, Unit.PIXELS);

        // Use animation
        setAnimated(true);

        // Make strip to behave like select
        setSelectable(true);

        // Set size of the box surrounding the images
        setImageBoxWidth(120);
        setImageBoxHeight(180);

        // Set maximum size of the images
        setImageMaxWidth(110);
        setImageMaxHeight(170);

        // Limit how many images are visible at most simultaneously
        setMaxAllowed(7);

        if (gender != null && gender.getImageDir() != null) {
            File imageDir = new File(DSConstant.getImageDir() + File.separator + gender.getImageDir());

            if (imageDir.isDirectory()) { // make sure it's a directory
                for (final File imageFile : imageDir.listFiles(new ImageFilter())) {
                    FileResource resource = new FileResource(imageFile);
                    Image image = addImage(resource);
                    imageMap.put(image, File.separator + gender.getImageDir() + File.separator + imageFile.getName());
                }
            }
        }
    }

    public Map<ImageStrip.Image, String> getImageMap() {
        return imageMap;
    }

}
