package com.dungeonstory.form;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class FeatForm extends DSAbstractForm<Feat> {

    private static final long serialVersionUID = 5697384706401456761L;
    
    private TextField name;
    private TextArea description;
    private EnumSelect<FeatUsage> usage;

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();
        usage = new EnumSelect<FeatUsage>("Usage");
        
        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(usage);
        layout.addComponent(getToolbar());

        return layout;
    }
    
    @Override
    public String toString() {
        return "Dons";
    }

}
