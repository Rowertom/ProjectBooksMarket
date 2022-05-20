package ru.learnUp.market.dao.filters.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.learnUp.market.dao.service.JwtService;
import ru.learnUp.market.dao.user.User;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;;
    private final String successRedirectUrl;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.successRedirectUrl = "/resource";
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        final String accessToken = jwtService.generateAccessToken(request.getRequestURI(), user);
        final String refreshToken = jwtService.generateRefreshToken(request.getRequestURI(), user);
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
    }
}
