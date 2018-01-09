package com.dungeonstory.ui.test.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.dungeonstory.ui.test.integration.authentication.LoginPageObject;
import com.dungeonstory.ui.test.integration.authentication.NewUserPageObject;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CustomFieldElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.MenuBarElement;
import com.vaadin.testbench.elements.TextFieldElement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserIT
        extends IntegrationTestBase {

    private LoginPageObject loginPO;
    private Messages        messages;

    private static int random = getRandomNumberInRange(1, 100);

    @Override
    public void setUp() throws Exception {
        super.setUp();

        messages = Messages.getInstance(Locale.getDefault());

        loginPO = PageFactory.initElements(getDriver(), LoginPageObject.class);
    }

    @Test
    public void test1CreateUser() {
        loginPO.open();

        NewUserPageObject newUserPO = loginPO.clickNewUser();

        newUserPO.enterInfo("test" + random, "test", "test", "test@gmail.com");
        newUserPO.submit();
    }

    @Test
    public void test2UserView() throws InterruptedException {
        loginPO.login("test" + random, "test");

        MenuBarElement menuBar = $(MenuBarElement.class).first();
        menuBar.clickItem(messages.getMessage("userView.caption"));
        
        LabelElement usernameLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.username"))
                                                                .$$(LabelElement.class)
                                                                .first();
        LabelElement nameLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.name")).$$(LabelElement.class).first();
        LabelElement emailLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.email"))
                                                             .$$(LabelElement.class)
                                                             .first();

        assertEquals("test" + random, usernameLabel.getText());
        assertEquals("test", nameLabel.getText());
        assertEquals("test@gmail.com", emailLabel.getText());

        ButtonElement editButton = $(ButtonElement.class).caption("Modifier").first();
        editButton.click();

        assertFalse(editButton.isEnabled());
        TextFieldElement nameTextField = $(TextFieldElement.class).caption(messages.getMessage("userView.label.name")).first();
        TextFieldElement emailTextField = $(TextFieldElement.class).caption(messages.getMessage("userView.label.email")).first();
        assertEquals("test", nameTextField.getValue());
        assertEquals("test@gmail.com", emailTextField.getValue());

        ButtonElement saveButton = $(ButtonElement.class).caption(messages.getMessage("button.save")).first();
        ButtonElement cancelButton = $(ButtonElement.class).caption(messages.getMessage("button.cancel")).first();
        Thread.sleep(1000);
        //        assertFalse(saveButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        cancelButton.click();

        //nothing has changed
        nameLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.name"))
                                               .$$(LabelElement.class)
                                               .first();
        emailLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.email"))
                                                .$$(LabelElement.class)
                                                .first();

        assertEquals("test", nameLabel.getText());
        assertEquals("test@gmail.com", emailLabel.getText());
        assertTrue(editButton.isEnabled());

        editButton.click();
        saveButton = $(ButtonElement.class).caption(messages.getMessage("button.save")).first();
        cancelButton = $(ButtonElement.class).caption(messages.getMessage("button.cancel")).first();
        nameTextField = $(TextFieldElement.class).caption(messages.getMessage("userView.label.name")).first();
        emailTextField = $(TextFieldElement.class).caption(messages.getMessage("userView.label.email")).first();

        nameTextField.setValue("test" + random);
        saveButton.click();

        assertTrue(editButton.isEnabled());
        nameLabel = $(CustomFieldElement.class).caption(messages.getMessage("userView.label.name"))
                                               .$$(LabelElement.class)
                                               .first();
        assertEquals("test" + random, nameLabel.getText());

    }

}
