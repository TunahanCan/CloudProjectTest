package com.example.schoolservice;

import com.example.schoolservice.model.SchoolModel;
import com.example.schoolservice.repo.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
class SchoolServiceApplicationTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SchoolRepository schoolRepository;

    SchoolModel model1 = new SchoolModel("AtaturkLisesi" , "Ankara/Cankaya");;

    SchoolModel model2 = new SchoolModel("AnadoluLisesi" , "Ankara/Ulus");;

    SchoolModel model3 = new SchoolModel("FenLisesi" , "Bursa/Osmangazi");;

    SchoolModel model4 = new SchoolModel("MeslekLisesi" , "Istanbul/Esen");;






    @Test
    public void getAllRecords_success() throws Exception {
        List<SchoolModel> records = new ArrayList<>(Arrays.asList(model1, model2, model3 , model4));
        Mockito.when(schoolRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/school/getAllSchool")
                        .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlcyI6WyJST0xFX0FETUlOIl0sImlkIjoidHVuYWhhbiIsImp0aSI6ImViZmM5MjU2LThmYjctNDY4OS1hMzI2LTQ0MWFkOWRjMDEzOSIsInN1YiI6InR1bmFoYW4iLCJpYXQiOjE2NDcxMTIxNjcsImV4cCI6MTY0NzM3MTM2N30.vg5ZkOa6X9eICbHUmMqI29TMDbauvw5pzEoVFTp_O40")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].schoolName", is("AtaturkLisesi")));
    }





}
