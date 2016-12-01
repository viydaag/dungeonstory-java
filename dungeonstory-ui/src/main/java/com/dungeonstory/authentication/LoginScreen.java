package com.dungeonstory.authentication;

import java.io.Serializable;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.util.DSTheme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
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
public class LoginScreen extends CssLayout {

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

    private DataService<User, Long> service;

    public LoginScreen(AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        this.service = UserService.getInstance();
        buildUI();
        buildNewUserForm();
        username.focus();
    }

    private void buildUI() {
        addStyleName(DSTheme.LOGIN_SCREEN);

        // login form, centered in the available part of the screen
        Component loginForm = buildLoginForm();

        // layout to center login form when there is sufficient screen space
        // - see the theme for how this is made responsive for various screen
        // sizes
        centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName(DSTheme.LOGIN_CENTERING_LAYOUT);
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

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

        loginForm.addComponent(username = new TextField("Utilisateur", "admin"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Mot de passe"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Mot de passe");
        CssLayout buttons = new CssLayout();
        buttons.setStyleName(DSTheme.LOGIN_BUTTON_LAYOUT);
        loginForm.addComponent(buttons);

        login = new Button("Login");
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

        forgotPassword = new Button("Forgot password?");
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        forgotPassword.addClickListener(e -> showNotification(new Notification("Hint: Try anything")));

        buttons.addComponent(forgotPassword);

        newUserButton = new Button("Nouvel utilisateur");
        newUserButton.addClickListener(e -> {
            centeringLayout.removeAllComponents();
            Component newUserForm = buildNewUserForm();
            centeringLayout.addComponent(newUserForm);
            centeringLayout.setComponentAlignment(newUserForm, Alignment.MIDDLE_CENTER);
        });
        buttons.addComponent(newUserButton);

        return loginForm;
    }

    private Component buildNewUserForm() {
        NewUserForm newUserForm = new NewUserForm();

        User user = new User();

        BeanFieldGroup<User> binder = new BeanFieldGroup<User>(User.class);
        binder.setItemDataSource(user);
        binder.bindMemberFields(newUserForm);
        binder.setBuffered(true);

        newUserFormSave = new Button("Envoyer");
        newUserFormSave.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        newUserFormCancel = new Button("Annuler");

        newUserFormSave.addClickListener(event -> {
            try {
                binder.commit();
                service.create(user);
                showLoginForm();
            } catch (CommitException e) {
                showNotification(new Notification("Certaines informations sont manquantes ou invalides.",
                        Notification.Type.ERROR_MESSAGE));
                newUserForm.setValidationVisible(true);
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
        Component loginForm = buildLoginForm();
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
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            loginListener.loginSuccessful();
        } else {
            showNotification(new Notification("Login failed", "Please check your username and password and try again.",
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }
}
