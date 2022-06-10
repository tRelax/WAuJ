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
class HardwareControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDk1MzUxMSwiaWF0IjoxNjU0MzQ4NzExLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.-Ci1RqQw8kH5M0MVLrOo79A32mCsUpmHYAdsaB_0MNEZ8S6B_Jk9NQ0nr8l1b9lKQ4LgT1AJ-wxjVLbFJdzhrw";
    String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU0NjAwNDY0LCJpYXQiOjE2NTM5OTU2NjQsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.yAIF2wVtdrspsCwf1d2CCponH6_t5T2nyXUAMvsYcRx6W_l3dPXcaJFK-F5MVKyRM3GaSjW5Z4AQLWONMRDTSQ";
    final Long TEST_ID = 1L;
    final String TEST_CODE = "000";
    final String TEST_NAME = "testName";
    final Double TEST_PRICE = 1000.00;
    final HardwareType TEST_TYPE = HardwareType.CPU;
    final Integer TEST_AVAILABLE = 1000;

    HardwareCommand hardwareCommand = new HardwareCommand(TEST_ID, TEST_CODE, TEST_NAME, TEST_PRICE, TEST_TYPE, TEST_AVAILABLE);
    HardwareCommand invalidHardwareCommand = new HardwareCommand(TEST_ID, TEST_CODE, TEST_NAME, null, TEST_TYPE, TEST_AVAILABLE);

    @Test
    void findAllHardware() throws Exception {
        this.mockMvc.perform
                    (get("/hardware")
                    .with(user("admin").password("admin").roles("ADMIN"))
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void findAllHardwareByCode() throws Exception {
        this.mockMvc.perform
                    (get("/hardware/001")
                    .with(user("admin").password("admin").roles("ADMIN"))
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void findAllHardwareByCodeInvalid() throws Exception {
        this.mockMvc.perform
                    (get("/hardware/111")
                    .with(user("admin").password("admin").roles("ADMIN"))
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
                .andExpect(status().isNotFound());
    }

    @Test
    void findHardwareById() throws Exception {
        this.mockMvc.perform
                    (
                    get("/hardware")
                    .with(user("admin").password("admin").roles("ADMIN"))
                    .param("id", "1")
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void findHardwareByIdInvalid() throws Exception {
        this.mockMvc.perform
                    (
                    get("/hardware")
                    .with(user("admin").password("admin").roles("ADMIN"))
                    .param("id", "7")
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    )
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void save() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.available").value(TEST_AVAILABLE));
    }

    @Test
    @Transactional
    void saveInvalid() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(invalidHardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void update() throws Exception {
        this.mockMvc.perform(
                        put("/hardware/1")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void updateInvalid() throws Exception {
        this.mockMvc.perform(
                        put("/hardware/8")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidHardwareCommand))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void delete_roleAdmin() throws Exception {
        this.mockMvc.perform(
                delete("/hardware/001")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .with(csrf())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}