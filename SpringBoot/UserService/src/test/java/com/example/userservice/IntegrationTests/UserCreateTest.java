package com.example.userservice.IntegrationTests;


import com.example.userservice.UserServiceApplication;
import com.example.userservice.models.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class UserCreateTest {


    @Test
    public void testCreateUser() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8081/user/create?firstName=RandomName1&lastName=RandomLastName",
                HttpMethod.POST, entity, String.class);

        assertTrue(response.hasBody());
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
