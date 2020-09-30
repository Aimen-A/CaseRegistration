package com.madad.case_registration;

import com.madad.case_registration.domain.Igp;
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
public class IgpControllerIntegrationTest {
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
    public void testGetAllIgp() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/igp",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetIgpById() {
        Igp igp = restTemplate.getForObject(getRootUrl() + "/igp/1", Igp.class);
        System.out.println(igp.getFirstName());
        assertNotNull(igp);
    }

    @Test
    public void testCreateIgp() {
        Igp igp = new Igp();
        igp.setProvince("province");
        igp.setFirstName("admin");
        igp.setLastName("admin");
        igp.setPassword("admin");


        ResponseEntity<Igp> postResponse = restTemplate.postForEntity(getRootUrl() + "/igp", igp, Igp.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateIgp() {
        int id = 1;
        Igp igp = restTemplate.getForObject(getRootUrl() + "/igp/" + id, Igp.class);
        igp.setFirstName("admin1");
        igp.setLastName("admin1");
        igp.setPassword("admin1");
        igp.setProvince("province");

        restTemplate.put(getRootUrl() + "/igp/" + id, igp);

        Igp updatedIgp = restTemplate.getForObject(getRootUrl() + "/igp/" + id, Igp.class);
        assertNotNull(updatedIgp);
    }

    @Test
    public void testDeleteIgp() {
        int id = 2;
        Igp igp = restTemplate.getForObject(getRootUrl() + "/igp/" + id, Igp.class);
        assertNotNull(igp);

        restTemplate.delete(getRootUrl() + "/igp/" + id);

        try {
            igp = restTemplate.getForObject(getRootUrl() + "/igp/" + id, Igp.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}