package com.example.projectEmlak.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TODO: Proper authentication.
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());


//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable())
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                /*.antMatchers("/loginCustomer","/loginCompany","/registerCompanyPage", "/registerCustomerPage", "/createCompanyEstateAdPage", "/createCustomerEstateAdPage", "/searchEstateAds").permitAll()*/ // Allow unrestricted access to login and register
//                                .anyRequest().permitAll()/*authenticated()*/ // Require authentication for other endpoints
//                )
//                .formLogin(withDefaults()); // Use default login form

        return http.build();
    }

}
