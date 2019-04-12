package com.itemsSharing.config;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import com.itemsSharing.jwt.JwtTokenAuthenticationConfig;
import com.itemsSharing.jwt.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@ComponentScan("com.itemsSharing")
@Order(3)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtTokenAuthenticationConfig config;

    private String prfix = "/api/invitation";

    @Bean
    public JwtTokenAuthenticationConfig jwtConfig() {
/*        JwtAuthenticationConfig jwtAuthenticationConfig = new JwtAuthenticationConfig();
        return jwtAuthenticationConfig();*/
        return new JwtTokenAuthenticationConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
        // return new BCryptPasswordEncoder();
    }

    @Autowired
    DataSource dataSource;

    // Enable jdbc authentication
    // Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .anonymous()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(config.getUrl()).permitAll()
                .antMatchers("/staffUsers/addNewUser").hasRole("ADMIN")
                .antMatchers("/staffUsers/addStaffUserFirstTime").permitAll()
                .antMatchers("/staffUsers/login/**").permitAll()
                .anyRequest().permitAll();
    }

}