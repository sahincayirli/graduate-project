package com.graduateproject.jwtimplementation.controller;

import com.graduateproject.jwtimplementation.hateoas.UserModelAssembler;
import com.graduateproject.jwtimplementation.model.Problem;
import com.graduateproject.jwtimplementation.model.User;
import com.graduateproject.jwtimplementation.service.ProblemService;
import com.graduateproject.jwtimplementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final ProblemService problemService;
    private final UserModelAssembler userModelAssembler;

    /* USER OPERATIONS */
    @GetMapping("users")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok(userModelAssembler.toCollectionModel(userService.getAllUsers()));
    }

    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(userModelAssembler.toModel(userService.getUser(id)));
    }

    @PutMapping("users/block/{id}")
    public ResponseEntity<User> blockUser(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(userService.blockToUser(id));
    }

    @PutMapping("users/unblock/{id}")
    public ResponseEntity<User> unblockUser(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(userService.unblockToUser(id));
    }

    /* PROBLEM OPERATIONS */

    @PostMapping("/problems/register")
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(problemService.createProblem(problem));
    }

    @DeleteMapping("/problems/delete/{id}")
    public ResponseEntity<Problem> deleteProblem(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(problemService.deleteProblem(id));
    }

    @PutMapping("/problems/update")
    public ResponseEntity<Problem> updateProblem(@RequestBody Problem problem) {
        return ResponseEntity.ok(problemService.updateProblem(problem));
    }

}
