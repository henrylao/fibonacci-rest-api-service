package com.example.hcl.service.fibonacci;

import com.example.hcl.service.fibonacci.FibonacciController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@WebMvcTest(FibonacciController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class FibonacciTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllFibonacci() throws Exception {
        this.mockMvc.perform(get("/api/v1/fibonacci/all"))
                .andExpect(status().isOk())
                .andDo(document("GET all fibonacci"));
    }

    @Test
    public void getAFibonacci() throws Exception {
        this.mockMvc.perform(get("/api/v1/fibonacci/get/?={id}", 5))
                .andExpect(status().isOk())
                .andDo(document("GET a fibonacci",
                        pathParameters(parameterWithName("id").description("The id of the input defining the position of " +
                                "a Fibonacci number in series"))));
    }

}