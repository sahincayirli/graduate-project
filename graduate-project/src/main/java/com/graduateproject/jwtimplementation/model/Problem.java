package com.graduateproject.jwtimplementation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "problems")
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private long id;
    @Column(name = "problem_name")
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "user_problems",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    private String level;
    @Column(name = "programming_languages")
    private String programmingLanguages;
    private String completed;

    @Lob
    private String description;

    private String examples;

    private String solution;

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public Problem(String name) {
        this.name = name;
    }

    public Problem(String name, String level, String programmingLanguages, String completed) {
        this.name = name;
        this.level = level;
        this.programmingLanguages = programmingLanguages;
        this.completed = completed;
    }

    public Problem(String name, String level, String programmingLanguages, String completed, String description, String examples) {
        this.name = name;
        this.level = level;
        this.programmingLanguages = programmingLanguages;
        this.completed = completed;
        this.description = description;
        this.examples = examples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return id == problem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
