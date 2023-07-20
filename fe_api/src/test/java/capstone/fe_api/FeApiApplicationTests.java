package capstone.fe_api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class FeApiApplicationTests {

}

//	@Test
//	void getClienti() throws Exception {
//
////		RequestBuilder requestBuilder = MockMvcRequestBuilders
////				.get("/clienti")
////				.header("Authorization", "Bearer " + loginToken);
//////				.accept(MediaType.APPLICATION_JSON).content(setLoginUserJson)
//////				.contentType(MediaType.APPLICATION_JSON);
////		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
////	MvcResult response = mockMvc
////			.perform(get("/clienti")
////					.header("Authorization", "Bearer " + loginToken)
////					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
////					.accept(MediaType.APPLICATION_JSON))
////			.andExpect(status().isOk())
////			.andReturn();
//
////	}
//}


//    mockMvc.perform(post("/users/reset_token")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(ResetPasswordTokenDTO))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.resetPasswordToken").value(randomString_254))
//			.andExpect(jsonPath("$.login").value("login"))
//			.andExpect(jsonPath("$.status").value("200"))
//			.andExpect(jsonPath("$.error").value(""))
//			.andExpect(jsonPath("$.errorDescription").value(""));

//	String token = "your_token_here";
//	MvcResult response = mockMvc
//			.perform(post("/auth/login")
//					.header("Authorization", "Bearer " + token)
//					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//					.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andReturn();