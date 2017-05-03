package com.dungeonstory.util.renderer;


import com.vaadin.ui.renderers.HtmlRenderer;

import elemental.json.JsonValue;

public class ByteArrayImageRenderer extends HtmlRenderer {
    
    private static final long serialVersionUID = 3813268410665010692L;

    @Override
    public JsonValue encode(String value) {
        String newValue = null;
        if (value != null) {
            newValue = "<img src=\"data:image/png;base64," + value + "\" alt=\"\" />";
        }
        return super.encode(newValue);
    }

}
