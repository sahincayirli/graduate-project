package com.graduateproject.jwtimplementation.controller;

import com.graduateproject.jwtimplementation.hateoas.ProblemModelAssembler;
import com.graduateproject.jwtimplementation.model.Solution;
import com.graduateproject.jwtimplementation.service.ProblemService;
import com.graduateproject.jwtimplementation.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("problems")
public class UserController {

    private final UserService userService;
    private final ProblemService problemService;
    private final ProblemModelAssembler problemModelAssembler;

    @GetMapping
    public ResponseEntity<?> getProblems() {
        return ResponseEntity.ok(problemModelAssembler.toCollectionModel(problemService.problems()));
    }

    @PutMapping("/solve/{problemid}")
    public ResponseEntity<?> solveProblem(@PathVariable(value = "problemid") long problemId, @RequestBody Solution solution){
        return ResponseEntity.ok(userService.solveProblem(problemId,solution));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProblem(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(problemModelAssembler.toModel(problemService.getProblem(id)));
    }

}
