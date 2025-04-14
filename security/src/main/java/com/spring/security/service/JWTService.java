package com.spring.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTService {
    public String extractUserName(String token){
        System.out.println("Token Received: "+token);
        try{
            Claims claims=extractAllClaims(token);
            System.out.println("Extracted Claims "+claims);
            return claims.getSubject();
        }
        catch (Exception e){
            System.out.println("Error extracting username: " + e.getMessage());
            return null;
        }
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken( UserDetails userDetails){
//        HashMap<String,Object>extractClaims,
//        .setClaims(extractClaims)
        System.out.println("UserName "+userDetails.getUsername());
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignKey(){
        byte[]key= Decoders.BASE64.decode("413F442847284862506553685660597033733676397924422645294840406351");
        return Keys.hmacShaKeyFor(key);
    }
    private <T> T extractClaim(String token, Function<Claims,T>claimResolvers){
        final Claims claims=extractAllClaims(token);
        return claimResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims= Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build().
                parseClaimsJws(token).
                getBody();
        System.out.println("claims "+claims);
        return claims;
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUserName(token);
        if(username.equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true;
        }
        return false;
    }
    public boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
