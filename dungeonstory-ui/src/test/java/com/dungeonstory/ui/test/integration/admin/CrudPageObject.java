package com.dungeonstory.ui.test.integration.admin;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.GridElement;

public class CrudPageObject extends TestBenchTestCase {

    @FindBy(id = "crudAddButton")
    private WebElement addButton;

    @FindBy(id = "crudSaveButton")
    private WebElement saveButton;

    @FindBy(id = "crudResetButton")
    private WebElement resetButton;

    @FindBy(id = "crudDeleteButton")
    private WebElement deleteButton;

    @FindBy(id = "crudCancelButton")
    private WebElement cancelButton;

    public CrudPageObject(WebDriver driver) {
        setDriver(driver);
    }

    public void clickAdd() {
        assertTrue(isElementPresent(By.id("crudAddButton")));
        addButton.click();
    }

    public void clickSave() {
        assertTrue(isElementPresent(By.id("crudSaveButton")));
        saveButton.click();
    }

    public void clickDelete() {
        assertTrue(isElementPresent(By.id("crudDeleteButton")));
        deleteButton.click();
    }

    public void clickReset() {
        assertTrue(isElementPresent(By.id("crudResetButton")));
        resetButton.click();
    }

    public void clickCancel() {
        assertTrue(isElementPresent(By.id("crudCancelButton")));
        cancelButton.click();
    }

    public void clickLastGridRow() {
        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();
    }

}
