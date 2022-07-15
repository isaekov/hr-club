package ru.hwru.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.hwru.integration.service.auth.UserAuthService;


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAuthService userAuthService;

//    @Bean
//    public BCryptPasswordEncoder bcryptEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public SecurityConfiguration(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/registration",
                        "/plugins/**",
                        "/dist/**",
                        "/img/**",
                        "/"
                ).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

}
