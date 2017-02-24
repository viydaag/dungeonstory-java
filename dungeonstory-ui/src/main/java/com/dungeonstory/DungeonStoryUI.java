package com.dungeonstory;

import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.dungeonstory.authentication.AccessControl;
import com.dungeonstory.authentication.BasicAccessControl;
import com.dungeonstory.authentication.DsAccessControl;
import com.dungeonstory.authentication.LoginScreen;
import com.dungeonstory.authentication.LoginScreen.LoginListener;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.event.LogoutEvent;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.i18n.Translatable;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 *
 * The @Viewport annotation configures the viewport meta tags appropriately on
 * mobile devices. Instead of device based scaling (default), using responsive
 * layouts.
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("dungeonstory")
public class DungeonStoryUI extends UI {

    private static final long serialVersionUID = -5249908238351407763L;

    private EventBus      eventBus;
    private AccessControl accessControl;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        getPage().setTitle("DungeonStory");

        if (Configuration.getInstance().isMock()) {
            accessControl = new BasicAccessControl();
        } else {
            accessControl = new DsAccessControl();
        }

        setupEventBus();

        if (!accessControl.isUserSignedIn()) {
            setContent(new LoginScreen(accessControl, new LoginListener() {

                private static final long serialVersionUID = 917942908442238610L;

                @Override
                public void loginSuccessful() {
                    showMainView();
                }
            }));
        } else {
            showMainView();
        }

        setLocale(vaadinRequest.getLocale());
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(DungeonStoryUI.this));
        updateMessageStrings(getContent());
    }

    public static DungeonStoryUI get() {
        return (DungeonStoryUI) UI.getCurrent();
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

    private void setupEventBus() {
        eventBus = new EventBus((throwable, subscriberExceptionContext) -> {
            // log error
            throwable.printStackTrace();
        });
        eventBus.register(this);
    }

    public static DungeonStoryUI getCurrent() {
        return (DungeonStoryUI) UI.getCurrent();
    }

    public static EventBus getEventBus() {
        return getCurrent().eventBus;
    }

    @Subscribe
    public void navigateTo(NavigationEvent view) {
        getNavigator().navigateTo(view.getViewName());
    }

    @Subscribe
    public void logout(LogoutEvent logoutEvent) {
        // Don't invalidate the underlying HTTP session if you are using it for something else
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        updateMessageStrings(getContent());
    }

    private void updateMessageStrings(Component component) {
        if (component instanceof Translatable) {
            ((Translatable) component).updateMessageStrings();
        }
        if (component instanceof HasComponents) {
            ((HasComponents) component).iterator().forEachRemaining(this::updateMessageStrings);
        }
    }

    @WebServlet(urlPatterns = "/*", name = "DungeonStoryUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DungeonStoryUI.class, productionMode = false)
    public static class DungeonStoryUIServlet extends VaadinServlet {

        private static final long serialVersionUID = 4139296258602945675L;
    }
}
