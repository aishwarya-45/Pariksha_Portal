package com.exam;


import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("starting code");

			User user = new User();

			user.setFirstName("amit");
			user.setLastName("yadav");
			user.setUsername("amt123");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setEmail("amit@gmail.com");
			user.setProfile("fff.png");

			Role role1 = new Role();
			role1.setRoleId(44l);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);

			userRoleSet.add(userRole);

			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());

		}catch(UserFoundException e){
			e.printStackTrace();
		}

	}
}
