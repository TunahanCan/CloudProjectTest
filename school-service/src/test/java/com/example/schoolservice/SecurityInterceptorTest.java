package com.example.schoolservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityInterceptorTest {

    public static final String TEST_URI = "/school/test/info";
    public static final String RESPONSE = "test";

    @Test
    void contextLoads() {
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testInterceptor_is_requested() throws Exception {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(TEST_URI, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotEmpty();
    }


    @Test
    public void getAppNameService_test() throws Exception {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(TEST_URI, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotEmpty();
    }


}
