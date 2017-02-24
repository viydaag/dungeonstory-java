package com.dungeonstory.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class ImageFilter implements FilenameFilter {

    private static final String[] IMG_EXTENSIONS = new String[] { "gif", "png", "bmp", "jpg" };
    private List<String>  extensions;

    public ImageFilter() {
        super();
        extensions = Arrays.asList(IMG_EXTENSIONS);
    }

    @Override
    public boolean accept(File dir, String name) {
        for (final String ext : extensions) {
            if (name.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    public void setExtension(String... extension) {
        extensions.clear();
        extensions.addAll(Arrays.asList(extension));
    }

}