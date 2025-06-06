package likelion.portmate.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;   // ✨추가
import io.jsonwebtoken.security.Keys; // ✨추가
import java.security.Key;             // ✨추가

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") private String secret;

    private Key key;

    private final long ACCESS_TOKEN_EXP = 60 * 60 * 1000; // 1시간
    private final long REFRESH_TOKEN_EXP = 7 * 24 * 60 * 60 * 1000L; // 1주일

    @PostConstruct
    private void initKey() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String createAccessToken(Long id, String sub) {
        return token(id, sub, ACCESS_TOKEN_EXP);
    }

    public String createRefreshToken(Long id, String sub) {
        return token(id, sub, REFRESH_TOKEN_EXP);
    }

    private String token(Long id, String sub, long exp) {
        return Jwts.builder()
                .setSubject(sub)
                .claim("id", id)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ key 객체 활용
                .compact();
    }
}