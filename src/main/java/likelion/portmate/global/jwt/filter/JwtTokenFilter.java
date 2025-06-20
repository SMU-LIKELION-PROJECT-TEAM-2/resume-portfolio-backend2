package likelion.portmate.global.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion.portmate.global.auth.controller.exception.AuthenticationRequiredException;
import likelion.portmate.global.auth.controller.exception.RefreshTokenNotValidException;
import likelion.portmate.global.auth.service.RefreshTokenService;
import likelion.portmate.global.jwt.resolver.JwtTokenResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenResolver tokenResolver;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        processTokenAuthentication(request);
        filterChain.doFilter(request, response);
    }

    private void processTokenAuthentication(
            HttpServletRequest request
    ) {
        try {
            String token = resolveTokenFromRequest(request);
            setAuthentication(request, getUserDetails(token, request));
        } catch (ExpiredJwtException | AuthenticationRequiredException e) {
            log.debug("Failed to authenticate", e);
        } catch (RefreshTokenNotValidException e) {
            log.warn("Failed to authenticate", e);
        } catch (Exception e) {
            log.error("Failed to authenticate", e);
        }
    }

    private String resolveTokenFromRequest(
            HttpServletRequest request
    ) {
        try {
            return tokenResolver.resolveTokenFromRequest(request)
                    .orElseGet(() -> refreshTokenService.reissueBasedOnRefreshToken(request).accessToken());
        } catch (ExpiredJwtException e) {
            return refreshTokenService.reissueBasedOnRefreshToken(request).accessToken();
        }
    }

    private UserDetails getUserDetails(
            String token,
            HttpServletRequest request
    ) {
        try {
            String subject = tokenResolver.getSubjectFromToken(token);
            return userDetailsService.loadUserByUsername(subject);
        } catch (ExpiredJwtException e) {
            String accessToken = refreshTokenService.reissueBasedOnRefreshToken(request).accessToken();
            String subject = tokenResolver.getSubjectFromToken(accessToken);
            return userDetailsService.loadUserByUsername(subject);
        }
    }

    private void setAuthentication(
            HttpServletRequest request,
            UserDetails userDetails
    ) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
