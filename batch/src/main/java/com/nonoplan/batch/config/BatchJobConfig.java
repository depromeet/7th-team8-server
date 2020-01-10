package com.nonoplan.batch.config;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;

/**
 * Batch Job Default Config
 */
public class BatchJobConfig {
    protected final JobBuilderFactory jobBuilderFactory;
    protected final StepBuilderFactory stepBuilderFactory;

    public BatchJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * 배치에서 사용하는 기본적인 테이블을 사용하지 않고, 메모리에서 처리하도록 설정
     * @return
     */
    @Bean
    public BatchConfigurer batchConfigurer() {
        return new InMemoryBatchConfig();
    }
}
