package com.dungeonstory.ui.test.integration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.RequestHeaders;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.UserAgent;

public class DriverUtil {

    public static final String DEFAULT_CONFIG_PROPERTIES = "/test-config.properties";
    private Properties         prop                      = new Properties();

    public DriverUtil(String propertyFileName) {
        try (InputStream in = DriverUtil.class.getResourceAsStream(propertyFileName)) {
            if (in != null) {
                prop.load(in);
            } else {
                throw new RuntimeException("Test property file not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to open test property file.", e);
        }
    }

    public DriverUtil() {
        this(DriverUtil.DEFAULT_CONFIG_PROPERTIES);
    }

    public Optional<WebDriver> getPreferredDriver() {
        WebDriver driver = null;
        try {
            String driverPath = null;
            String browser = prop.getProperty("test.browser", "jbrowser");
            switch (browser) {
                case "phantomjs":
                    driverPath = "./lib/phantomjs-2.1.1.exe";
                    System.setProperty("phantomjs.binary.path", driverPath);
                    driver = new PhantomJSDriver();
                    break;
                case "chrome":
                    int chromeVersion = Integer.parseInt(prop.getProperty("chrome.version"));

                    if (chromeVersion <= 56) {
                        driverPath = "./lib/chromedriver-2.27.exe";
                    } else {
                        driverPath = "./lib/chromedriver-2.31.exe";
                    }
                    System.setProperty("webdriver.chrome.driver", driverPath);

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setBinary(prop.getProperty("chrome.binary.path"));
                    driver = new ChromeDriver(chromeOptions);
                    break;
                default:
                    driver = new JBrowserDriver(Settings.builder().requestHeaders(RequestHeaders.CHROME)
                            .userAgent(new UserAgent(UserAgent.Family.WEBKIT, "Google Inc.", "Win32", "Windows NT 6.1",
                                    "5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2869.0 Safari/537.36",
                                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2869.0 Safari/537.36"))
                            .build());
            }
            driver.manage().window().setSize(new Dimension(1280, 720));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(driver);
    }

    public String getTestUrl() {
        return String.format("http://%s:%s", getTestHostname(), getTestPort());
    }

    public String getTestPort() {
        return prop.getProperty("test.port", "8080");
    }

    public String getTestHostname() {
        return prop.getProperty("test.hostname", "localhost/dungeonstory-uis");
    }
}
