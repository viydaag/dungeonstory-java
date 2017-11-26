package com.dungeonstory.ui.test.integration.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TextFieldElement;

public class NewUserIT extends IntegrationTestBase {

    private LoginPageObject loginPO;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Use the PageFactory to automatically initialize fields.
        loginPO = PageFactory.initElements(getDriver(), LoginPageObject.class);
    }

    @Test
    public void testCreateNewUser() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();
        assertTrue(newUserPO.isVisible());

        newUserPO.enterInfo("test", "test", "test", "test@gmail.com");
        newUserPO.submit();

        assertTrue(loginPO.isVisible());
    }

    @Test
    public void testBlankUsername() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();
        assertTrue(newUserPO.isVisible());

        newUserPO.enterInfo("", "test", "test", "test@gmail.com");
        newUserPO.submit();

        // Verify the notification
        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals("error", notification.getType());
        notification.close();
    }

    @Test
    public void testBlankName() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();
        assertTrue(newUserPO.isVisible());

        newUserPO.enterInfo("test", "test", "", "test@gmail.com");
        newUserPO.submit();

        // Verify the notification
        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals("error", notification.getType());
        notification.close();

        assertTrue($(TextFieldElement.class).id("newName").hasClassName("v-textfield-error"));
    }

    @Test
    public void testBlankPassword() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();
        assertTrue(newUserPO.isVisible());

        newUserPO.enterInfo("test", "", "test", "test@gmail.com");
        newUserPO.submit();

        // Verify the notification
        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals("error", notification.getType());
        notification.close();
    }

    @Test
    public void testInvalidEmail() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();
        assertTrue(newUserPO.isVisible());

        newUserPO.enterInfo("test", "test", "test", "test");
        newUserPO.submit();

        // Verify the notification
        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals("error", notification.getType());
        notification.close();
    }

}
