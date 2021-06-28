package com.example.patientrecord.utility;

import com.example.patientrecord.config.UserPrincipal;
import com.example.patientrecord.service.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JWTTokenFilter extends GenericFilterBean {


    private final JWTTokenProvider jwtTokenProvider;
    private final UserPrincipalService userPrincipalService;

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    public JWTTokenFilter(JWTTokenProvider jwtTokenProvider, UserPrincipalService userPrincipalService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userPrincipalService = userPrincipalService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token != null && jwtTokenProvider.validateToken(token)){
            String userName = jwtTokenProvider.getUsernameFromToken(token);
            UserPrincipal userPrincipal = userPrincipalService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearer = request.getHeader(AUTHORIZATION);
        if(hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
