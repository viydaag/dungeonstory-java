package com.dungeonstory.ui.test.integration.admin;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.dungeonstory.ui.test.integration.authentication.LoginPageObject;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;

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

    @Ignore
    @Test
    public void testValidateFields() throws InterruptedException {
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

}
