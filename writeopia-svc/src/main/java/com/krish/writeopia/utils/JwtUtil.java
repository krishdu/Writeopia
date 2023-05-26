package com.krish.writeopia.utils;

import com.krish.writeopia.models.MyUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    public static final String ROLES = "ROLES";

    /**
     * Create JWT Token from MyUser Instance/ Data from DB
     */
    public String generateJWT(Optional<MyUsers> user){
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLES, user.get().getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.get().getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     *Parse and get all claims, if parse is successful token is also ok and verified
     */
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Get a single claim from all claims
     */
    private <T> T extractOneClaim(String jwtToken, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    /**
     * Get username from token
     */
    public String extractUserName(String jwtToken){
        return extractOneClaim(jwtToken, Claims::getSubject);

    }

    /**
     *Get single role from token
     */
    public String extractRoles(String jwtToken){
        return extractOneClaim(jwtToken, claims -> (String) claims.get(ROLES));

    }


    /**
     * Get the expiration time
     */
    public Date extractExpiration(String jwtToken){
        return extractOneClaim(jwtToken, Claims::getExpiration);

    }

    /**
     *Check if expired or not
     */
    public boolean isJwtExpired(String jwtToken){
        final Date expiration = extractExpiration(jwtToken);
        return expiration.before(new Date());
    }
}

