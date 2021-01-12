package com.graduateproject.jwtimplementation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example {

    private String input;
    private String output;

    public Example(String[] examples) {
        input = examples[0];
        output = examples[1];
    }
}
