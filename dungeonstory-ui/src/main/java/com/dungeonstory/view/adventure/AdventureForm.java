package com.dungeonstory.view.adventure;

import java.util.Arrays;

import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Adventure.AdventureStatus;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.form.DSAbstractForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class AdventureForm extends DSAbstractForm<Adventure> {

    private static final long           serialVersionUID = 2692854965104730175L;

    private MTextField                  name;
    private MTextArea                   description;
    private TypedSelect<Level>          challengeRating;
    private EnumSelect<AdventureStatus> status;

    private FormLayout                  layout;

    private Button                      messageButton;

    public AdventureForm() {
        super();
    }

    @Override
    protected Component createContent() {
        layout = new FormLayout();

        name = new MTextField("Titre");
        description = new MTextArea("Description");
        challengeRating = new TypedSelect<>(Level.class);
        challengeRating.setCaption("Degré de difficulté");
        challengeRating.setBeans(LevelService.getInstance().findAll());
        status = new EnumSelect<>("Statut");
        status.setBeans(Arrays.asList(AdventureStatus.values()));
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
