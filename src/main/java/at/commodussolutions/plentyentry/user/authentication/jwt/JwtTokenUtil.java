package at.commodussolutions.plentyentry.user.authentication.jwt;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

import static at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;


    @Autowired
    private Environment env;


    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(COMMODUS_SOLUTIONS_PLENTYENTRY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Base64.encodeBase64(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        try {
            return claimResolver.apply(claims);
        } catch (NullPointerException pointerException) {
            return null;
        }


    }

    public String getUsernameFromToken(String token) {
        if (env.acceptsProfiles(Profiles.of("test"))) {
            return "test-mode";
        }
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid (UserDetails userDetails, String token) {
        String userName = getUsernameFromToken(token);
        JWTVerifier verifier = getJWTVerifier();
        try {
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(verifier, token));
        } catch (NullPointerException nullPointerException) {
            return false;
        }
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();

        try {
            return verifier.verify(token).getSubject();
        } catch (TokenExpiredException expiredException) {
            return "Session ist abgelaufen - bitte melden Sie sich nochmals an.";
        }


    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {

        try {
            return Jwts.parser().setSigningKey(Base64.encodeBase64(secret.getBytes(StandardCharsets.UTF_8))).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new BadRequestException("Session ist abgelaufen - bitte melden Sie sich nochmals an.");
        }

        /*
        return Jwts.parser().setSigningKey(Base64.encodeBase64(secret.getBytes(StandardCharsets.UTF_8))).parseClaimsJws(token).getBody();

         */
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = HMAC512(Base64.encodeBase64(secret.getBytes(StandardCharsets.UTF_8)));
            jwtVerifier = JWT.require(algorithm).withIssuer(COMMODUS_SOLUTIONS_PLENTYENTRY).build();
        }catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return jwtVerifier;
    }

    private String[] getClaimsFromUser(User user) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }



}
