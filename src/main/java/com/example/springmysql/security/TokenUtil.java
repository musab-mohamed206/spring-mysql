package com.example.springmysql.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {


    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";

    //@Valid("${auth.expiration}")
    private Long TOKEN_VALIDITY = 604800L;
    private String TOKEN_SECRET = "TodoApp123456654321";

    public String generateToken(UserDetails userDetails){
        
        //clims
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS512 , TOKEN_SECRET)
            .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 100);
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
           return null;
        }
    }

	public boolean isTokenValid(String token, UserDetails userDetails) {
        String username =getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
             claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
           return null;
        }
        return claims;
    }
}
