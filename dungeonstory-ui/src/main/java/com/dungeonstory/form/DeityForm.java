package com.dungeonstory.form;

import java.util.Set;

import org.vaadin.easyuploads.ImagePreviewField;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.service.AlignmentDataService;
import com.dungeonstory.backend.service.DivineDomainDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.util.field.DSSubSetSelector2;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DeityForm extends DSAbstractForm<Deity> {

    private static final long serialVersionUID = -8851250073630908019L;

    private TextField                                          name;
    private TextField                                          shortDescription;
    private TextArea                                           description;
    private ComboBox<Alignment>                                alignment;
    private DSSubSetSelector2<DivineDomain, Set<DivineDomain>> domains;
    private TextField                                          symbol;
    private ImagePreviewField                                  image;

    private DivineDomainDataService domainService    = null;
    private AlignmentDataService    alignmentService = null;

    public DeityForm() {
        super(Deity.class);
        domainService = Services.getDivineDomainService();
        alignmentService = Services.getAlignmentService();
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
        alignment = new ComboBox<Alignment>("Alignement", alignmentService.findAll());
        alignment.setEmptySelectionAllowed(false);

        image = new ImagePreviewField();
        image.setCaption("Image");
        image.setButtonCaption("Choisir une image");

        domains = new DSSubSetSelector2<>(DivineDomain.class);
        domains.setCaption("Domaines divins");
        // domains.setVisibleProperties("name");
        // domains.setColumnHeader("name", "Domaine");
        // domains.setOptions(domainService.findAll());
        domains.getGrid().addColumn(DivineDomain::getName).setCaption("Domaine").setId("name");
        domains.getGrid().setColumnOrder("name");
        domains.setItems(domainService.findAll());
        domains.setValue(null); // nothing selected
        domains.setWidth("50%");

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
