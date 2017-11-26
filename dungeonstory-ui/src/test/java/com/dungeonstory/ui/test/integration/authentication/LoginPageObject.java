package com.dungeonstory.ui.test.integration.authentication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.FormLayoutElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TextFieldElement;

/**
 * 
 * This class knows how to login to the dungeonstory application.
 *
 */
public class LoginPageObject extends TestBenchTestCase {

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "newUser")
    private WebElement newUserButton;

    private static final String URL = "http://localhost:8080/dungeonstory-ui";

    public LoginPageObject(WebDriver driver) {
        setDriver(driver);
    }

    public void login(String username, String password) {
        open();
        $(TextFieldElement.class).id("username").setValue(username);
        $(PasswordFieldElement.class).id("password").setValue(password);
        loginButton.click();
    }

    public void open() {
        getDriver().get(URL);
    }

    public NewUserPageObject clickNewUser() {
        newUserButton.click();
        return PageFactory.initElements(getDriver(), NewUserPageObject.class);
    }

    public boolean isVisible() {
        return $(FormLayoutElement.class).id("loginForm").isDisplayed();
    }

}
