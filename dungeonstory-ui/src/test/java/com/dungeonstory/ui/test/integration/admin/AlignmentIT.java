package com.dungeonstory.ui.test.integration.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.dungeonstory.ui.test.integration.authentication.LoginPageObject;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.GridElement.GridRowElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlignmentIT extends IntegrationTestBase {

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

    @Ignore
    @Test
    public void testValidateFields() throws InterruptedException {
        $(ButtonElement.class).caption("Alignements").first().click();

        crudPO = PageFactory.initElements(getDriver(), CrudPageObject.class);

        assertTrue(isElementPresent(By.id("filterText")));
        assertTrue(isElementPresent(By.id("clearFilterButton")));
        assertTrue(isElementPresent(By.id("crudAddButton")));

        GridElement crudGrid = $(GridElement.class).id("crudGrid");
        assertTrue(crudGrid.isDisplayed());

        crudPO.clickAdd();

        assertTrue($(TextFieldElement.class).caption("Nom").exists());
        assertTrue($(TextFieldElement.class).caption("Abbréviation").exists());
        assertTrue($(TextFieldElement.class).caption("Description courte").exists());
        assertTrue($(TextAreaElement.class).caption("Description").exists());
        assertTrue($(CheckBoxElement.class).caption("Jouable par un personnage").exists());

        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertFalse($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption("Nom").first();
        nameTextField.setValue(name);
        TextFieldElement abbreviationTextField = $(TextFieldElement.class).caption("Abbréviation").first();
        abbreviationTextField.setValue("TI");
        TextFieldElement descriptionTextArea = $(TextFieldElement.class).caption("Description courte").first();
        descriptionTextArea.setValue("Testing new alignment");

        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudResetButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudCancelButton").isEnabled());
        assertTrue($(ButtonElement.class).id("crudDeleteButton").isEnabled());

        // test empty name
        clearTextField(nameTextField);
        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());

        nameTextField.setValue(name);
        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());

        // test abbreviation greater than 2
        abbreviationTextField.setValue("TI3");
        assertFalse($(ButtonElement.class).id("crudSaveButton").isEnabled());

        abbreviationTextField.setValue("TI");
        assertTrue($(ButtonElement.class).id("crudSaveButton").isEnabled());

        crudPO.clickSave();

        assertTrue(crudGrid.isDisplayed());

        moveTo(crudGrid);

        GridRowElement newRow = crudGrid.getRow((int) (crudGrid.getRowCount() - 1));
        assertEquals(name, newRow.getCell(0).getText());
        assertEquals("TI", newRow.getCell(1).getText());
    }

}