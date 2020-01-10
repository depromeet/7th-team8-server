package com.nonoplan.batch.job.example;

import com.nonoplan.batch.BatchApplication;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.util.List;

public class ExampleJobTest {

    @Test
    public void exampleJobTest() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        BatchApplication.main(List.of("exampleJob").toArray(String[]::new));
    }
}
