package capstone.fe_api.auth;


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

import java.time.Duration;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
//    @Autowired
//    JWTAuthFilter jwtAuthFilter;
//    @Autowired
//    ExceptionHandlerFilter exceptionHandlerFilter;
//
    @Autowired
    HeadersInterceptor headersInterceptor;
    @Autowired
    RestTemplateErrorHandler restTemplateErrorHandler;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());

//        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
//        http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/**").authenticated());
//        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ruoli/**").authenticated());
        http.authorizeRequests().anyRequest().permitAll();
        //http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(exceptionHandlerFilter, JWTAuthFilter.class);
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
