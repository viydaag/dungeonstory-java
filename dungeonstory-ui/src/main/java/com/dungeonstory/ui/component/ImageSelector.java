package com.dungeonstory.ui.component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Component to display images.
 * If selectable, the image can be selected and value change listener is available.
 * The number of displayed images, height and width can be set programatically.
 *
 */
public class ImageSelector extends CustomField<DSImage> {

    private static final long serialVersionUID = -7097346215523099106L;

    private HorizontalLayout layout;
    private Button           leftButton;
    private Button           rightButton;
    private HorizontalLayout imageLayout;

    //All image resources
    private final List<DSImage> images;

    //List of vaadin images
    private final List<Image> loadedImages;

    //Map of index attached to visible images
    private final Map<Integer, Image> visibleImages;

    //The image selected by user.
    private DSImage selectedImage;

    /**
     * Number of images that are allowed to be visible simultaneously, if value
     * is negative, there is no limit
     */
    private int maxAllowed = -1;

    //Maximum width of the image
    private int imageMaxWidth;

    //Maximum height of the image
    private int imageMaxHeight;

    //Index of first image displayed in the selector.
    private int index = 0;

    //Is selector selectable.
    private boolean selectable = true;

    //Panel containing buttons and images
    private Panel mainPanel;

    public ImageSelector() {
        images = new LinkedList<DSImage>();
        visibleImages = new HashMap<Integer, Image>();
        loadedImages = new LinkedList<Image>();

        mainPanel = new Panel();
        imageLayout = new HorizontalLayout();
        layout = new HorizontalLayout();

        imageMaxWidth = 110;
        imageMaxHeight = 110;

        leftButton = new Button("<", e -> scrollLeft());
        rightButton = new Button(">", e -> scrollRight());
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
    }

    @Override
    protected Component initContent() {

        mainPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        imageLayout.setSpacing(true);
        imageLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        layout.setSpacing(true);
        layout.addComponents(leftButton, imageLayout, rightButton);
        layout.setComponentAlignment(rightButton, Alignment.MIDDLE_RIGHT);
        layout.setComponentAlignment(leftButton, Alignment.MIDDLE_LEFT);
        layout.setComponentAlignment(imageLayout, Alignment.MIDDLE_CENTER);
        mainPanel.setContent(layout);

        return mainPanel;
    }

    @Override
    public Class<? extends DSImage> getType() {
        return DSImage.class;
    }

    @Override
    public void setWidth(float width, Unit unit) {
        if (layout != null) {
            layout.setWidth(width, unit);
        } else {
            super.setWidth(width, unit);
        }
    }

    @Override
    public void setWidth(String width) {
        if (layout != null) {
            layout.setWidth(width);
        } else {
            super.setWidth(width);
        }
    }

    public List<DSImage> getImages() {
        return new ArrayList<>(images);
    }

    public DSImage addImage(File imageFile) {
        return addImage(new FileResource(imageFile));
    }

    public DSImage addImage(FileResource fileResource) {
        String fullPath = fileResource.getSourceFile().getAbsolutePath();
        int lastSlash = fullPath.lastIndexOf(File.separator);
        int second = fullPath.lastIndexOf(File.separator, lastSlash - 1);
        String directory = fullPath.substring(second + 1, lastSlash);
        String filename = fullPath.substring(lastSlash + 1);
        DSImage image = new DSImage(fileResource, directory, filename);
        addImage(image);
        return image;
    }

    public void addImage(DSImage image) {
        image.setIndex(images.size());
        images.add(image);
        Image loadedImage = new Image();
        loadedImage.setSource(image.getResource());

        if (loadedImage.getWidth() > imageMaxWidth) {
            loadedImage.setWidth(imageMaxWidth, Unit.PIXELS);
        }
        if (loadedImage.getHeight() > imageMaxHeight) {
            loadedImage.setHeight(imageMaxHeight, Unit.PIXELS);
        }

        loadedImage.setData(image.getIndex());
        loadedImage.addClickListener(event -> {
            if (isSelectable()) {
                selectedImage = images.get((int) loadedImage.getData());
//                Notification.show(selectedImage.toString(), Type.HUMANIZED_MESSAGE);
                setValue(selectedImage);
            }
        });
        loadedImages.add(loadedImage);

        if (visibleImages.size() < maxAllowed || maxAllowed < 0) {
            addVisibleImage(image.getIndex(), loadedImage);
        }
    }

