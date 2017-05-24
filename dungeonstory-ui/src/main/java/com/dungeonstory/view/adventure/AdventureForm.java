package com.dungeonstory.view.adventure;

import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Adventure.AdventureStatus;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.component.EnumComboBox;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class AdventureForm extends DSAbstractForm<Adventure> {

    private static final long serialVersionUID = 2692854965104730175L;

    private MTextField                    name;
    private DSTextArea                    description;
    private ComboBox<Level>               challengeRating;
    private EnumComboBox<AdventureStatus> status;

    private FormLayout layout;

    private Button messageButton;

    public AdventureForm() {
        super(Adventure.class);
    }

    @Override
    protected Component createContent() {
        layout = new FormLayout();

        name = new MTextField("Titre").withWidth("50%");
        description = new DSTextArea("Description").withFullWidth();
        challengeRating = new ComboBox<>("Degré de difficulté");
        challengeRating.setItems(Services.getLevelService().findAll());
        status = new EnumComboBox<>(AdventureStatus.class, "Statut");
        layout.addComponents(name, description, challengeRating, status);
        layout.addComponent(getToolbar());

        messageButton = new Button("Messages", event -> {
            EventBus.post(new NavigationEvent(AdventureView.URI + "/" + getEntity().getId()));
        });
        layout.addComponent(messageButton);

        return layout;
    }

    @Override
    public String toString() {
        return "Aventures";
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        if (getEntity() != null) {
            status.setVisible(getEntity().getId() != null);
            messageButton.setVisible(getEntity().getId() != null);
        }
    }

}
