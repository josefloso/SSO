package com.example.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import org.springframework.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class SsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

	@GetMapping("/welcomeMessage")
	@PreAuthorize("hasAuthority('SCOPE_profile')")
	public String getWelcomeMessage(Principal principal) {
		JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
		String fullName = jwtAuth.getToken().getClaimAsString("fullName");
		return "Welcome" + fullName + "!";
	

	}

	@GetMapping("/userEmail")
	@PreAuthorize("hasAuthority('SCOPE_email')")
	public String getUserEmail(Principal principal) {
		JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
		String email = jwtAuth.getToken().getClaimAsString("userEmail");
		return email;

	}


}
