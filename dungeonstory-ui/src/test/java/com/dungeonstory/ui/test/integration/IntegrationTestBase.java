package com.dungeonstory.ui.test.integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.vaadin.testbench.TestBenchTestCase;

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

}
