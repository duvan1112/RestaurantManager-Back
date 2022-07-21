package com.restaurant_manager.restaurant_manager.controllers;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant_manager.restaurant_manager.models.users.Role;
import com.restaurant_manager.restaurant_manager.models.users.User;
import com.restaurant_manager.restaurant_manager.models.users.repository.UserRepository;

@Controller
public class UserLoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping (value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userRepository.findByEmail(email);

                String accessToken = JWT.create().withSubject(user.getEmail())
                                .withExpiresAt(new Date(System.currentTimeMillis()
                                                + TimeUnit.DAYS.toMillis(1)))
                                .withIssuer(request.getRequestURL().toString())
                                .withClaim("roles",
                                                user.getRoles().stream().map(Role::getName)
                                                                .collect(Collectors.toList()))
                                .sign(algorithm);

                Map<String, String> map = new HashMap<>();
                map.put("accessToken", accessToken);
                map.put("refreshToken", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getWriter(), map);

                
                
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getWriter(), error);
            }

        } else {
            throw new Exception("No token found");
        }
    }

}
