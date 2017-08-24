package com.dungeonstory.ui.test.integration.authentication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vaadin.testbench.TestBenchTestCase;
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

    private static final String URL = "http://localhost:8080/dungeonstory-ui";

    public LoginPageObject(WebDriver driver) {
        setDriver(driver);
    }

    public void login(String username, String password) {
        getDriver().get(URL);

        $(TextFieldElement.class).id("username").setValue(username);
        $(PasswordFieldElement.class).id("password").setValue(password);
        loginButton.click();
    }

}
