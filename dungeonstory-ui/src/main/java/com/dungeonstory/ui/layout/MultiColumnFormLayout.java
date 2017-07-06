package com.dungeonstory.ui.layout;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

public class MultiColumnFormLayout extends CustomComponent {

    private static final long serialVersionUID = -6945803941243321430L;
    
    HorizontalLayout hLayout;

    public MultiColumnFormLayout(int columns) {
        this(columns, null);
        setCompositionRoot(hLayout);
    }
    
    public MultiColumnFormLayout(int columns, String caption) {
        if (columns < 1) {
            throw new IllegalArgumentException("column number must be greater than 1");
        }
        hLayout = new HorizontalLayout();
        hLayout.setWidth(100, Unit.PERCENTAGE);
        for (int i = 0; i < columns; i++) {
            FormLayout form = new FormLayout();
            hLayout.addComponent(form, i);
        }
        
        if (caption != null) {
            hLayout.setMargin(new MarginInfo(false, true));
            Panel panel = new Panel(caption, hLayout);
            setCompositionRoot(panel);
        } else {
            setCompositionRoot(hLayout);
        }
        
    }
    
    public void addComponent(int column, Component c) {
        if (column >= hLayout.getComponentCount()) {
            throw new IndexOutOfBoundsException("column index must be lower than the number of form layouts");
        } else if (column < 0) {
            throw new IndexOutOfBoundsException("column index must be a positive number");
        }
        
        FormLayout fLayout = (FormLayout) hLayout.getComponent(column);
        fLayout.addComponent(c);
    }
    
    public void addComponents(int column, Component... components) {
        if (column >= hLayout.getComponentCount()) {
            throw new IndexOutOfBoundsException("column index must be lower than the number of form layouts");
        } else if (column < 0) {
            throw new IndexOutOfBoundsException("column index must be a positive number");
        }
        
        FormLayout fLayout = (FormLayout) hLayout.getComponent(column);
        fLayout.addComponents(components);
    }
    
    public void setMargin(boolean enabled) {
        hLayout.setMargin(enabled);
    }
    
    public void setSpacing(boolean enabled) {
        hLayout.setSpacing(enabled);
    }

}
