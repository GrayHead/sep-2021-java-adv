package ua.com.owu.sep2021javaadv.configs;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ua.com.owu.sep2021javaadv.dao.AuthTokenDAO;
import ua.com.owu.sep2021javaadv.models.entity.AuthToken;
import ua.com.owu.sep2021javaadv.models.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

public class RequestsProcessingFilter extends GenericFilterBean {
    private AuthTokenDAO authTokenDAO;

    public RequestsProcessingFilter(AuthTokenDAO authTokenDAO) {
        this.authTokenDAO = authTokenDAO;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorization = request.getHeader("Authorization");
        System.out.println("ihgjdshgsd");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            System.out.println("urtfdsfdshg");
            String bearer = authorization.replace("Bearer ", "");
            AuthToken userToken = authTokenDAO.findAuthTokenByToken(bearer);
            User user = userToken.getUser();
            if (user != null) {
                String username = user.getUsername();
                String password = user.getPassword();
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                UsernamePasswordAuthenticationToken authenticationObject = new UsernamePasswordAuthenticationToken(username, password, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationObject);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
