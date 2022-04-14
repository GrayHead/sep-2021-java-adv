package ua.com.owu.sep2021javaadv.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import ua.com.owu.sep2021javaadv.dao.AuthTokenDAO;
import ua.com.owu.sep2021javaadv.dao.services.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private DaoAuthenticationProvider daoAuthenticationProvider;
    private CorsConfigurationSource corsConfigurationSource;
    private UserService userService;
    private AuthTokenDAO authTokenDAO;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/", "/hello").permitAll()
                .antMatchers(HttpMethod.POST, "/save", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/securityURL", "/getInfo", "/admin/user/**").hasAnyRole("USER")
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager(), userService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new RequestsProcessingFilter(authTokenDAO), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
