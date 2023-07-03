package capstone.sensor_api;

import capstone.sensor_api.sensors.services.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SensorApiApplicationTests {

	private String serviceUrl = "http://localhost:5081";
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testGetSensorsStatusOk() throws Exception {
		String servicePath = "/sensor";
		int page = 0;
		int size = 10;
		String sortBy = "id";
		mockMvc.perform(get(serviceUrl + servicePath )
						.param("page", String.valueOf(page))
						.param("size", String.valueOf(size))
						.param("sortBy", sortBy))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}

