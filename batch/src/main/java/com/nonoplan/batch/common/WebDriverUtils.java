package com.nonoplan.batch.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class WebDriverUtils {

    private WebDriverUtils() {
    }

    public static WebElement findElement(WebDriver driver, WebDriverWait wait, By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by);
        } catch (Exception e) {
            log.warn("findElement fail. by={}", by);
            return null;
        }
    }

    public static String getElementText(WebDriver driver, WebDriverWait wait, By by) {
        WebElement element = findElement(driver, wait, by);
        if (element != null) {
            return element.getText();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private static WebElement findLastElement(WebDriver driver, WebDriverWait wait, By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            List<WebElement> elements = driver.findElements(by);
            return elements.get(elements.size() - 1);
        } catch (Exception e) {
            log.warn("findElements fail. by={}", by);
            return null;
        }
    }

    public static String getLastElementText(WebDriver driver, WebDriverWait wait, By by) {
        WebElement element = findLastElement(driver, wait, by);
        if (element != null) {
            return element.getText();
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static boolean waitPageLoading(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}
