package org.AndrePerezGarza.API.Project.delivery.Controller.Configuration;


import org.AndrePerezGarza.API.Project.delivery.Controller.Handlers.AccessDeniedHandler;
import org.AndrePerezGarza.API.Project.delivery.Controller.Handlers.AuthenticationEntryPointHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Value("${spring.security.debug:false}")
    boolean securityDebug;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests().
                requestMatchers(HttpMethod.DELETE).hasRole("ADMIN").
                requestMatchers(HttpMethod.GET).permitAll().
                requestMatchers(HttpMethod.POST).hasRole("OWNER").
                requestMatchers(HttpMethod.PUT).hasRole("OWNER").
                anyRequest().authenticated().
                and().httpBasic().
                and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointHandler()).
                and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler());
                             ;

        return http.build();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(securityDebug)
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }



}
