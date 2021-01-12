package com.graduateproject.jwtimplementation.hateoas;

import com.graduateproject.jwtimplementation.controller.AdminController;
import com.graduateproject.jwtimplementation.controller.UserController;
import com.graduateproject.jwtimplementation.dto.ProblemModel;
import com.graduateproject.jwtimplementation.dto.UserModel;
import com.graduateproject.jwtimplementation.model.Problem;
import com.graduateproject.jwtimplementation.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
    public UserModelAssembler() {
        super(AdminController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User entity) {

        UserModel userModel = instantiateModel(entity);

        userModel.add(linkTo(methodOn(AdminController.class).getUser(entity.getId())).withSelfRel());

        userModel.setId(entity.getId());
        userModel.setPassword(entity.getPassword());
        userModel.setProblems(toProblemModels(entity.getProblems()));

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {

        CollectionModel<UserModel> userModels = super.toCollectionModel(entities);

        userModels.add(linkTo(methodOn(AdminController.class).users()).withSelfRel());

        return userModels;
    }

    private Set<ProblemModel> toProblemModels(Set<Problem> problems) {

        if (problems == null || problems.isEmpty())
            return Collections.emptySet();

        return problems.stream()
                .map(problem -> ProblemModel.builder()
                        .id(problem.getId())
                        .name(problem.getName())
                        .level(problem.getLevel())
                        .completed(problem.getCompleted())
                        .programmingLanguages(problem.getProgrammingLanguages()).build()
                        .add(linkTo(methodOn(UserController.class).getProblem(problem.getId())).withSelfRel()))
                .collect(Collectors.toSet());

    }

}
