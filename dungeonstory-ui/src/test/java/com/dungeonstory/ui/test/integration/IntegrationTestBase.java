package com.dungeonstory.ui.test.integration;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.AbstractTextFieldElement;

@RunWith(BlockJUnit4ClassRunner.class)
public class IntegrationTestBase extends TestBenchTestCase {

    @Before
    public void setUp() throws Exception {
        setDriver(new DriverUtil().getPreferredDriver().get());
    }

    @After
    public void tearDown() throws Exception {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    public void clearTextField(AbstractTextFieldElement field) {
        int length = field.getValue().length();
        Keys[] data = new Keys[length];
        Arrays.fill(data, Keys.BACK_SPACE);
        field.sendKeys(data);
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    public void moveTo(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        actions.perform();
    }

    public void click(WebElement element, int nbTimes) {
        moveTo(element);
        for (int i = 0; i < nbTimes; i++) {
            element.click();
        }
    }

    public String getText(WebElement element) {
        moveTo(element);
        return element.getText();
    }

    public String getValue(WebElement element) {
        throw new RuntimeException("not supported");
    }

    public String getAttribute(WebElement element, String attributeName) {
        moveTo(element);
        return element.getAttribute(attributeName);
    }

}
