package com.dungeonstory.util;

import com.dungeonstory.i18n.Messages;

public final class ViewConfigUtil {

    private ViewConfigUtil() {

    }

    public static String getDisplayName(String key) {
        Messages messages = Messages.getInstance();
        String displayName = messages.getMessage(key);
        //If the message is not found, return simply the key
        if (displayName.startsWith("!")) {
            displayName = key;
        }
        return displayName;
    }

}
