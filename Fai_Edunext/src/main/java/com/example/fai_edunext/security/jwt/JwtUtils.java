package com.example.fai_edunext.security.jwt;

import com.example.fai_edunext.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
