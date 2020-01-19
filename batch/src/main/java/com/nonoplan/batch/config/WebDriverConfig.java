package com.nonoplan.batch.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver webDriver() throws FileNotFoundException {
        var file = ResourceUtils.getFile("classpath:drivers/chromedriver-macos");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        var options = new ChromeOptions();
        //ßoptions.setHeadless(true);

        var driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        return driver;
    }
}
