package com.graduateproject.jwtimplementation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.graduateproject.jwtimplementation.model.Example;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("problem")
@Relation(collectionRelation = "problems")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemModel extends RepresentationModel<ProblemModel> {

    private long id;
    private String name;
    private String level;
    private String completed;
    private String programmingLanguages;
    private List<Example> examples;
    private String description;

    public static List<Example> toExamples(String examples) {
        return Arrays.stream(examples.split("\\+"))
                .map(example -> new Example(example.split("-")))
                .collect(Collectors.toList());

    }
}
