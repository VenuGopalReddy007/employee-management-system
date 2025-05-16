package com.example.Employee.Management.System.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(configure->configure.disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "/v3/api-docs/**")).permitAll()
                        //USERS Role-based access by only ADMIN
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        //EMPLOYEES Role-based access by only ADMIN-MANAGER
                        .requestMatchers("/api/employees/sendmail/**").hasAnyRole("ADMIN" ,"MANAGER")
                        .anyRequest().authenticated())

                .formLogin(f->f.successHandler(successHandler).permitAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(se-> se.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                .logout(l->l
                        .logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
                .headers(h->h.frameOptions(frame->frame.sameOrigin()));
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}
