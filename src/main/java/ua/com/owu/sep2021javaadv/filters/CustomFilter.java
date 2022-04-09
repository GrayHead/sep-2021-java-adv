package ua.com.owu.sep2021javaadv.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.models.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        /*Conflict with requestBody Annotation!*/
//        UserDTO dto = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UserDTO.class);
//        System.out.println(dto);
        String xxx = httpServletRequest.getHeader("xxx");
        System.out.println(xxx);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.addHeader("responseCustomHeader","some response");


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
