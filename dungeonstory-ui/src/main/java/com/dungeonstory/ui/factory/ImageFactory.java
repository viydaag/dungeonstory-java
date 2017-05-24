package com.dungeonstory.ui.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.ui.component.DSImage;
import com.dungeonstory.ui.util.DSConstant;
import com.dungeonstory.ui.util.ImageFilter;
import com.vaadin.server.FileResource;

public class ImageFactory {

    private static ImageFactory instance = null;

    private List<DSImage> images;

    private ImageFactory() {
        images = new ArrayList<>();
    }

    public static ImageFactory getInstance() {
        if (instance == null) {
            instance = new ImageFactory();
            instance.loadImages();
        }
        return instance;
    }

    private void loadImages() {
        File imageDir = new File(DSConstant.getImageDir());
        Collection<File> listFiles = FileUtils.listFiles(imageDir, ImageFilter.getImgExtensions(), true);

        for (File file : listFiles) {
            String fullPath = file.getAbsolutePath();
            int lastSlash = fullPath.lastIndexOf(File.separator);
            int second = fullPath.lastIndexOf(File.separator, lastSlash - 1);
            String directory = fullPath.substring(second + 1, lastSlash);
            String filename = fullPath.substring(lastSlash + 1);
            DSImage image = new DSImage(new FileResource(file), directory, filename);
            images.add(image);
        }
    }

    public List<DSImage> getMaleImages() {
        return images.stream().filter(image -> image.getDirectory().equals(Gender.M.getImageDir())).collect(Collectors.toList());
    }

    public List<DSImage> getFemaleImages() {
        return images.stream().filter(image -> image.getDirectory().equals(Gender.F.getImageDir())).collect(Collectors.toList());
    }

}
