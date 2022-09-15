package com.iherd.spring.security.mongodb.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iherd.spring.security.mongodb.models.ERole;
import com.iherd.spring.security.mongodb.models.Role;
import com.iherd.spring.security.mongodb.models.User;
import com.iherd.spring.security.mongodb.Payload.request.Loginrequest;
import com.iherd.spring.security.mongodb.Payload.request.Signuprequest;
import com.iherd.spring.security.mongodb.Payload.response.UserInfoResponse;
import com.iherd.spring.security.mongodb.Payload.response.MessageResponse;
import com.iherd.spring.security.mongodb.repository.RoleRepository;
import com.iherd.spring.security.mongodb.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Loginrequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getmobilenumber(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().cookie(new Cookie("SomeName", "someId"))
                .body(new UserInfoResponse(userDetails.getmobilenumber(),
                        userDetails.getpassword(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Signuprequest signUpRequest) {
        if (userRepository.existsBymobilenumber(signUpRequest.getmobilenumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: mobilenumber is already registred!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getmobilenumber(),
                encoder.encode(signUpRequest.getpassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
