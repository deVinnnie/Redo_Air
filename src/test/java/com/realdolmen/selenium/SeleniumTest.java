package com.realdolmen.selenium;

import com.realdolmen.util.integration.RemoteIntegrationTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

public abstract class SeleniumTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass(){
        assumeNotNull("Set the webdriver path: -Dwebdriver.gecko.driver", System.getProperty("webdriver.gecko.driver"));

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDownClass(){
        if(driver != null){
            driver.close();
        }
    }

    /**
     * Adapted from {@link RemoteIntegrationTest}, uses the same flag to enable the testing.
     */
    @Before
    public void verifyIntegrationEnablingPreConditions(){
        boolean isIntegrationEnabled = (System.getProperty(RemoteIntegrationTest.INTEGRATION_ENABLED_SYSTEM_PROPERTY) != null);
        assumeTrue("Integration testing is disabled (enable using -Dintegration)", isIntegrationEnabled);
    }

    protected WebDriver driver(){
        return driver;
    }
}
