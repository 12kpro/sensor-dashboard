package capstone.fe_api.config;


import capstone.fe_api.auth.JWTAuthFilter;
import capstone.fe_api.exceptions.ExceptionHandlerFilter;
import capstone.fe_api.exceptions.RestTemplateErrorHandler;
import capstone.fe_api.utils.HeadersInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JWTAuthFilter jwtAuthFilter;
    @Autowired
    ExceptionHandlerFilter exceptionHandlerFilter;

    @Autowired
    HeadersInterceptor headersInterceptor;
    @Autowired
    RestTemplateErrorHandler restTemplateErrorHandler;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //TODO Attivare cors per localhost porta 3000 (frontend) e porta 5081 (Backend service)
        //http.cors(c -> c.disable());
        http.csrf(c -> c.disable());
        http.cors(c -> c.configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:5081"));
            cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            return cors;
        }));
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
        //TODO permettere accesso a utente con permessi USER
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/sensorevent").permitAll());
        //Ok impostato token per testare le API
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api-docs/**").permitAll());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/roles/**").authenticated());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/sensor/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/um/**").authenticated());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JWTAuthFilter.class);

//        http.authorizeRequests().anyRequest().permitAll();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
    @Bean
    PasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(60000))
                .setReadTimeout(Duration.ofMillis(60000))
                .additionalInterceptors(headersInterceptor)
                .errorHandler(restTemplateErrorHandler)
                .build();
    }
}
