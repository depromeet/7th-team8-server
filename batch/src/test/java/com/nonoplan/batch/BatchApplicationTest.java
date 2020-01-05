package com.nonoplan.batch;

import com.nonoplan.core.Example;
import com.nonoplan.core.ExampleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BatchApplicationTest {

    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    public void test() {
        var example = Example.builder()
                .name("name")
                .build();

        exampleRepository.save(example);
    }
}