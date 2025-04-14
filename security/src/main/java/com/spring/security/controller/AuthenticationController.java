package com.spring.security.controller;

import com.spring.security.dto.RefreshTokenrequest;
import com.spring.security.dto.SignInRequest;
import com.spring.security.dto.SignUpRequest;
import com.spring.security.entity.User;
import com.spring.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping
    public ResponseEntity<User> SignUp(@RequestBody SignUpRequest signUpRequest){
        User user=authenticationService.SignUp(signUpRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/SignIn")
    public ResponseEntity SignIn(@RequestBody SignInRequest request){
        return new ResponseEntity(authenticationService.signin(request),HttpStatus.OK);
    }
    @PostMapping("/refreshToken")
    public String generateAccessTokenFromRefreshToken(@RequestBody RefreshTokenrequest refreshTokenrequest){
        return authenticationService.generateAccessTokenFromRefreshToken(refreshTokenrequest);
    }
}
