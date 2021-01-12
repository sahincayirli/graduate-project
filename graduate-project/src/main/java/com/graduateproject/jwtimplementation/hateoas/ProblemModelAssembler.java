package com.graduateproject.jwtimplementation.hateoas;


import com.graduateproject.jwtimplementation.controller.UserController;
import com.graduateproject.jwtimplementation.dto.ProblemModel;

import com.graduateproject.jwtimplementation.model.Example;
import com.graduateproject.jwtimplementation.model.Problem;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProblemModelAssembler extends RepresentationModelAssemblerSupport<Problem, ProblemModel> {


    public ProblemModelAssembler() {
        super(UserController.class, ProblemModel.class);
    }

    @Override
    public ProblemModel toModel(Problem entity) {
        ProblemModel problemModel = instantiateModel(entity);

        problemModel.add(linkTo(methodOn(UserController.class).getProblem(entity.getId())).withSelfRel());
        problemModel.setId(entity.getId());
        problemModel.setName(entity.getName());
        problemModel.setLevel(entity.getLevel());
        problemModel.setCompleted(entity.getCompleted());
        problemModel.setProgrammingLanguages(entity.getProgrammingLanguages());
        problemModel.setExamples(ProblemModel.toExamples(entity.getExamples()));
        problemModel.setDescription(entity.getDescription());
        return problemModel;
    }

    @Override
    public CollectionModel<ProblemModel> toCollectionModel(Iterable<? extends Problem> entities) {
        CollectionModel<ProblemModel> problemModels = super.toCollectionModel(entities);
        problemModels.add(linkTo(methodOn(UserController.class).getProblems()).withSelfRel());
        return problemModels;
    }

}
