package com.dungeonstory.ui.view.admin.form;

import java.util.Set;

import org.vaadin.easyuploads.ImagePreviewField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.field.SubSetSelector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DeityForm extends DSAbstractForm<Deity> {

    private static final long serialVersionUID = -8851250073630908019L;

    private TextField                                                     name;
    private TextField                                                     shortDescription;
    private TextArea                                                      description;
    private ComboBox<Alignment>                                           alignment;
    private SubSetSelector<ClassSpecialization, Set<ClassSpecialization>> domains;
    private TextField                                                     symbol;
    private ImagePreviewField                                             image;

    public DeityForm() {
        super(Deity.class);
    }

    @Override
    public String toString() {
        return "Dieux";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom").withWidth("50%");
        shortDescription = new MTextField("Description courte").withFullWidth();
        description = new DSTextArea("Description").withFullWidth();
        symbol = new MTextField("Symbole").withWidth("50%");
        alignment = new ComboBox<Alignment>("Alignement", Services.getAlignmentService().findAll());
        alignment.setEmptySelectionAllowed(false);

        image = new ImagePreviewField();
        image.setCaption("Image");
        image.setButtonCaption("Choisir une image");

        domains = new SubSetSelector<>(ClassSpecialization.class);
        domains.setCaption("Domaines divins");
        domains.getGrid().addColumn(ClassSpecialization::getName).setCaption("Domaine").setId("name");
        domains.getGrid().setColumnOrder("name");
        domains.setItems(Services.getClassSpecializationService().findAllDivineDomainSpecializations());
        domains.setValue(null); // nothing selected
        domains.setWidth("50%");
        domains.getComboBox().setWidth("40%");

        layout.addComponent(name);
        layout.addComponent(shortDescription);
        layout.addComponent(description);
        layout.addComponent(alignment);
        layout.addComponent(domains);
        layout.addComponent(symbol);
        layout.addComponent(image);
        layout.addComponent(getToolbar());

        return layout;
    }

}
