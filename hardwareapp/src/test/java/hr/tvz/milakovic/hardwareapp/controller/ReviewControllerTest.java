package hr.tvz.milakovic.hardwareapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.enums.HardwareType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ReviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwMDI3NCwiaWF0IjoxNjUzOTk1NDc0LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.-o2xZ80fOHQrl8XQNH9plPVd8WMpWEGc5cbzsn2hif121wL2VV-gieK-X5-JILSQFQkLS9Azl99s58cELb53dA";
    String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU0NjAwNDY0LCJpYXQiOjE2NTM5OTU2NjQsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.yAIF2wVtdrspsCwf1d2CCponH6_t5T2nyXUAMvsYcRx6W_l3dPXcaJFK-F5MVKyRM3GaSjW5Z4AQLWONMRDTSQ";

    @Test
    void getAllReviews() throws Exception {
        this.mockMvc.perform
                    (get("/review")
                            .with(user("admin").password("admin").roles("ADMIN"))
                            .with(csrf())
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getAllReviewsByHardwareCode() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .param("code", "001")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getReviewById() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .param("id", "1")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getReviewByIdInvalid() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .param("id", "9")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isNotFound());
    }
}