package com.spring.security.controller;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String sayHello(HttpServletRequest request){
        String authorization=request.getHeader("Authorization");
        String jwt=authorization.substring(7);
        String userName=jwtService.extractUserName(jwt);
        if(!userName.isEmpty()){
            User user=userRepository.findByEmail(userName);
            return "Hi,"+user.getEmail();
        }
        return "Hi,dummy User";
    }
}
