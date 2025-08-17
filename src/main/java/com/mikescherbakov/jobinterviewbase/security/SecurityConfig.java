package com.mikescherbakov.jobinterviewbase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

  public SecurityConfig(ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) {
    this.apiKeyAuthenticationFilter = apiKeyAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/discover")
                    .hasRole("API")
                    .requestMatchers("/time")
                    .authenticated()
                    .requestMatchers("/login", "/error", "/css/**", "/js/**", "/images/**")
                    .permitAll()
                    .anyRequest()
                    .permitAll())
        .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/", false))
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(
            exceptions ->
                exceptions
                    .authenticationEntryPoint(
                        (request, response, authException) ->
                            response.sendRedirect("/oauth2/authorization/google"))
                    .accessDeniedHandler(
                        (request, response, accessDeniedException) -> {
                          if (request.getRequestURI().equals("/discover")) {
                            response.sendError(403, "Forbidden - API Key Required");
                          } else {
                            response.sendError(403, "Access Denied");
                          }
                        }));

    return http.build();
  }
}
