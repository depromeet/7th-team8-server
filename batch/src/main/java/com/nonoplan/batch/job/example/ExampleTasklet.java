package com.nonoplan.batch.job.example;

import com.nonoplan.core.Example;
import com.nonoplan.core.ExampleRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExampleTasklet implements Tasklet {

    private final ExampleRepository exampleRepository;

    public ExampleTasklet(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        var example = Example.builder().name("hello").build();
        exampleRepository.save(example);
        return RepeatStatus.FINISHED;
    }
}
