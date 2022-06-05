package com.example.studentservice;

import com.example.studentservice.controller.StudentController;
import com.example.studentservice.model.StudentModel;
import com.example.studentservice.repo.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class StudentServiceApplicationTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    StudentModel model1 = new StudentModel("Samantha" , 19 , "AtaturkLisesi") ;

    StudentModel model2 = new StudentModel("Uriq" , 19 ,  "AnadoluLisesi") ;

    StudentModel model3 = new StudentModel("Cristiano" , 19 , "AnadoluLisesi") ;

    StudentModel model4 = new StudentModel("Robben" , 20 , "AnadoluLisesi") ;


    @Test
    public void getAllRecords_success() throws Exception {
        List<StudentModel> records = new ArrayList<>(Arrays.asList(model1, model2, model3 , model4));
        Mockito.when(studentRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAllStudent")
                        .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlcyI6WyJST0xFX0FETUlOIl0sImlkIjoidHVuYWhhbiIsImp0aSI6ImViZmM5MjU2LThmYjctNDY4OS1hMzI2LTQ0MWFkOWRjMDEzOSIsInN1YiI6InR1bmFoYW4iLCJpYXQiOjE2NDcxMTIxNjcsImV4cCI6MTY0NzM3MTM2N30.vg5ZkOa6X9eICbHUmMqI29TMDbauvw5pzEoVFTp_O40")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].studentName", is("Samantha")));
    }










}
