package com.madad.case_registration;
import com.madad.case_registration.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllUser() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/user",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/user/1", User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("Aimen");
        user.setLastName("Ahmed");
        user.setPassword("Admin");
        user.setCnic("12345-1234433-0");
        user.setPhone("1234567898");
        user.setEmail("somenthing@gmail.com");
        user.setProvince("Karachi");
        user.setDistrict("defence");


        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/user", user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateUser() {
        int id = 1;
        User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        user.setFirstName("Ahmed");
        user.setLastName("Ahmed");
        user.setPassword("Admin");
        user.setCnic("12345-1234433-0");
        user.setPhone("1234567898");
        user.setEmail("somenthing@gmail.com");
        user.setProvince("Karachi");
        user.setDistrict("defence");
        restTemplate.put(getRootUrl() + "/user/" + id, user);

        User updatedUser = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void testDeleteUser() {
        int id = 2;
        User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        assertNotNull(user);

        restTemplate.delete(getRootUrl() + "/user/" + id);

        try {
            user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}