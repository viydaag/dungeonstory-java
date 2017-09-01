package com.dungeonstory.ui.view.adventure;

import java.io.File;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.backend.data.Message;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.util.DSConstant;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.fluent.ui.FLabel;
import com.vaadin.fluent.ui.FPanel;
import com.vaadin.fluent.ui.FVerticalLayout;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MessageComponent extends CustomComponent {

    private static final long serialVersionUID = -1705445968181419822L;

    public MessageComponent(Message message, AdventureView view) {
        FPanel panel = new FPanel().withFullWidth();

        FHorizontalLayout layout = new FHorizontalLayout().withFullWidth().withMargin(true);

        VerticalLayout infoLayout = new VerticalLayout();
        
        if (message.getCharacter() != null) {
            FLabel characterName = new FLabel(message.getCharacter().getName()).withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
            
            File imageFile = new File(DSConstant.getImageDir() + message.getCharacter().getImage());
            FileResource resource = new FileResource(imageFile);
            Image characterImage = new Image(null, resource);
            
            infoLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
            infoLayout.addComponents(characterImage, characterName);
            
        } else {
            //debug purpose
            FLabel characterName = new FLabel("test").withStyleName(DSTheme.TEXT_CENTER_ALIGNED);
            
            File imageFile = new File(DSConstant.getImageDir() + "/male/abeirL.bmp");
            FileResource resource = new FileResource(imageFile);
            Image characterImage = new Image(null, resource);
            
            infoLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
            infoLayout.addComponents(characterImage, characterName);
            
        }
        layout.addComponent(infoLayout);

        Label text = new Label(message.getText(), ContentMode.HTML);
        layout.addComponent(text);
        layout.setExpandRatio(infoLayout, 1);
        layout.setExpandRatio(text, 3);

        if (!view.getAdventure().isCancelledOrClosed()) {
            FVerticalLayout buttonLayout = new FVerticalLayout().withSpacing(true).withDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            if (CurrentUser.get().equals(message.getCreator()) || CurrentUser.get().isAdmin()) {
                // edit
                Button editMessageButton = new Button("Modifier");
                editMessageButton.addClickListener(click -> view.editMessage(message));
                buttonLayout.addComponent(editMessageButton);

                // delete
                Button deleteMessageButton = new Button("Supprimer");
                deleteMessageButton.addClickListener(click -> ConfirmDialog.show(getUI(), "Supprimer",
                        "Êtes-vous certain?", "OK", "Annuler", new Runnable() {
                            @Override
                            public void run() {
                                view.deleteMessage(message);
                            }
                        }));
                buttonLayout.addComponent(deleteMessageButton);

            }
            if (CurrentUser.get().equals(view.getAdventure().getCreator()) && !message.isXpGiven()) {
                // give XP
                Button giveXpButton = new Button("Donner XP");
                giveXpButton.addClickListener(click -> view.giveXp(message));
                giveXpButton.setDisableOnClick(true);
                buttonLayout.addComponent(giveXpButton);
            }
            layout.addComponent(buttonLayout);
            layout.setExpandRatio(buttonLayout, 1);
        }

        panel.setContent(layout);
        setCompositionRoot(panel);
    }

}
