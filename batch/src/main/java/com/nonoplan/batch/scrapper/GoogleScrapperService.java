package com.nonoplan.batch.scrapper;

import com.nonoplan.batch.common.RegexpUtils;
import com.nonoplan.batch.common.WebDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleScrapperService {
    private static final String X_PATH_TITLE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div/span";
    private static final String X_PATH_TYPE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[2]/div/span";
    private static final String X_PATH_ADDRESS = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[2]/div/div[2]/div/div/span[2]";
    private static final String X_PATH_SCORE = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div/span[1]";
    private static final String X_PATH_REVIEW_COUNT= "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div/span[2]/span/a/span";
    private static final String X_PATH_POPULAR_TIME = "//*[@id='%s']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[10]/div/div[3]/div[3]/div[2]/div[3]";

    private final WebDriver webDriver;

    public GoogleScrapperService(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void processScrapper() throws InterruptedException {
        var wait = new WebDriverWait(webDriver, 5);
        webDriver.get("https://www.google.com/search?sxsrf=ACYBGNQSoj0L4F6jk4JpNcvKhSshUCnmtg:1578838555503&q=%EC%A0%9C%EC%A3%BC%EB%8F%84+%EB%A7%9B%EC%A7%91&npsic=0&rflfq=1&rlha=0&rllag=33471447,126734272,11041&tbm=lcl&ved=2ahUKEwiXo4j6n_7mAhVWQd4KHfPJDIwQjGp6BAgKED0&tbs=lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9&rldoc=1&biw=1207&bih=936#rlfi=hd:;si:;mv:[[33.5736644,126.9559221],[33.1999081,126.2090241]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9");
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        var table = webDriver.findElement(By.id("nav"));
        var iterator = table.findElements(By.className("fl")).stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .iterator();

        do {
            wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
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
                log.info("title={}", title);
                log.info("address={}", address);
                log.info("type={}", type);
                log.info("score={}", score);
                log.info("reviewCount={}", reviewCount);

                // TODO: 인기 시간대
                ((JavascriptExecutor) webDriver).executeScript("document.getElementsByClassName('immersive-container')[0].remove()");
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cur")));
            var cur = webDriver.findElement(By.className("cur"));
            log.info("pageNumber={}", cur.getText());
            var nextHref = iterator.next();
            log.info("href={}", nextHref);
            webDriver.get(nextHref);
        } while (iterator.hasNext());

    }
}
