package com.spring.security.configuration;

import com.spring.security.service.JWTService;
import com.spring.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
@EnableWebSecurity
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JWTService jwtService;
    @Autowired
    private  UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        System.out.println("Authorization "+authHeader);
        if(StringUtils.isEmpty(authHeader)){
            System.out.println("authHeader Problem");
        }
        if(org.apache.commons.lang3.StringUtils.startsWith(authHeader," Bearer")){
            System.out.println("Bearer Problem");
        }
        if(StringUtils.isEmpty(authHeader) || org.apache.commons.lang3.StringUtils.startsWith(authHeader," Bearer")){
            System.out.println("Null");
            filterChain.doFilter(request,response);
            return;
        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No token found, proceeding without authentication");
            filterChain.doFilter(request, response);
            return;
        }
        jwt=authHeader.substring(7);
        userEmail=jwtService.extractUserName(jwt);
        System.out.println("useremail "+userEmail);
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userService.userDetailService().loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)){
                System.out.println("token is valid");
                SecurityContext securityContext=SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
            System.out.println("SecurityContext Authentication: " + SecurityContextHolder.getContext().getAuthentication());

        }
        filterChain.doFilter(request,response);
        System.out.println("Filter is applied correctly");
    }
}
