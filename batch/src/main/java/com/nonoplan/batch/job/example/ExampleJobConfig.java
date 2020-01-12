package com.nonoplan.batch.job.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 배치 잡 빈 생성 예
 */
@Configuration
public class ExampleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExampleTasklet exampleTasklet;

    public ExampleJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ExampleTasklet exampleTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.exampleTasklet = exampleTasklet;
    }

    @Bean
    public Job exampleJob() {
        return jobBuilderFactory.get("exampleJob") // exampleJob 이란 이름의 Batch Job 생성
                .start(exampleStep())
                .build();
    }

    @Bean
    public Step exampleStep() {
        return stepBuilderFactory.get("exampleStep") // exampleStep 이란 이름의 Batch Step 생성
                .tasklet(exampleTasklet)
                .allowStartIfComplete(true) // 항상 재시작하게 하는 설
                .build();
    }
}
