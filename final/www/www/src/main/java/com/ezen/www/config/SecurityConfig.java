package com.ezen.www.config;

import com.ezen.www.handler.CustomAuthFailureHandler;
import com.ezen.www.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize
                        -> authorize
                        .anyRequest().permitAll())  // 모든 요청을 허용
                .build();*/


        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers
                                ("/", "/index", "/js/**", "/dist/**",
                        "/member/login", "/member/registerpolicy", "/member/register",
                                        "/test/information", "/test/pay"
                        )
                        .permitAll()
                        .requestMatchers("/member/admin_page").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login
                        -> login.usernameParameter("id")
                        .passwordParameter("pwd")
                        .loginPage("/member/login")
                        .failureHandler(customAuthenticationFailureHandler())
                        .defaultSuccessUrl("/").permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/member/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                ).build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserService();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthFailureHandler();
    }

}
