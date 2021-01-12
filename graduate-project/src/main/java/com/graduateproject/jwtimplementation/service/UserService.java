package com.graduateproject.jwtimplementation.service;

import com.graduateproject.jwtimplementation.model.Solution;
import com.graduateproject.jwtimplementation.model.User;
import com.graduateproject.jwtimplementation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final ProblemService problemService;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(long id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("There is no user with id: " + id));
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void saveAllUsers(List<User> users) {
        users.forEach(this::createUser);
    }

    public User updateUser(User user) {
        var existingUser = getUser(user.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setProblems(user.getProblems());
        existingUser.setActive(user.isActive());
        return repository.save(existingUser);
    }

    public User deleteUser(long id) {
        var deleted = getUser(id);
        deleted.getProblems().forEach(deleted::deleteProblem);
        deleted = updateUser(deleted);
        repository.deleteById(id);
        return deleted;
    }

    public User solveProblem(long problemId, Solution solution) {
        var solved = problemService.getProblem(problemId);
        solved.setSolution(solution.getSolution());
        var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var solver = repository.findByUsername(username).get();
        solver.addProblem(solved);
        return updateUser(solver);
    }

    public User blockToUser(long id) {
        var user = getUser(id);
        user.setActive(false);
        return updateUser(user);
    }

    public void cleanTable() {
        repository.deleteAllInBatch();
    }

    public User unblockToUser(long id) {
        var user = getUser(id);
        user.setActive(true);
        return updateUser(user);
    }
}
