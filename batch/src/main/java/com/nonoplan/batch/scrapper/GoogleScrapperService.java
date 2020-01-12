package com.nonoplan.batch.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleScrapperService {
    private final WebDriver webDriver;

    public GoogleScrapperService(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void processScrapper() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.get("https://www.google.com/search?sxsrf=ACYBGNQSoj0L4F6jk4JpNcvKhSshUCnmtg:1578838555503&q=%EC%A0%9C%EC%A3%BC%EB%8F%84+%EB%A7%9B%EC%A7%91&npsic=0&rflfq=1&rlha=0&rllag=33471447,126734272,11041&tbm=lcl&ved=2ahUKEwiXo4j6n_7mAhVWQd4KHfPJDIwQjGp6BAgKED0&tbs=lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9&rldoc=1&biw=1207&bih=936#rlfi=hd:;si:;mv:[[33.5736644,126.9559221],[33.1999081,126.2090241]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9");
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        WebElement table = webDriver.findElement(By.id("nav"));
        Iterator<String> iterator = table.findElements(By.className("fl")).stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .iterator();

        do {
            wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
            List<WebElement> elements = webDriver.findElements(By.className("a-no-hover-decoration"));

            log.info("elements.size={}", elements.size());

            for (WebElement element : elements) {
                element.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("immersive-container")));
                // TODO: 음식점 정보 가져오기


                ((JavascriptExecutor) webDriver).executeScript("document.getElementsByClassName('immersive-container')[0].remove()");
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cur")));
            WebElement cur = webDriver.findElement(By.className("cur"));
            log.info("pageNumber={}", cur.getText());
            String nextHref = iterator.next();
            log.info("href={}", nextHref);
            webDriver.get(nextHref);
        } while (iterator.hasNext());

    }
}
