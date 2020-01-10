package com.nonoplan.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.nonoplan")
@EnableJpaRepositories(basePackages = "com.nonoplan")
@EntityScan("com.nonoplan")
public class BatchApplication {

    /**
     * 배치 잡을 메인 함수에서 실행 시키기 위한 설정
     * @param args 파라미터: 배치 잡의 이름을 필수로 주어야 한다.
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        var springApplication = new SpringApplication(BatchApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        var ctx = springApplication.run(args);
        var jobLauncher = ctx.getBean(JobLauncher.class);

        var job = ctx.getBean(args[0], Job.class);
        jobLauncher.run(job, new JobParameters());
        System.exit(0);
    }
}
