package com.dungeonstory.ui;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.Labels;
import com.dungeonstory.backend.repository.JPAService;
import com.dungeonstory.ui.authentication.AccessControl;
import com.dungeonstory.ui.authentication.BasicAccessControl;
import com.dungeonstory.ui.authentication.DsAccessControl;
import com.dungeonstory.ui.authentication.LoginScreen;
import com.dungeonstory.ui.event.LogoutEvent;
import com.dungeonstory.ui.event.NavigationEvent;
import com.dungeonstory.ui.factory.ImageFactory;
import com.dungeonstory.ui.i18n.DSSystemMessagesProvider;
import com.dungeonstory.ui.i18n.Translatable;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ui.Transport;
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
@Push(transport = Transport.WEBSOCKET_XHR)
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
            setContent(new LoginScreen(accessControl, this::showMainView));
        } else {
            showMainView();
        }

        setLocale(vaadinRequest.getLocale());

        ImageFactory.getInstance();
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
        accessControl.signout();

        // if other ui are opened in different browsers or tabs, refresh them as well
        for (UI ui : VaadinSession.getCurrent().getUIs()) {
            ui.access(() -> {
                ui.getPage().reload();
            });
        }
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        Labels.getInstance(locale);
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
        
        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();

            getService().setSystemMessagesProvider(new DSSystemMessagesProvider());
        }
    }
    
    @WebListener
    public static class DungeonStoryUIContextListener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            JPAService.init();
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            JPAService.close();
        }
    }
}
