package com.nonoplan.api.example;

import com.nonoplan.core.example.Example;
import com.nonoplan.core.example.ExampleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExampleController {

    private final ExampleRepository exampleRepository;


    public ExampleController(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @GetMapping(value = "/example")
    public ResponseEntity<List<Example>> example() {
        List<Example> examples = exampleRepository.findAll();
        return ResponseEntity.ok(examples);
    }
}
