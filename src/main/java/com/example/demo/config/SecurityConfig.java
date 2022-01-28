package com.example.demo.config;

import com.example.demo.security.AuthFilter;
import com.example.demo.security.AuthProvider;
import com.example.demo.security.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    private AuthFilter authFilter() throws Exception {
        AuthFilter filter = new AuthFilter(new AntPathRequestMatcher("/login", "GET"));
        filter.setAuthenticationManager(super.authenticationManager());
        filter.setAuthenticationSuccessHandler(new AuthSuccessHandler());

        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                .antMatchers("/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.httpBasic().disable();
        http.cors().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (req, resp, e) -> resp.sendRedirect("/")
                );

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
    }

}
