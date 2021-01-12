package com.graduateproject.jwtimplementation.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Getter @Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String username;
    private String password;
    private boolean isActive = true;

    @ManyToMany(mappedBy = "users")
    private Set<Problem> problems = new HashSet<>();

    @Column(name = "user_role")
    private String roles = "ROLE_USER";

    public void addAuthority(String role) {
        if (!role.startsWith("ROLE_"))
            role = "ROLE_" + role;

        this.roles += "," + role;
    }

    public String[] getAuthorities() {
        return roles.split(",");
    }

    public void addProblem(Problem problem) {
        problems.add(problem);
        problem.addUser(this);
    }

    public void deleteProblem(Problem problem) {
        problems.remove(problem);
        problem.removeUser(this);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;

        if (!roles.startsWith("ROLE_"))
            roles = "ROLE_" + roles;

        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
