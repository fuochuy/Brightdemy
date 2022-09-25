package hcmus.brightdemy.security;

import hcmus.brightdemy.constant.Constant;
import hcmus.brightdemy.constant.SecurityConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(SecurityConstant.TOKEN_HEADER);
        String username = null;
        String authToken = null;

        if (header != null) {
            if (header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
                authToken = header.replace(SecurityConstant.TOKEN_PREFIX, " ");
                try {
                    username = tokenProvider.getUsernameFromToken(authToken);
                } catch (IllegalArgumentException e) {
                    log.error("CUSTOM LOGGER ----------- An error occurred during getting username and password: ", e);
                } catch (ExpiredJwtException e) {
                    log.error("CUSTOM LOGGER ----------- The token is expired and not valid anymore: ", e);
                } catch (SignatureException e) {
                    log.error("CUSTOM LOGGER ----------- Authentication failed. Username and password not valid: ", e);
                }
            }
        } else if (header.startsWith(SecurityConstant.TOKEN_PREFIX_SECRET)) {
            authToken = header.replace(SecurityConstant.TOKEN_PREFIX_SECRET, "");
            if (authToken.equals(SecurityConstant.TOKEN_SECRET)) {
                UserDetails user =userDetailsService.loadUserByUsername(Constant.ADMIN_NAME);
                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthenticationSecret(
                            authToken,
                            SecurityContextHolder.getContext().getAuthentication(),
                            user
                    );

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }else {
            log.warn("CUSTOM LOGGER ----------- Couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (!header.startsWith(SecurityConstant.TOKEN_PREFIX_SECRET)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (tokenProvider.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(
                            authToken,
                            SecurityContextHolder.getContext().getAuthentication(),
                            userDetails
                    );

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);

    }
}
