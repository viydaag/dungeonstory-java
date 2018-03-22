package com.dungeonstory.ui.authentication;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dungeonstory.backend.Labels;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.i18n.LanguageSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.i18n.Translatable;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout implements Translatable {

    private static final long serialVersionUID = 3185061373532915991L;

    private TextField      username;
    private PasswordField  password;
    private Button         loginButton;
    private Button         forgotPassword;
    private LoginListener  loginListener;
    private AccessControl  accessControl;
    private VerticalLayout centeringLayout;

    private Button newUserButton;
    private Button newUserFormSave;
    private Button newUserFormCancel;

    private Component loginForm;
    private Component newUserForm;

    public LoginScreen(AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        buildUI();
        username.focus();
    }

    private void buildUI() {
        addStyleName(DSTheme.LOGIN_SCREEN);

        // login form, centered in the available part of the screen
        loginForm = buildLoginForm();

        newUserForm = buildNewUserForm();

        // layout to center login form when there is sufficient screen space
        // - see the theme for how this is made responsive for various screen sizes
        centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName(DSTheme.LOGIN_CENTERING_LAYOUT);
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        centeringLayout.addComponentAttachListener(event -> {
            if (event.getAttachedComponent() instanceof Translatable) {
                ((Translatable) event.getAttachedComponent()).updateMessageStrings();
            }
        });

        // information text about logging in
        CssLayout loginInformation = buildLoginInformation();

        addComponent(centeringLayout);
        addComponent(loginInformation);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName(DSTheme.LOGIN_FORM);
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);
        loginForm.setId("loginForm");

        username = new TextField();
        username.setValue("admin");
        username.setId("username");
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(username);

        password = new PasswordField();
        password.setId("password");
        password.setWidth(15, Unit.EM);
        loginForm.addComponent(password);

        CssLayout buttons = new CssLayout();
        buttons.setStyleName(DSTheme.LOGIN_BUTTON_LAYOUT);
        loginForm.addComponent(buttons);

        loginButton = new Button();
        loginButton.setId("login");
        loginButton.setDisableOnClick(true);
        loginButton.addClickListener(e -> {
            try {
                login();
            } finally {
                loginButton.setEnabled(true);
            }
        });
        buttons.addComponent(loginButton);

        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        forgotPassword = new Button();
        forgotPassword.setId("forgotPassword");
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        //        forgotPassword.addClickListener(e -> showNotification(new Notification("Hint: Try anything")));

        buttons.addComponent(forgotPassword);

        newUserButton = new Button();
        newUserButton.setId("newUser");
        newUserButton.addClickListener(e -> {
            centeringLayout.removeAllComponents();
            centeringLayout.addComponent(newUserForm);
            centeringLayout.setComponentAlignment(newUserForm, Alignment.MIDDLE_CENTER);
        });
        buttons.addComponent(newUserButton);

        return loginForm;
    }

    private Component buildNewUserForm() {
        NewUserForm newUserForm = new NewUserForm();

        User user = new User();

        newUserForm.getBinder().readBean(user);

        newUserFormSave = new Button();
        newUserFormSave.setId("newUserFormSave");
        newUserFormSave.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        newUserFormSave.addClickListener(event -> {
            try {
                newUserForm.getBinder().writeBean(user);
                Services.getUserService().create(user);
                newUserForm.clear();
                showLoginForm();
            } catch (ValidationException e) {
                Messages messages = Messages.getInstance();
                String message = messages.getMessage("newUserForm.notif.text") + "\n\n";
                message += newUserForm.getBinder().validate().getFieldValidationErrors().stream().map(BindingValidationStatus::getMessage)
                        .filter(Optional::isPresent).map(Optional::get)
                        .collect(Collectors.joining("\n"));
                showNotification(new Notification(message, Notification.Type.ERROR_MESSAGE));

            }
        });

        newUserFormCancel = new Button();
        newUserFormCancel.setId("newUserFormCancel");
        newUserFormCancel.addClickListener(event -> {
            showLoginForm();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponents(newUserFormSave, newUserFormCancel);

        newUserForm.addComponents(buttons);

        return newUserForm;
    }

    private void showLoginForm() {
        centeringLayout.removeAllComponents();
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label(
                "<h1>Login Information</h1>"
                        + "Log in as &quot;admin&quot; to have full access. Log in with any other username to have read-only access. For all users, any password is fine",
                ContentMode.HTML);
        loginInfoText.setWidth("270px");
        loginInformation.addComponent(loginInfoText);
        LanguageSelector language = new LanguageSelector();
        language.addValueChangeListener(e -> Labels.getInstance(e.getValue()));
        loginInformation.addComponent(language);
        return loginInformation;
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            loginListener.loginSuccessful();
        } else {
            Messages messages = Messages.getInstance();
            showNotification(new Notification(messages.getMessage("loginScreen.notif.caption"), messages.getMessage("loginScreen.notif.text"),
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        username.setCaption(messages.getMessage("loginForm.textfield.user"));
        password.setCaption(messages.getMessage("loginForm.textfield.password"));
        password.setDescription(messages.getMessage("loginForm.textfield.password"));
        loginButton.setCaption(messages.getMessage("loginForm.button.login"));
        forgotPassword.setCaption(messages.getMessage("loginForm.button.forgotpassword"));
        newUserButton.setCaption(messages.getMessage("loginScreen.button.newUser"));
        newUserFormSave.setCaption(messages.getMessage("button.send"));
        newUserFormCancel.setCaption(messages.getMessage("button.cancel"));
    }
}
