package com.cityconcert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testEventCreation() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/event/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"startTime\": \"2023-05-15T15:17:39.989Z\",\n" +
                        "  \"ticketLimit\": 0,\n" +
                        "  \"status\": \"SELLING\",\n" +
                        "  \"genreDescriptors\": \"string\",\n" +
                        "  \"image\": [],\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"venue\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testEventCreationWithWrongData() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/event/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEventCreationWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/event/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"startTime\": \"2023-05-15T15:17:39.989Z\",\n" +
                        "  \"ticketLimit\": 0,\n" +
                        "  \"status\": \"SELLING\",\n" +
                        "  \"genreDescriptors\": \"string\",\n" +
                        "  \"image\": [],\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"venue\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testEventUpdate() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/event/update")
                .content("{\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"startTime\": \"2023-05-15T15:17:39.989Z\",\n" +
                        "  \"ticketLimit\": 0,\n" +
                        "  \"status\": \"SELLING\",\n" +
                        "  \"genreDescriptors\": \"string\",\n" +
                        "  \"image\": [],\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"venue\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testEventUpdateWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/event/update")
                .content("{\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"startTime\": \"2023-05-15T15:17:39.989Z\",\n" +
                        "  \"ticketLimit\": 0,\n" +
                        "  \"status\": \"SELLING\",\n" +
                        "  \"genreDescriptors\": \"string\",\n" +
                        "  \"image\": [],\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"venue\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testEventDelete() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/admin/event/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk());
    }

    @Test
    void testEventDeleteWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/admin/event/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testVenueCreation() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/venue/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"location\": \"string\",\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"capacity\": 0,\n" +
                        "  \"image\": []\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testVenueCreationWithWrongData() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/venue/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testVenueCreationWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/venue/add")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"location\": \"string\",\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"capacity\": 0,\n" +
                        "  \"image\": []\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testVenueUpdate() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/venue/update")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"location\": \"string\",\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"capacity\": 0,\n" +
                        "  \"image\": []\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testVenueUpdateWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(post("/admin/venue/update")
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"location\": \"string\",\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"capacity\": 0,\n" +
                        "  \"image\": []\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testVenueDelete() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/admin/venue/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk());
    }

    @Test
    void testVenueDeleteWithoutPermission() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/admin/venue/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isFound());
    }


    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testGetUsers() throws Exception {
        final ResultActions result = mockMvc.perform(get("/admin/users"));
        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

	@Test
	void testGetUsersWithoutPermission() throws Exception {
		final ResultActions result = mockMvc.perform(get("/admin/users"));
		result
				.andExpect(status().isFound());
	}
}
