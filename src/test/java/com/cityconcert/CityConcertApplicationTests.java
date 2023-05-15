package com.cityconcert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc

class AdminControllerTests {
	@Autowired
	private MockMvc mockMvc;


	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testEventCreation() throws Exception {
		final ResultActions result =mockMvc.perform(post("/admin/event/add")
				.content("{\n" +
						"  \"id\": 0,\n" +
						"  \"name\": \"string\",\n" +
						"  \"startTime\": \"2023-05-15T15:17:39.989Z\",\n" +
						"  \"ticketLimit\": 0,\n" +
						"  \"status\": \"SELLING\",\n" +
						"  \"genreDescriptors\": \"string\",\n" +
						"  \"image\": [],\n" +
						"  \"description\": \"string\",\n" +
						"  \"venue\": 0")
				.contentType(MediaType.APPLICATION_JSON));
		result
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON));
	}


}
