package ru.clevertec.backendtest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getReceiptShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/check?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234")).andExpect(status().isOk());
    }

    @Test
    void getReceiptWithoutParamsShouldReturnBadRequestStatus() throws Exception {
        this.mockMvc.perform(get("/check")).andExpect(status().isBadRequest());
    }

    @Test
    void getReceiptViewShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/check/view?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234")).andExpect(status().isOk());
    }

    @Test
    void getReceiptViewWithoutParamsShouldReturnBadRequestStatus() throws Exception {
        this.mockMvc.perform(get("/check/view")).andExpect(status().isBadRequest());
    }
}