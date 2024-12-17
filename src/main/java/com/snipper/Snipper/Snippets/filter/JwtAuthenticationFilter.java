package com.snipper.Snipper.Snippets.filter;

//Secure API endpoints

import com.snipper.Snipper.Snippets.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletRequest response, FilterChain filterChain)
            throws ServletException, IOException{
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            String email = JwtUtil.validateToken(token);
            if(email==null){
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
