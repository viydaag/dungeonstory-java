package com.dungeonstory.ui.component;

import org.vaadin.peter.imagestrip.ImageStrip.Alignment;

import com.dungeonstory.backend.data.Character.Gender;

public class ImageStripFactory {

    private static ImageStripFactory instance = null;

    private static DSImageStrip maleImageStrip   = null;
    private static DSImageStrip femaleImageStrip = null;

    private ImageStripFactory() {

    }

    public static ImageStripFactory getInstance() {
        if (instance == null) {
            instance = new ImageStripFactory();
        }
        return instance;
    }

    public DSImageStrip getImageStrip(Gender gender) {
        DSImageStrip imageStrip = null;
        if (gender != null) {
            switch (gender) {
                case M:
                    if (maleImageStrip == null) {
                        maleImageStrip = new DSImageStrip(gender, Alignment.HORIZONTAL);
                    }
                    imageStrip = maleImageStrip;
                    break;

                case F:
                    if (femaleImageStrip == null) {
                        femaleImageStrip = new DSImageStrip(gender, Alignment.HORIZONTAL);
                    }
                    imageStrip = femaleImageStrip;
                    break;

                default:
                    break;
            }
        } else {
            if (maleImageStrip == null) {
                maleImageStrip = new DSImageStrip(Gender.M, Alignment.HORIZONTAL);
            }
            if (femaleImageStrip == null) {
                femaleImageStrip = new DSImageStrip(Gender.F, Alignment.HORIZONTAL);
            }
        }
        return imageStrip;
    }

}
