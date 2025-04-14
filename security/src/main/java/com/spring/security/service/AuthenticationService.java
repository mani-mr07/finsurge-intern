package com.spring.security.service;

import com.spring.security.dto.RefreshTokenrequest;
import com.spring.security.dto.SignInRequest;
import com.spring.security.dto.SignInResponse;
import com.spring.security.dto.SignUpRequest;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    public User SignUp(SignUpRequest signUpRequest){

        User user=new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public SignInResponse signin(SignInRequest signInRequest){
        String email=signInRequest.getEmail();
        String password=signInRequest.getPassword();
        System.out.println("Email "+email);
        System.out.println("Password "+password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        User user=userRepository.findByEmail(email);
        System.out.println("User Email "+user.getEmail());
        String jwt=jwtService.generateToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);
        SignInResponse response=new SignInResponse();
        response.setEmail(signInRequest.getEmail());
        response.setAccessToken(jwt);
        response.setRefreshToken(refreshToken);
        return response;
    }
    public String generateAccessTokenFromRefreshToken(RefreshTokenrequest tokenrequest) {
        String userName = jwtService.extractUserName(tokenrequest.getRefreshToken());
        System.out.println(userName);
        User user = userRepository.findByEmail(userName);
        if(user!=null){
        System.out.println(user.getEmail());}
       else{
        System.out.println("not user");}
        String jwt = "";
        if (jwtService.isTokenValid(tokenrequest.getRefreshToken(), user)) {
            jwt = jwtService.generateToken(user);
            System.out.println("token is generated");
        }
        else{
            System.out.println("Token is invalid");
        }
        System.out.println(jwt);
        return jwt;
    }
}
