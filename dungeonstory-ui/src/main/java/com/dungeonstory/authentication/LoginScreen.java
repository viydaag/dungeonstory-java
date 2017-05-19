package com.dungeonstory.authentication;

import java.io.Serializable;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.i18n.LanguageSelector;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.i18n.Translatable;
import com.dungeonstory.util.DSTheme;
import com.vaadin.data.BeanValidationBinder;
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
    private Button         login;
    private Button         forgotPassword;
    private LoginListener  loginListener;
    private AccessControl  accessControl;
    private VerticalLayout centeringLayout;

    private Button newUserButton;
    private Button newUserFormSave;
    private Button newUserFormCancel;

    private Component loginForm;
    private Component newUserForm;

    private DataService<User, Long> service;

    public LoginScreen(AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        this.service = UserService.getInstance();
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

        username = new TextField();
        username.setValue("admin");
        loginForm.addComponent(username);
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField());
        password.setWidth(15, Unit.EM);
        CssLayout buttons = new CssLayout();
        buttons.setStyleName(DSTheme.LOGIN_BUTTON_LAYOUT);
        loginForm.addComponent(buttons);

        login = new Button();
        login.setDisableOnClick(true);
        login.addClickListener(e -> {
            try {
                login();
            } finally {
                login.setEnabled(true);
            }
        });
        buttons.addComponent(login);

        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        forgotPassword = new Button();
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        //        forgotPassword.addClickListener(e -> showNotification(new Notification("Hint: Try anything")));

        buttons.addComponent(forgotPassword);

        newUserButton = new Button();
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

        //        BeanFieldGroup<User> binder = new BeanFieldGroup<User>(User.class);
        //        binder.setItemDataSource(user);
        //        binder.bindMemberFields(newUserForm);
        //        binder.setBuffered(true);
        BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(newUserForm);
        binder.readBean(user);

        newUserFormSave = new Button();
        newUserFormSave.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        newUserFormCancel = new Button();

        newUserFormSave.addClickListener(event -> {
            try {
                binder.writeBean(user);
                service.create(user);
                showLoginForm();
                //            } catch (CommitException e) {
                //                Messages messages = Messages.getInstance();
                //                showNotification(new Notification(messages.getMessage("newUserForm.notif.text"), Notification.Type.ERROR_MESSAGE));
                ////                newUserForm.setValidationVisible(true);
            } catch (ValidationException e) {
                Messages messages = Messages.getInstance();
                showNotification(new Notification(messages.getMessage("newUserForm.notif.text"),
                        Notification.Type.ERROR_MESSAGE));
            }
        });

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
        login.setCaption(messages.getMessage("loginForm.button.login"));
        forgotPassword.setCaption(messages.getMessage("loginForm.button.forgotpassword"));
        newUserButton.setCaption(messages.getMessage("loginScreen.button.newUser"));
        newUserFormSave.setCaption(messages.getMessage("button.send"));
        newUserFormCancel.setCaption(messages.getMessage("button.cancel"));
    }
}
