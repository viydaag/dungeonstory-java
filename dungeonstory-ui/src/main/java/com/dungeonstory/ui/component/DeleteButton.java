package com.dungeonstory.ui.component;

import com.vaadin.server.Resource;

public class DeleteButton
        extends ConfirmButton {

    private static final long serialVersionUID = -255825531511724576L;

    public DeleteButton() {
        setupDeleteButton();
    }

    public DeleteButton(String caption) {
        setCaption(caption);
        setupDeleteButton();
    }

    public DeleteButton(String caption, String confirmationText, ClickListener listener) {
        super(caption, confirmationText, listener);
        setupDeleteButton();
    }

    public DeleteButton(Resource icon, String caption, String confirmationText, ClickListener listener) {
        super(icon, caption, confirmationText, listener);
        setupDeleteButton();
    }

    private void setupDeleteButton() {
        setStyleName("danger default");
        setConfirmWindowOkButtonStyle("danger default");
    }

}
