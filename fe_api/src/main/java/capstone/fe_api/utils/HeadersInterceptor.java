package capstone.fe_api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


public class HeadersInterceptor implements ClientHttpRequestInterceptor {
    @Value("${service.key}")
    private String serviceKey;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("X-Shared-Secret", serviceKey);
        return execution.execute(request, body);
    }
}
