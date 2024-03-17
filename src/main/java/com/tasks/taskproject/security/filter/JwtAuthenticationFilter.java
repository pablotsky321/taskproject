package com.tasks.taskproject.security.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tasks.taskproject.security.services.JwtService;
import com.tasks.taskproject.security.services.UserDetailsServiceImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImp userDetailsServiceImp){
        this.jwtService = jwtService;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

                final String token = getTokenFromRequest(request);
                final String username;

                if(token == null){
                    filterChain.doFilter(request, response);
                    return;
                }

                username = jwtService.extractUsername(token);
                
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);

                    if(jwtService.isValid(token, userDetails)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }

                filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request){
        
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
    
        return null;

    }

}
