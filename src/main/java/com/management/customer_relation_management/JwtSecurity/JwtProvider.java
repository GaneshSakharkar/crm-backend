package com.management.customer_relation_management.JwtSecurity;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    public static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRETE_KEY.getBytes());

//    public static String generateJwtToken(Authentication auth){
//            String jwt = Jwts.builder()
//                        .setIssuer("ok").setIssuedAt(new Date())
//                        .setExpiration(new Date(new Date().getTime()+84600000))
//                        .claim("email", auth.getName())
//                        .signWith(key)
//                        .compact();
//            return jwt;
//    }
    
    public static String generateJwtToken(Authentication auth) {
        Object principal = auth.getPrincipal();

        String email = auth.getName(); // default
        String role = null;

        // Extract role from UserDetails
        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
            role = userDetails.getAuthorities().stream()
                      .findFirst()
                      .map(GrantedAuthority::getAuthority)
                      .orElse("ROLE_USER");
        }

        return Jwts.builder()
                .setIssuer("ok")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .claim("email", email)
                .claim("role", role)
                .signWith(key)
                .compact();
    }


    public static String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                        .setSigningKey(key).build()
                        .parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }


}
