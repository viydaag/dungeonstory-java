package com.dungeonstory.ui.test.integration.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.dungeonstory.ui.test.integration.authentication.LoginPageObject;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.GridElement.GridRowElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbilityIT extends IntegrationTestBase {

    private LoginPageObject loginPO;
    private CrudPageObject  crudPO;
    private static int      random = getRandomNumberInRange(1, 100);
    private final String    name   = "TestIT" + random;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        loginPO = PageFactory.initElements(getDriver(), LoginPageObject.class);
        loginPO.login("admin", "admin");
    }

    @Test
    public void test1NewAbility() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        assertTrue(isElementPresent(By.id("filterText")));
        assertTrue(isElementPresent(By.id("clearFilterButton")));
        assertTrue(isElementPresent(By.id("crudAddButton")));

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        crudPO.clickAdd();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        nameTextField.setValue(name);
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        abbreviationTextField.setValue("TI");
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();
        descriptionTextArea.setValue("Testing new ability");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        crudPO.clickSave();

        assertTrue(crudGrid.isDisplayed());

        GridRowElement newRow = crudGrid.getRow((int) (crudGrid.getRowCount() - 1));
        assertEquals(name, newRow.getCell(0).getText());
        assertEquals("TI", newRow.getCell(1).getText());
    }

    @Test
    public void test2ValidateFields() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        crudPO.clickAdd();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        nameTextField.setValue(name);

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        abbreviationTextField.setValue("TI");
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();
        descriptionTextArea.setValue("Testing new ability");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        //empty name
        clearTextField(nameTextField);
        assertTrue(nameTextField.hasClassName("v-textfield-error"));

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        nameTextField.setValue(name);
        assertFalse(nameTextField.hasClassName("v-textfield-error"));

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        //abbreviation longer than 3
        abbreviationTextField.setValue("TITI");
        assertTrue(abbreviationTextField.hasClassName("v-textfield-error"));

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        abbreviationTextField.setValue("TI");
        assertFalse(abbreviationTextField.hasClassName("v-textfield-error"));

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());
    }

    @Test
    public void test3UpdateAbility() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TI", abbreviationTextField.getValue());
        assertEquals("Testing new ability", descriptionTextArea.getValue());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        abbreviationTextField.setValue("TIT");
        descriptionTextArea.setValue("Testing update ability");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        crudPO.clickSave();

        assertTrue(crudGrid.isDisplayed());

        //check change in grid
        GridRowElement newRow = crudGrid.getRow((int) (crudGrid.getRowCount() - 1));
        assertEquals(name, newRow.getCell(0).getText());
        assertEquals("TIT", newRow.getCell(1).getText());

        //check change if form
        crudPO.clickLastGridRow();

        nameTextField = $(TextFieldElement.class).caption("Nom").first();
        abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TIT", abbreviationTextField.getValue());
        assertEquals("Testing update ability", descriptionTextArea.getValue());
    }

    @Test
    public void test4ResetAbility() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TIT", abbreviationTextField.getValue());
        assertEquals("Testing update ability", descriptionTextArea.getValue());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        descriptionTextArea.setValue("Testing reset ability");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        crudPO.clickReset();

        assertEquals("Testing update ability", descriptionTextArea.getValue());
        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());
    }

    @Test
    public void test5CancelAbility() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TIT", abbreviationTextField.getValue());
        assertEquals("Testing update ability", descriptionTextArea.getValue());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        descriptionTextArea.setValue("Testing cancel ability");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        crudPO.clickCancel();

        GridRowElement newRow = crudGrid.getRow((int) (crudGrid.getRowCount() - 1));
        assertEquals(name, newRow.getCell(0).getText());
        assertEquals("TIT", newRow.getCell(1).getText());

        //check nothing has changed in form
        crudPO.clickLastGridRow();

        nameTextField = $(TextFieldElement.class).caption("Nom").first();
        abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TIT", abbreviationTextField.getValue());
        assertEquals("Testing update ability", descriptionTextArea.getValue());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());
    }

    @Test
    public void test6DeleteAbility() throws InterruptedException {
        $(ButtonElement.class).caption("Caractéristiques").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        long rowCountBeforeDelete = crudGrid.getRowCount();
        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();

        assertEquals(name, nameTextField.getValue());
        assertEquals("TIT", abbreviationTextField.getValue());
        assertEquals("Testing update ability", descriptionTextArea.getValue());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        crudPO.clickDelete();

        assertTrue($$(WindowElement.class).exists());

        //cancel
        $(ButtonElement.class).id("confirmdialog-cancel-button").click();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());

        assertEquals(name, $(TextFieldElement.class).caption("Nom").first().getValue());
        assertEquals("TIT", $(TextFieldElement.class).caption("Abbréviation").first().getValue());
        assertEquals("Testing update ability", $(TextAreaElement.class).caption("Description").first().getValue());

        //delete
        crudPO.clickDelete();
        $(ButtonElement.class).id("confirmdialog-ok-button").click();

        assertTrue(crudGrid.isDisplayed());
        long rowCountAfterDelete = crudGrid.getRowCount();

        assertEquals(rowCountAfterDelete, rowCountBeforeDelete - 1);

        crudGrid.getRow((int) (crudGrid.getRowCount() - 1)).click();
    }

}
