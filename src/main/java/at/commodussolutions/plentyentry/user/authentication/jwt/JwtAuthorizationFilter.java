package at.commodussolutions.plentyentry.user.authentication.jwt;

import at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant.OPTIONS_HTTP_METHOD;
import static at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant.TOKEN_PREFIX;
import static com.sun.activation.registries.LogSupport.log;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getRemoteHost().equals("localhost")) {
            return true;
        }
        if (request.getRequestURL().toString().contains("/api/backend/event/list") || request.getRequestURL().toString().contains("/api/backend/user/register")) {
            if (request.getHeader(AUTHORIZATION).isEmpty() | request.getHeader(AUTHORIZATION).startsWith(TOKEN_PREFIX + "No Token")) {
                return true;
            }
        }
        return super.shouldNotFilter(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = null;
        String username = null;
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value());
        } else {

            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                jwtToken = authorizationHeader.substring(7);

                try {
                    username =  jwtTokenUtil.getSubject(jwtToken);
                }catch (IllegalArgumentException e) {
                    log(SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
                }catch (ExpiredJwtException e) {
                    log(SecurityConstant.TOKEN_EXPIRED);
                }

            }else {
                log("jwt token dont startsWith our TOKEN_PREFIX");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userService.loadUserByUsername(username);

                if(jwtTokenUtil.isTokenValid(userDetails, jwtToken)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken
                                    (userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }


    }
}
