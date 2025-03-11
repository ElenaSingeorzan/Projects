package socialmedia.server;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private final String secretKey = "secret";
    private long validityInMilliseconds = 3600000; // 1 oră

    public String createToken(String email, String userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            System.err.println("JWT Token invalid: " + e.getMessage());
//            return false;
//        }
//    }
//
//    private boolean isTokenExpired(String token) {
//        return getExpirationDate(token).before(new Date());
//    }
//
//    private Date getExpirationDate(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
//    }

    public String getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userId", String.class); // Asigură-te că 'userId' este corect
        } catch (Exception e) {

            throw e;
        }
    }
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey) // Secretul pentru validarea semnăturii
                    .parseClaimsJws(token)
                    .getBody(); // Returnează claims (informațiile stocate în JWT)
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }
}

