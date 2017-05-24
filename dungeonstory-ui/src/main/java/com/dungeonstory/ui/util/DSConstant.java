package com.dungeonstory.ui.util;

import com.vaadin.server.VaadinService;

public final class DSConstant {
    
    private DSConstant() {
        
    }
    
    public static String getBaseDir() {
        return VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    }
    
    public static String getImageDir() {
        return getBaseDir() + "/WEB-INF/images";
    }
    
    public static String getSoundDir() {
        return getBaseDir() + "/WEB-INF/sound";
    }

}
