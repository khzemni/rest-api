package com.hosting.restapi.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hosting.restapi.entity.Role;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.UserRepository;
import com.hosting.restapi.service.UserService;

@Component
public class SeedDataConfig implements CommandLineRunner {

	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    
    public SeedDataConfig(
    		UserRepository userRepository,
    	    PasswordEncoder passwordEncoder,
    	    UserService userService
    		) {
				this.userService = userService;
				this.passwordEncoder = passwordEncoder;
				this.userRepository = userRepository;
    	
    }

    @Override
    public void run(String... args) throws Exception {
        
      if (userRepository.count() == 0) {

    	  User admin = new User();
	      admin.setFirstName("admin");
	      admin.setLastName("admin");
	      admin.setEmail("admin");
	      admin.setPassword(passwordEncoder.encode("password"));
	      admin.setRole(Role.ROLE_ADMIN);
	      userService.saveUser(admin);
//	      System.out.println("created ADMIN user - {} " + admin);
      }
    }

}