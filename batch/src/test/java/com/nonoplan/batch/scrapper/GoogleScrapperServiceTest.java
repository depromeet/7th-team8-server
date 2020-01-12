package com.nonoplan.batch.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleScrapperServiceTest {

    @Autowired
    private GoogleScrapperService googleScrapperService;

    @Test
    public void test() throws InterruptedException {
        googleScrapperService.processScrapper();
    }
}