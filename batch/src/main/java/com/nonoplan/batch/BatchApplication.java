package com.nonoplan.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.nonoplan")
@EnableJpaRepositories(basePackages = "com.nonoplan")
@EntityScan("com.nonoplan")
@EnableBatchProcessing
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class);
    }
}
