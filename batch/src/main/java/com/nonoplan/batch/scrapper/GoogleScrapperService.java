package com.nonoplan.batch.scrapper;

import com.nonoplan.batch.common.RegexpUtils;
import com.nonoplan.batch.common.WebDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleScrapperService implements ScrapperInterface {
    private static final String X_PATH_MAP = "//*[@id=\"rso\"]/div[1]/div/div/div[2]/div/div[4]/div[3]/div/div/a/div/span";
    private static final String X_PATH_TITLE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div/span";
    private static final String X_PATH_TYPE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[2]/div/span";
    private static final String X_PATH_ADDRESS = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[2]/div/div[2]/div/div/span[2]";
    private static final String X_PATH_SCORE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div/span[1]";
    private static final String X_PATH_REVIEW_COUNT= "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div/span[2]/span/a/span";
    private static final String X_PATH_CONTACT= "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[4]/div/div/span[2]/span/span";
    private static final String X_PATH_POPULAR_TIME = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[10]/div/div[3]/div[3]/div[2]/div[3]";

    private final WebDriver webDriver;

    public GoogleScrapperService(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void scrapping(String keyword) throws InterruptedException {
        var wait = new WebDriverWait(webDriver, 5);
        webDriver.get("https://www.google.com");
        wait.until(WebDriverUtils::waitPageLoading);

        var input = WebDriverUtils.findElement(webDriver, wait, By.name("q"));

        if (input == null) {
            throw new RuntimeException();
        }

        input.sendKeys(keyword);
        input.sendKeys(Keys.ENTER);
        wait.until(WebDriverUtils::waitPageLoading);

        var link = WebDriverUtils.findElement(webDriver, wait, By.xpath(X_PATH_MAP));

        if(link == null) {
            throw new RuntimeException();
        }

        link.click();
        wait.until(WebDriverUtils::waitPageLoading);

        do {
            wait.until(WebDriverUtils::waitPageLoading);
            var elements = webDriver.findElements(By.className("a-no-hover-decoration"));

            log.info("elements.size={}", elements.size());

            for (var element : elements) {
                element.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("immersive-container")));
                var container = webDriver.findElement(By.className("immersive-container"));
                var parent = container.findElement(By.xpath("./..")); // get parent
                var id = parent.getAttribute("id");

                var title = WebDriverUtils.getElementText(webDriver, wait, By.xpath(String.format(X_PATH_TITLE, id)));
                var address = WebDriverUtils.getElementText(webDriver, wait, By.xpath(String.format(X_PATH_ADDRESS, id)));
                var type = WebDriverUtils.getLastElementText(webDriver, wait, By.xpath(String.format(X_PATH_TYPE, id)));
                var score = WebDriverUtils.getElementText(webDriver, wait, By.xpath(String.format(X_PATH_SCORE, id)));
                var reviewCount = RegexpUtils.getOnlyNumber(WebDriverUtils.getElementText(webDriver, wait, By.xpath(String.format(X_PATH_REVIEW_COUNT, id))));
                var contact = WebDriverUtils.getElementText(webDriver, wait, By.xpath(String.format(X_PATH_CONTACT, id)));
                log.info("title={}", title);
                log.info("address={}", address);
                log.info("type={}", type);
                log.info("score={}", score);
                log.info("reviewCount={}", reviewCount);
                log.info("contact={}", contact);

                // TODO: 영업 시간
                // TODO: 인기 시간대
                ((JavascriptExecutor) webDriver).executeScript("document.getElementsByClassName('immersive-container')[0].remove()");
            }

            var next = WebDriverUtils.findElement(webDriver, wait, By.id("pnnext"));
            if (next != null) {
                String href = next.getAttribute("href");
                log.info("href={}", href);
                webDriver.get(href);
            } else {
                break;
            }
        } while (true);

    }
}
