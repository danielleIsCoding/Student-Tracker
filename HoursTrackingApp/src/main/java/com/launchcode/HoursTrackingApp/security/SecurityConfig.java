package com.launchcode.HoursTrackingApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/*","/css/*").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/**").hasRole("USER").and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/students")
                .permitAll().and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
