package com.dungeonstory.util.renderer;

import java.text.DecimalFormat;

import com.vaadin.ui.renderers.NumberRenderer;

public class ModifierRenderer extends NumberRenderer {

    private static final long serialVersionUID = -4089477044728647448L;

    public ModifierRenderer() {
        super(new DecimalFormat("+#;-#"));
    }

}
