package com.dungeonstory.ui.component;

import java.io.File;

import com.vaadin.server.Resource;

public class DSImage {

    private Resource resource;
    private String   directory;
    private String   filename;
    private int      index;

    public DSImage(Resource resource, String directory, String filename) {
        super();
        this.setDirectory(directory);
        this.setFilename(filename);
        this.setResource(resource);
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRelativePath() {
        return File.separator + directory + File.separator + filename;
    }

    @Override
    public String toString() {
        return "DSImage : " + index + " = " + getRelativePath();
    }


}
