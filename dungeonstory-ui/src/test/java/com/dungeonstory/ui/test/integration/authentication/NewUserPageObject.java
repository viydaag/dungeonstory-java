package com.dungeonstory.ui.test.integration.authentication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.FormLayoutElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TextFieldElement;

/**
 * 
 * This class knows how to create a new user.
 *
 */
public class NewUserPageObject extends TestBenchTestCase {

    @FindBy(id = "newUserFormSave")
    private WebElement saveButton;

    @FindBy(id = "newUserFormCancel")
    private WebElement cancelButton;

    public NewUserPageObject(WebDriver driver) {
        setDriver(driver);
    }

    public void enterInfo(String username, String password, String name, String email) {

        $(TextFieldElement.class).id("newUsername").setValue(username);
        $(PasswordFieldElement.class).id("newPassword").setValue(password);
        $(TextFieldElement.class).id("newName").setValue(name);
        $(TextFieldElement.class).id("newEmail").setValue(email);
    }

    public void submit() {
        saveButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }

    public boolean isVisible() {
        return $(FormLayoutElement.class).id("newUserForm").isDisplayed();
    }

}
