package capstone.sensor_api;

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;

@Component
public class ApiKeyHeaderMockMvcBuilderCustomizer implements MockMvcBuilderCustomizer {
    @Override
    public void customize(ConfigurableMockMvcBuilder<?> builder) {
        RequestBuilder apiKeyRequestBuilder = MockMvcRequestBuilders.get("/")
                .header("X-Shared-Secret", "8dUJVrw9G1H8J*4cgZiJ!b7owIlmetoY1xLhbWHu!7EQivI*%O")
                .header("Origin", "http://localhost:5081");
        builder.defaultRequest(apiKeyRequestBuilder);
    }
}
