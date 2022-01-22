package at.commodussolutions.plentyentry.user.authentication.jwt;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenUtil {

    @Value("{$jwt.secret}")
    private String secret;



    public String generateJwtToken(User user) {
        String[] claims = getClaimsFromUser(user);
        return JWT.create().withIssuer(COMMODUS_SOLUTIONS_PLENTYENTRY).withAudience(PLENTYENTRY_ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(user.getId().toString()).withArrayClaim(AUTHORITIES, claims).withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
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
        return ( userName.equals(userDetails.getUsername()) && !isTokenExpired(verifier, token));
    }

    public String getSubject(String token) {
        JWTVerifier verifier =  getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        //JWTVerifier jwtVerifier = getJWTVerifier();
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                //jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = HMAC512(secret);
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
