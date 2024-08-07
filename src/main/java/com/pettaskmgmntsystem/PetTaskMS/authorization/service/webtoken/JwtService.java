package com.pettaskmgmntsystem.PetTaskMS.authorization.service.webtoken;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final long VALIDTIME = TimeUnit.MINUTES.toMillis(20);
    public String generateToken(UserDetails userDetails){
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("iss", "https://effective-mobile.ru/");
        return Jwts.builder()
                .claims(claimsMap)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDTIME)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey(){
        byte[] decodedKey = Base64.getDecoder().decode(ConstantsClass.SECRETKEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractEmailUser(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

}
