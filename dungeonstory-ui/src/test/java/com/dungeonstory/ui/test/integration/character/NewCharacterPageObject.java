package com.dungeonstory.ui.test.integration.character;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.CustomFieldElement;

/**
 * 
 * This class knows how to create a new character for dungeonstory application.
 *
 */
public class NewCharacterPageObject extends TestBenchTestCase {

    @FindBy(id = "cancelButton")
    private WebElement cancelButton;

    @FindBy(id = "nextButton")
    private WebElement nextButton;

    @FindBy(id = "backButton")
    private WebElement backButton;

    @FindBy(id = "finishButton")
    private WebElement finishButton;

    private Messages messages;

    public NewCharacterPageObject(WebDriver driver) {
        setDriver(driver);
        messages = Messages.getInstance(Locale.getDefault());
    }

    public void chooseRace(String race) {
        ComboBoxElement raceChoice = $(ComboBoxElement.class).caption(messages.getMessage("raceStep.race.label")).first();
        raceChoice.selectByText(race);
    }

    public void chooseRaceLanguage(String language) {
        ComboBoxElement languageChoice = $(ComboBoxElement.class).caption(messages.getMessage("raceStep.extraLanguage.label")).first();
        languageChoice.selectByText(language);
    }

    public void chooseClass(String classe) {
        ComboBoxElement classComboBox = $(ComboBoxElement.class).caption(messages.getMessage("classStep.class.label")).first();
        classComboBox.selectByText(classe);
    }

    public void chooseBackground(String background) {
        ComboBoxElement backgroundComboBox = $(ComboBoxElement.class).caption(messages.getMessage("backgroundStep.background.label")).first();
        backgroundComboBox.selectByText(background);
    }

    public void chooseBackgroundLanguage(String language) {
        ComboBoxElement languageChoice = $(CustomFieldElement.class).caption(messages.getMessage("backgroundStep.languages.label"))
                                                                    .$(ComboBoxElement.class)
                                                                    .first();
        languageChoice.selectByText(language);
    }

    public void clickNext() {
        moveTo(nextButton);
        nextButton.click();
    }

    public void clickBack() {
        moveTo(backButton);
        backButton.click();
    }

    public void clickCancel() {
        moveTo(cancelButton);
        cancelButton.click();
    }

    public void clickFinish() {
        moveTo(finishButton);
        finishButton.click();
    }

    public void moveTo(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        actions.perform();
    }

}
