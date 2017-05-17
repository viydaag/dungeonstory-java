package com.dungeonstory.view.adventure;

import java.util.HashMap;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.AdventureDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.UserDataService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.event.ViewRemovedEvent;
import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@ViewConfig(displayName = "", uri = AdventureView.URI)
public class AdventureView extends VerticalLayout implements View {

    private static final long               serialVersionUID = 4538696780640432869L;

    public static final String              URI              = "adventure";

    private Adventure                       adventure        = null;
    private AdventureDataService            service;
    private UserDataService                 userService;
    private HashMap<Long, MessageComponent> messageComponentMap;

    private Label                           title;
    private Label                           description;
    private Button                          newMessageButton;
    private Button                          quitAdventureButton;
    private HorizontalLayout                buttonLayout;
    private VerticalLayout                  messageLayout;
    private MessageForm                     form;

    public AdventureView() {
        service = Services.getAdventureService();
        userService = Services.getUserService();
        
        buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth(100, Unit.PERCENTAGE);
        messageLayout = new VerticalLayout();
        messageLayout.setSpacing(true);
        messageComponentMap = new HashMap<>();
        form = new MessageForm();
        form.setSavedHandler(this::saveMessage);
        form.setResetHandler(this::cancelMessage);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            return;
        } else {
            adventure = service.read(Long.valueOf(event.getParameters()));
            if (adventure != null) {
                title = new Label(adventure.getName());
                description = new Label(adventure.getDescription());
                for (Message message : adventure.getMessages()) {
                    messageLayout.addComponentAsFirst(new MessageComponent(message, this));
                }

                if (CurrentUser.get().getAdventure() != null && CurrentUser.get().getAdventure().equals(adventure)) {
                    newMessageButton = new Button("Nouveau message");
                    newMessageButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
                    newMessageButton.addClickListener(click -> editMessage(new Message()));

                    quitAdventureButton = new Button("Quitter l'aventure");
                    quitAdventureButton.addStyleName(ValoTheme.BUTTON_DANGER);
                    quitAdventureButton.addClickListener(e -> {
                        ConfirmDialog.show(getUI(), "Quitter l'aventure",
                                "Êtes-vous certain de quitter cette aventure?", "Oui", "Non", new Runnable() {
                                    @Override
                                    public void run() {
                                        User user = CurrentUser.get();
                                        user.setAdventure(null);
                                        user = userService.update(user);
                                        CurrentUser.set(user);
                                        EventBus.post(new ViewRemovedEvent(AdventureView.URI));
                                        EventBus.post(new NavigationEvent(AdventureListView.URI));
                                    }
                                });
                    });

                    buttonLayout.addComponents(newMessageButton, quitAdventureButton);
                    buttonLayout.setComponentAlignment(quitAdventureButton, Alignment.MIDDLE_RIGHT);
                }

                addComponents(title, description, buttonLayout, messageLayout);
            }
        }

    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void editMessage(Message message) {
        form.setEntity(message);
        form.openInModalPopup();
        form.getPopup().setWidth(60, Unit.PERCENTAGE);
        form.getPopup().setHeight(60, Unit.PERCENTAGE);
    }

    public void saveMessage(Message message) {
        if (message.getId() == null) {
            // new message
            message.setAdventure(adventure);
            message.setCreator(CurrentUser.get());
            message.setCharacter(CurrentUser.get().getCharacter());
            adventure.addMessage(message);
            adventure = service.saveOrUpdate(adventure);

            Message savedMessage = service.getLastPersistedMessage(adventure);
            MessageComponent component = new MessageComponent(savedMessage, this);
            messageLayout.addComponentAsFirst(component);
            messageComponentMap.put(savedMessage.getId(), component);
        } else {
            // existing message
            saveExistingMessage(message);

            // get the component index and replace it with a new one
            MessageComponent component = messageComponentMap.get(message.getId());
            int componentIndex = messageLayout.getComponentIndex(component);
            MessageComponent componentReplace = new MessageComponent(message, this);
            messageLayout.removeComponent(component);
            messageLayout.addComponent(componentReplace, componentIndex);
            messageComponentMap.put(message.getId(), componentReplace);
        }

        form.closePopup();
    }

    private void saveExistingMessage(Message message) {
        int index = adventure.getMessages().indexOf(message);
        adventure.getMessages().set(index, message);
        service.saveOrUpdate(adventure);
    }

    public void cancelMessage(Message message) {
        form.setEntity(null);
        form.closePopup();
    }

    public void deleteMessage(Message message) {
        // remove message from adventure
        adventure.removeMessage(message);
        service.saveOrUpdate(adventure);

        // remove message from layout
        MessageComponent component = messageComponentMap.get(message.getId());
        messageLayout.removeComponent(component);
        messageComponentMap.remove(message.getId());
    }

    public void giveXp(Message message) {
        Character character = message.getCharacter();
        if (character != null) {
            message.setXpGiven(true);
            character.giveExperience(50);
            CharacterService.getInstance().saveOrUpdate(character);
            saveExistingMessage(message);
        }
    }

}
