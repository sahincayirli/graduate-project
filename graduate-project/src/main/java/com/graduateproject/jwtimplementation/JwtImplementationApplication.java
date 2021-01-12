package com.graduateproject.jwtimplementation;

import com.graduateproject.jwtimplementation.model.Problem;
import com.graduateproject.jwtimplementation.model.User;
import com.graduateproject.jwtimplementation.service.ProblemService;
import com.graduateproject.jwtimplementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class JwtImplementationApplication {

	private final UserService userService;
	private final ProblemService problemService;


	@PostConstruct
	private void init() {


		var admin = new User("admin","admin123","ADMIN");
		var user = new User("Sahin","sahin123");
		var user2 = new User("emre","emre123");

		var description = "You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.\n" +

				"However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.\n" +

				"Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.";

		var examples = "Input: [2,1,2]-Output: 5+Input: [1,2,1]-Output: 0";


		var problems = List.of(new Problem("Two Sum","EASY","JAVA C++ JavaScript","2548", description, examples ),
				new Problem("Largest Number","MEDIUM","C Ruby Haskell Java Python","1478", description, examples ),
				new Problem("String Multiplication","HARD","C++ Java C#","784", description, examples ),
				new Problem("Valid BST","HARD","C++ Java C#","784", description, examples ),
				new Problem("Is Palindrome","HARD","C++ Java C#","784", description, examples ),
				new Problem("Is Anagram","HARD","C++ Java C#","784", description, examples ),
				new Problem("Get Permutations","HARD","C++ Java C#","784", description, examples ),
				new Problem("Delete Node From BST","HARD","C++ Java C#","784", description, examples ),
				new Problem("Rotate Matrix","HARD","C++ Java C#","784", description, examples ));


		userService.saveAllUsers(Arrays.asList(admin,user,user2));
		problemService.createAll(problems);
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtImplementationApplication.class, args);
	}

}
