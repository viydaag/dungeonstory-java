package com.dungeonstory.ui.component;

import java.util.LinkedHashSet;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.server.Resource;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Button;

public class ConfirmButton
        extends Button {

    private static final long serialVersionUID = -1525902698603676276L;

    private String confirmWindowCaption;
    private String confirmationText = "Are you sure?";
    private String okCaption        = "OK";
    private String cancelCaption    = "Cancel";

    private String confirmWindowOkButtonStyle;

    public ConfirmButton() {
        super();
    }

    public ConfirmButton(String buttonCaption, String confirmationText, ClickListener listener) {
        super(buttonCaption, listener);
        this.confirmationText = confirmationText;
    }

    public ConfirmButton(Resource icon, String confirmationText, ClickListener listener) {
        super(icon);
        addClickListener(listener);
        this.confirmationText = confirmationText;
    }

    public ConfirmButton(Resource icon, String buttonCaption, String confirmationText, ClickListener listener) {
        super(buttonCaption, icon);
        addClickListener(listener);
        this.confirmationText = confirmationText;
    }

    // click listeners are for the confirmation dialog button
    private LinkedHashSet<ClickListener> clickListeners;

    public ConfirmButton withClickListener(ClickListener listener) {
        if (clickListeners == null) {
            clickListeners = new LinkedHashSet<>();
        }
        clickListeners.add(listener);
        return this;
    }

    @Override
    public void removeClickListener(ClickListener listener) {
        if (clickListeners != null) {
            clickListeners.remove(listener);
        }
    }

    @Override
    protected void fireClick(final MouseEventDetails details) {
        ConfirmDialog dialog = ConfirmDialog.show(getUI(), getConfirmWindowCaption(), getConfirmationText(), getOkCaption(),
                getCancelCaption(), new Runnable() {
                    @Override
                    public void run() {
                        doFireClickListener(details);
                    }
                });

        dialog.getOkButton().addStyleName(confirmWindowOkButtonStyle);
    }

    @Override
    protected void fireClick() {
        fireClick(null);
    }

    @Override
    public void setClickShortcut(int keyCode, int... modifiers) {
        super.setClickShortcut(keyCode, modifiers); //To change body of generated methods, choose Tools | Templates.
    }

    protected void doFireClickListener(final MouseEventDetails details) {
        ConfirmButton.super.fireClick(details);
        if (clickListeners != null) {
            final Button.ClickListener[] array = clickListeners.toArray(new Button.ClickListener[clickListeners.size()]);
            for (Button.ClickListener l : array) {
                l.buttonClick(null);
            }
        }
    }

    public String getConfirmWindowCaption() {
        return confirmWindowCaption;
    }

    public ConfirmButton setConfirmWindowCaption(String confirmWindowCaption) {
        this.confirmWindowCaption = confirmWindowCaption;
        return this;
    }

    public String getConfirmationText() {
        return confirmationText;
    }

    public ConfirmButton setConfirmationText(String confirmationText) {
        this.confirmationText = confirmationText;
        return this;
    }

    public String getOkCaption() {
        return okCaption;
    }

    public ConfirmButton setOkCaption(String okCaption) {
        this.okCaption = okCaption;
        return this;
    }

    public String getCancelCaption() {
        return cancelCaption;
    }

    public ConfirmButton setCancelCaption(String cancelCaption) {
        this.cancelCaption = cancelCaption;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ConfirmButton> T withI18NCaption(String okCaption, String cancelCaption) {
        this.okCaption = okCaption;
        this.cancelCaption = cancelCaption;
        return (T) this;
    }

    public void setConfirmWindowOkButtonStyle(String confirmWindowOkButtonStyle) {
        this.confirmWindowOkButtonStyle = confirmWindowOkButtonStyle;
    }

}
