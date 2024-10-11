package hr.autorepair.shop.util;

import hr.autorepair.shop.role.dto.RoleResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final AppProperties appProperties;

    private static final String ID_APP_USER = "idAppUser";
    private static final String ROLE = "role";

    public String generateToken(Long idAppUser, String email, RoleResponse role){
        int expiration = Integer.parseInt(appProperties.getProperty("app.jwt.expiration"));
        return Jwts
                .builder()
                .id(UUID.randomUUID().toString())
                .subject(email)
                .claim(ID_APP_USER, idAppUser)
                .claim(ROLE, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return username.equals(userDetails.getUsername());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appProperties.getProperty("app.jwt.key"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
