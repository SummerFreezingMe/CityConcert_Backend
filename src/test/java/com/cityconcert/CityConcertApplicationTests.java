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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration() throws Exception {
        final ResultActions result = mockMvc.perform(post("/registration")
                .content("{\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"email\": \"string@gmail.com\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"passwordConfirm\": \"string\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testLogin() throws Exception {
        final ResultActions result = mockMvc.perform(get("/login?username=test&password=test")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testAddUser() throws Exception {
        final ResultActions result = mockMvc.perform(post("/users/add")
                .content("{\n" +
                        " \"id\": 0,\n" +
                        " \"username\": \"string\",\n" +
                        " \"email\": \"string\",\n" +
                        " \"password\": \"string\",\n" +
                        " \"passwordConfirm\": \"string\",\n" +
                        " \"role\": \"string\",\n" +
                        " \"image\": [\n" +
                        " \"string\"\n" +
                        " ]\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testGetUserById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/users/get/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testGetCurrentUser() throws Exception {
        final ResultActions result = mockMvc.perform(get("/users/current")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testUpdateUser() throws Exception {
        final ResultActions result = mockMvc.perform(put("/users/update/{id}", 1)
                .content("{\n" +
                        " \"id\": 0,\n" +
                        " \"username\": \"string\",\n" +
                        " \"email\": \"string\",\n" +
                        " \"password\": \"string\",\n" +
                        " \"passwordConfirm\": \"string\",\n" +
                        " \"role\": \"string\",\n" +
                        " \"image\": [\n" +
                        " \"string\"\n" +
                        " ]\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testDeleteUser() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/users/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllVenues() throws Exception {
        final ResultActions result = mockMvc.perform(get("/venue/get_all")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetVenueById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/venue/get/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Venue 1"))
                .andExpect(jsonPath("$.address").value("Address 1"));
    }
}
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class RequestControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testAddRequest() throws Exception {
        final ResultActions result = mockMvc.perform(post("/request/add")
                .content("{\n" +
                        " \"userId\": 0,\n" +
                        " \"eventId\": 0,\n" +
                        " \"requestType\": \"EXCHANGE\",\n" +
                        " \"description\": \"string\",\n" +
                        " \"currentSeat\": \"string\",\n" +
                        " \"wantedSeat\": \"string\",\n" +
                        " \"seatFromUser\": \"string\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(0))
                .andExpect(jsonPath("$.eventId").value(0))
                .andExpect(jsonPath("$.requestType").value("EXCHANGE"))
                .andExpect(jsonPath("$.description").value("string"))
                .andExpect(jsonPath("$.currentSeat").value("string"))
                .andExpect(jsonPath("$.wantedSeat").value("string"))
                .andExpect(jsonPath("$.seatFromUser").value("string"));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testGetAllRequests() throws Exception {
        final ResultActions result = mockMvc.perform(get("/request/get_all")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testGetRequestById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/request/get/{id}", 25L)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    void testDeleteRequestById() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/request/delete/{id}", 24L)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/request/get/{id}", 24)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("null"));
    }
}
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSendTicketMail() throws Exception {
        final ResultActions result = mockMvc.perform(post("/ticket/mail")
                .content("{\n" +
                        " \"id\": 0,\n" +
                        " \"price\": 0,\n" +
                        " \"seat\": \"string\",\n" +
                        " \"status\": \"AVAILABLE\",\n" +
                        " \"purchaseDate\": \"2023-05-20T23:19:09.820Z\",\n" +
                        " \"userId\": 0,\n" +
                        " \"eventId\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.seat").value("string"))
                .andExpect(jsonPath("$.status").value("AVAILABLE"))
                .andExpect(jsonPath("$.purchaseDate").value("2023-05-20T23:19:09.820Z"))
                .andExpect(jsonPath("$.userId").value(0))
                .andExpect(jsonPath("$.eventId").value(0));
    }

    @Test
    void testExchangeTicket() throws Exception {
        final ResultActions result = mockMvc.perform(post("/ticket/exchange")
                .content("{\n" +
                        " \"userId\": 0,\n" +
                        " \"eventId\": 0,\n" +
                        " \"requestType\": \"EXCHANGE\",\n" +
                        " \"description\": \"string\",\n" +
                        " \"currentSeat\": \"string\",\n" +
                        " \"wantedSeat\": \"string\",\n" +
                        " \"seatFromUser\": \"string\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(0))
                .andExpect(jsonPath("$.eventId").value(0))
                .andExpect(jsonPath("$.requestType").value("EXCHANGE"))
                .andExpect(jsonPath("$.description").value("string"))
                .andExpect(jsonPath("$.currentSeat").value("string"))
                .andExpect(jsonPath("$.wantedSeat").value("string"))
                .andExpect(jsonPath("$.seatFromUser").value("string"));
    }

    @Test
    void testAddTicket() throws Exception {
        final ResultActions result = mockMvc.perform(post("/ticket/add")
                .content("{\n" +
                        " \"id\": 0,\n" +
                        " \"price\": 0,\n" +
                        " \"seat\": \"string\",\n" +
                        " \"status\": \"AVAILABLE\",\n" +
                        " \"purchaseDate\": \"2023-05-20T23:19:51.993Z\",\n" +
                        " \"userId\": 0,\n" +
                        " \"eventId\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.seat").value("string"))
                .andExpect(jsonPath("$.status").value("AVAILABLE"))
                .andExpect(jsonPath("$.purchaseDate").value("2023-05-20T23:19:51.993Z"))
                .andExpect(jsonPath("$.userId").value(0))
                .andExpect(jsonPath("$.eventId").value(0));
    }

    @Test
    void testGetUserTickets() throws Exception {
        final ResultActions result = mockMvc.perform(get("/ticket/user/{userId}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].eventId").value(2))
                .andExpect(jsonPath("$[0].seat").value("A1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andExpect(jsonPath("$[1].eventId").value(3))
                .andExpect(jsonPath("$[1].seat").value("B2"));
    }

//    @Test
//    void testGet

    @Test
    void testGetTicketById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/ticket/get/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.eventId").value(2))
                .andExpect(jsonPath("$.seat").value("A1"));
    }

    @Test
    void testBuyTicket() throws Exception {
        final ResultActions result = mockMvc.perform(post("/ticket/buy/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("SOLD"));
    }

    @Test
    void testDeleteTicket() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/ticket/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Ticket deleted successfully"));
    }
}
@SpringBootTest(classes = CityConcertApplication.class)
@AutoConfigureMockMvc
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testFilterEventsByPrice() throws Exception {
        final ResultActions result = mockMvc.perform(get("/event/filter_by_price")
                .param("minPrice", "0")
                .param("maxPrice", "100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void testFilterEventsByGenre() throws Exception {
        final ResultActions result = mockMvc.perform(post("/event/filter_by_genre")
                .content("[\"Rock\"]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFilterEventsByDate() throws Exception {
        final ResultActions result = mockMvc.perform(get("/event/filter_by_date")
                .content("{ \"start_date\": \"2023-05-20T23:33:40.407Z\", \"end_date\": \"2023-05-20T23:33:40.407Z\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFilterEvents() throws Exception {
        final ResultActions result = mockMvc.perform(post("/event/filter")
                .content("{ \"additionalProp1\": {}, \"additionalProp2\": {}, \"additionalProp3\": {} }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllEvents() throws Exception {
        final ResultActions result = mockMvc.perform(get("/event/get_all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetEventById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/event/get/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFilterEventsByName() throws Exception {
        final ResultActions result = mockMvc.perform(get("/event/filter_by_name/{name}", "Deftones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}