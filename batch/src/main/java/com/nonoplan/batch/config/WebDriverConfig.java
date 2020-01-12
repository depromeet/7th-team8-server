package com.nonoplan.batch.config;

import org.openqa.selenium.WebDriver;
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
        var file = ResourceUtils.getFile("classpath:drivers/geckodriver-macos");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

        var profile = new FirefoxProfile();
        profile.setPreference("permissions.default.image", 2);
        profile.setPreference("permissions.default.stylesheet", 2);
        profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
        var options = new FirefoxOptions();
        options.setProfile(profile);
        options.setHeadless(true);

        var driver = new FirefoxDriver(options);
        driver.manage().window().fullscreen();
        return driver;
    }
}
