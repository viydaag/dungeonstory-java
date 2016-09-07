package com.dungeonstory.form;

import java.util.List;

import org.vaadin.easyuploads.ImagePreviewField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.DivineDomainService;
import com.dungeonstory.backend.service.mock.MockAlignmentService;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class DeityForm extends DSAbstractForm<Deity> {

    private static final long serialVersionUID = -8851250073630908019L;

    private TextField                      name;
    private TextArea                       description;
    private TypedSelect<Alignment>         alignment;
    private DSSubSetSelector<DivineDomain> domains;
    private TextField                      symbol;
    private ImagePreviewField              image;

    private DataService<DivineDomain, Long> domainService    = null;
    private DataService<Alignment, Long>    alignmentService = null;

    public DeityForm() {
        super();
        if (Configuration.getInstance().isMock()) {
            //TODO
            //            domainService = MockDivineDomainService.getInstance();
            alignmentService = MockAlignmentService.getInstance();
        } else {
            domainService = DivineDomainService.getInstance();
            alignmentService = AlignmentService.getInstance();
        }
    }

    @Override
    public String toString() {
        return "Dieux";
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        name = new MTextField("Nom");
        description = new MTextArea("Description").withFullWidth();
        symbol = new MTextField("Symbole").withWidth("50%");
        alignment = new TypedSelect<Alignment>("Alignement", alignmentService.findAll());

        image = new ImagePreviewField();
        image.setCaption("Image");
        image.setButtonCaption("Choisir une image");

        domains = new DSSubSetSelector<DivineDomain>(DivineDomain.class);
        domains.setCaption("Domaines divins");
        domains.setVisibleProperties("name");
        domains.setColumnHeader("name", "Domaine");
        domains.setOptions((List<DivineDomain>) domainService.findAll());
        domains.setValue(null); // nothing selected
        domains.setWidth("50%");

        layout.addComponent(name);
        layout.addComponent(description);
        layout.addComponent(alignment);
        layout.addComponent(domains);
        layout.addComponent(symbol);
        layout.addComponent(image);
        layout.addComponent(getToolbar());

        return layout;
    }

}
