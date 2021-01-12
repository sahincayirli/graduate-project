package com.graduateproject.jwtimplementation.service;

import com.graduateproject.jwtimplementation.model.Problem;
import com.graduateproject.jwtimplementation.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository repository;

    public List<Problem> problems() {
        return repository.findAll();
    }

    public Problem getProblem(long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no problem with id: " + id));
    }

    public Problem createProblem(Problem problem) {
        return repository.save(problem);
    }

    public Problem updateProblem(Problem problem) {
        var existed = getProblem(problem.getId());
        existed.setName(problem.getName());
        existed.setCompleted(problem.getCompleted());
        existed.setDescription(problem.getDescription());
        existed.setExamples(problem.getExamples());
        existed.setLevel(problem.getLevel());
        existed.setProgrammingLanguages(problem.getProgrammingLanguages());
        existed.setSolution(problem.getSolution());
        return repository.save(existed);
    }

    public void createAll(List<Problem> problems) {
        repository.saveAll(problems);
    }

    public Problem deleteProblem(long id) {
        var deleted = getProblem(id);
        repository.delete(deleted);
        return deleted;
    }

    public void deleteAllProblems() {
        repository.deleteAllInBatch();
    }
}
