package capstone.sensor_api;

import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.services.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class SensorApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private SensorService sensorService;

	@Test
	public void testGetSensors() throws Exception {
		// given
		int page = 0;
		int size = 10;
		String sortBy = "id";
//		Page<Sensor> sensorPage = new PageImpl<>(Arrays.asList(new Sensor(), new Sensor()));
//		given(sensorService.find(page, size, sortBy)).willReturn(sensorPage);

		// when
		mockMvc.perform(get("/sensors")
						.param("page", String.valueOf(page))
						.param("size", String.valueOf(size))
						.param("sortBy", sortBy))
				.andDo(print());
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.content", hasSize(2)));
	}
}
