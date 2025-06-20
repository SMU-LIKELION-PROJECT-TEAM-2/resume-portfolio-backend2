package likelion.portmate.global.jwt.generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import likelion.portmate.global.jwt.config.TokenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.io.Decoders.BASE64;

@RequiredArgsConstructor
@Component
public class JwtTokenGenerator {

    private final TokenProperties tokenProperties;

    public String generateAccessToken(Long memberId) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + tokenProperties.expirationTime().accessToken() * 1000);
        SecretKey secretKey = Keys.hmacShaKeyFor(BASE64.decode(tokenProperties.secret()));

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(Long memberId) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        SecretKey secretKey = Keys.hmacShaKeyFor(BASE64.decode(tokenProperties.secret()));

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .issuedAt(now)
                .signWith(secretKey)
                .compact();
    }

}
