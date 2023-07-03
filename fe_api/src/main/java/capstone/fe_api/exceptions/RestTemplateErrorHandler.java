package capstone.fe_api.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
@Slf4j
@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseBody = new String(FileCopyUtils.copyToByteArray(response.getBody()));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);
        String message = root.path("message").asText();
        if (response.getStatusCode().is4xxClientError()) {
            if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException(message);
            } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new UnauthorizedException(message);
            }
        } else {
            super.handleError(response);
        }
    }
}
