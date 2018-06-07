package com.dungeonstory.backend;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.dungeonstory.backend.repository.JPAService;

public class TestWithBackend {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        JPAService.init();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        JPAService.close();
    }

}
