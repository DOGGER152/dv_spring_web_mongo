package com.springweb.dv_spring_web_mongo.security.jwt;

import com.springweb.dv_spring_web_mongo.model.Role;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final UserDetailsService userService;

    @Value("${jwt.token.expirationDate.Hours}")
    String exprHours;

    @Value("${jwt.token.secret}")
    String secret;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, Set<Role> list) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", list);

        SimpleDateFormat format = new SimpleDateFormat("H");
        Date now = new Date();
        Date vld = null;
        try {
            vld = new Date(now.getTime() + format.parse(exprHours).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(vld)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("JWT token invalid or expired");
        }
        return true;
    }

    public UserDetailsService getUserService() {
        return userService;
    }
}
