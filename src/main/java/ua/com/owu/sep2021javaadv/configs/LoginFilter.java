package ua.com.owu.sep2021javaadv.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.owu.sep2021javaadv.dao.services.UserService;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.models.entity.AuthToken;
import ua.com.owu.sep2021javaadv.models.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    public LoginFilter(String url, AuthenticationManager authenticationManager, UserService userService) {
        setFilterProcessesUrl(url);
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        UserDTO dto = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
        // vasya 1111

        Authentication authenticate = getAuthenticationManager()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
                );
        System.out.println(authenticate);

        return authenticate;
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, "okten".getBytes())
                .compact();

        User user = userService.findUserByName(authResult.getName());
        AuthToken authToken = new AuthToken();
        authToken.setToken(jwtToken);
        authToken.setUser(user);
        System.out.println(jwtToken);
        user.getAuthTokens().add(authToken);
        userService.save(user);
        response.addHeader("Authorization", "Bearer " + jwtToken);
        chain.doFilter(request, response);


    }
}
