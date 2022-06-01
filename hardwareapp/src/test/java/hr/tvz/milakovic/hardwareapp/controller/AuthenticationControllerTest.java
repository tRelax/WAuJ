package hr.tvz.milakovic.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.enums.HardwareType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    Map<String,Object> bodyAdmin = new HashMap<>();

    public void setBodyAdmin(Map<String, Object> bodyAdmin) {
        bodyAdmin.put("username", "admin");
        bodyAdmin.put("password", "admin");
        this.bodyAdmin = bodyAdmin;
    }

    public void setBodyAdminInvalid(Map<String, Object> bodyAdmin) {
        bodyAdmin.put("username", "admin");
        bodyAdmin.put("password", "aaabbb");
        this.bodyAdmin = bodyAdmin;
    }

    @Test
    void loginAdmin() throws Exception {
        setBodyAdmin(bodyAdmin);
        this.mockMvc.perform(
                        post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bodyAdmin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.jwt").isNotEmpty());
    }

    @Test
    void loginAdminInvalid() throws Exception {
        setBodyAdminInvalid(bodyAdmin);
        this.mockMvc.perform(
                        post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bodyAdmin)))
                .andExpect(status().isBadRequest());
    }
}