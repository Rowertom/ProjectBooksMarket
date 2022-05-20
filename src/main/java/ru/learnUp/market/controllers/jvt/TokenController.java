package ru.learnUp.market.controllers.jvt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnUp.market.dao.service.JwtService;
import ru.learnUp.market.dao.service.UserService;
import ru.learnUp.market.dao.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.learnUp.market.dao.filters.jwt.JwtAuthorizationFilter.getToken;


@RestController
@RequestMapping("/api")
public class TokenController {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public TokenController(UserService userService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/api/tokenRefresh")
    public void auth(HttpServletRequest request,
                     HttpServletResponse response
    ) throws IOException {
        String token = getToken(request);
        String username = null;


        if (token != null) {
            username = jwtService.getUsernameFromRefreshToken(token);
            if (username != null) {
                final User user = userService.loadUserByUsername(username);
                if (user != null) {
                    final String accessToken = jwtService.generateAccessToken(request.getRequestURI(), user);
                    final String refreshToken = jwtService.generateRefreshToken(request.getRequestURI(), user);
                    response.setHeader("access_token", accessToken);
                    response.setHeader("refresh_token", refreshToken);
                    return;
                }
            }
        }

        response.sendRedirect("/api/auth");
    }
}
