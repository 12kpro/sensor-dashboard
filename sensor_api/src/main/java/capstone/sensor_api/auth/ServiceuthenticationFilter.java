package capstone.sensor_api.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class ServiceuthenticationFilter extends OncePerRequestFilter {

    @Value("${service.auth.header}")
    private String customHeader;

    @Value("${service.auth.secret}")
    private String customSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String headerValue = request.getHeader(customHeader);
        if (customSecret.equals(headerValue)) {
            filterChain.doFilter(request, response);

        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