    public void addImages(Collection<DSImage> images) {
        images.forEach(this::addImage);
    }

    public void addImageFiles(Collection<File> files) {
        images.forEach(this::addImage);
    }

    public void scrollRight() {
        if (index < (loadedImages.size() - maxAllowed)) {
            //remove first image
            Image removedImage = getVisibleImage(index);
            imageLayout.removeComponent(removedImage);
            visibleImages.remove(removedImage.getData());

            //            CssAnimation translate = new CssAnimation(removedImage, new Css().translateX("-150px")).duration(500);
            //            CssAnimation opacity = new CssAnimation(removedImage, new Css().opacity(0)).duration(500);
            //            Animator.animate(translate.sendEndEvent());
            //            Animator.animate(opacity);

            //                        animator.addListener(endEvent -> {
            //                            CssAnimation animation = endEvent.getAnimation();
            //                            if (animation.equals(translate)) {
            //                                imageLayout.removeComponent(removedImage);
            //                                visibleImages.remove(removedImage.getData());
            //                            }
            //                        });

            //            for (Image image : visibleImages.values()) {
            //                Animator.animate(image, new Css().translateX("-150px")).duration(500);
            //            }

            //add the next image
            Image addedImage = loadedImages.get(index + maxAllowed);
            addVisibleImage((Integer) addedImage.getData(), addedImage);
            index++;
        }
    }

    public void scrollLeft() {
        if (index > 0) {
            //remove last image
            Image removedImage = getVisibleImage(index + maxAllowed - 1);
            imageLayout.removeComponent(removedImage);
            visibleImages.remove(removedImage.getData());

            //add previous image
            Image addedImage = loadedImages.get(index - 1);
            addVisibleImage((Integer) addedImage.getData(), addedImage, true);
            index--;
        }
    }

    public void scrollTo(int index) {
        if (index >= 0 && maxAllowed > 0) {
            visibleImages.clear();
            imageLayout.removeAllComponents();

            if (index > (loadedImages.size() - maxAllowed)) {
                index = loadedImages.size() - maxAllowed;
            }

            for (int i = index; i < (index + maxAllowed); i++) {
                Image image = loadedImages.get(i);
                addVisibleImage(i, image);
                imageLayout.addComponent(image);
            }

            this.index = index;
        }
    }

    @Override
    public void setValue(DSImage newFieldValue) throws com.vaadin.data.Property.ReadOnlyException, ConversionException {
        super.setValue(newFieldValue);
        if (isSelectable()) {
            visibleImages.values().stream().forEach(image -> image.removeStyleName("image-bordered-selected"));
            if (newFieldValue != null) {
                Image selected = loadedImages.get(newFieldValue.getIndex());
                selected.addStyleName("image-bordered-selected");
            }
        }
    }

    public void setValueWithScroll(int index) {
        setValue(images.get(index));
        scrollTo(index);
    }

    public void setValueWithScroll(DSImage image) {
        setValue(image);
        scrollTo(image.getIndex());
    }

    private void addVisibleImage(Integer key, Image image) {
        addVisibleImage(key, image, false);
    }

    private void addVisibleImage(Integer key, Image image, boolean first) {
        visibleImages.put(key, image);
        if (first) {
            imageLayout.addComponentAsFirst(image);
        } else {
            imageLayout.addComponent(image);
        }
    }

    private Image getVisibleImage(Integer key) {
        return visibleImages.get(key);
    }

    public DSImage getSelectedImage() {
        return selectedImage;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
        leftButton.setEnabled(maxAllowed > 0);
        rightButton.setEnabled(maxAllowed > 0);
    }

    public int getImageMaxWidth() {
        return imageMaxWidth;
    }

    public void setImageMaxWidth(int imageMaxWidth) {
        this.imageMaxWidth = imageMaxWidth;
    }

    public int getImageMaxHeight() {
        return imageMaxHeight;
    }

    public void setImageMaxHeight(int imageMaxHeight) {
        this.imageMaxHeight = imageMaxHeight;
        mainPanel.setHeight(imageMaxHeight + 60, Unit.PIXELS);
        layout.setHeight(imageMaxHeight + 40, Unit.PIXELS);
        imageLayout.setHeight(imageMaxHeight + 25, Unit.PIXELS);
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public void clear() {
        super.clear();
        images.clear();
        loadedImages.clear();
        visibleImages.clear();
        imageLayout.removeAllComponents();
        index = 0;
    }

}
