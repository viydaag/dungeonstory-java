package com.dungeonstory.ui.test.integration.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.MenuBarElement;
import com.vaadin.testbench.elements.NotificationElement;

public class LoginIT extends IntegrationTestBase {

    private LoginPageObject loginHelper;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Use the PageFactory to automatically initialize fields.
        loginHelper = PageFactory.initElements(getDriver(), LoginPageObject.class);
    }

    @Test
    public void testLoginSuccessful() {
        loginHelper.login("admin", "admin");

        assertTrue($$(HorizontalLayoutElement.class).exists());
        assertTrue($(MenuBarElement.class).exists());
    }

    @Test
    public void testLoginFail() {
        loginHelper.login("admin", "");

        // Verify the notification
        NotificationElement notification = $(NotificationElement.class).first();
        Messages messages = Messages.getInstance(Locale.CANADA_FRENCH);
        assertEquals(messages.getMessage("loginScreen.notif.caption"), notification.getCaption());
        assertEquals(messages.getMessage("loginScreen.notif.text"), notification.getDescription());
        assertEquals("humanized", notification.getType());
        notification.close();
    }

}
