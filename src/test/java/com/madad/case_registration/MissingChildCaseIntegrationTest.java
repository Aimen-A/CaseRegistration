package com.madad.case_registration;

import com.madad.case_registration.domain.MissingChildCase;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissingChildCaseIntegrationTest {
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
    public void testGetAllMissingChildCase() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/missingchildcase",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetMissingChildCaseById() {
        MissingChildCase missingchildcase = restTemplate.getForObject(getRootUrl() + "/missingchildcase/1", MissingChildCase.class);
        System.out.println(missingchildcase.getFirstName());
        assertNotNull(missingchildcase);
    }

//    @Test
//    public void testCreateMissingChildCase() {
//        MissingChildCase missingchildcase = new MissingChildCase();
//        missingchildcase.setProvinceTerritory("province");
//        missingchildcase.setFirstName("admin");
//        missingchildcase.setLastName("admin");
//        missingchildcase.setPassword("admin");
//
//
//        ResponseEntity<MissingChildCase> postResponse = restTemplate.postForEntity(getRootUrl() + "/missingchildcase", missingchildcase, MissingChildCase.class);
//        assertNotNull(postResponse);
//        assertNotNull(postResponse.getBody());
//    }

    @Test
    public void testUpdateMissingChildCase() {
        int id = 1;
        MissingChildCase missingchildcase = restTemplate.getForObject(getRootUrl() + "/missingchildcase/" + id, MissingChildCase.class);
        missingchildcase.setFirstName("admin");
        missingchildcase.setLastName("admin");
        missingchildcase.setAge(10);
//        String date=("22-01-1998");
//        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            missingchildcase.setDob(sdf.parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        missingchildcase.setEyeColor("adminnnn");
        missingchildcase.setGender("adminnnn");
        missingchildcase.setHairColor("adminnnn");
        missingchildcase.setHeight(5.52);
        missingchildcase.setWeight(50);
        missingchildcase.setLanguage("adminnnn");
        missingchildcase.setLastSeenClothes("adminnnn");
        missingchildcase.setLastSeenProvince("adminnnn");
//        String date2=("22-01-1998");
//        SimpleDateFormat sdf2=new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            missingchildcase.setLastSeenDate(sdf2.parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        restTemplate.put(getRootUrl() + "/missingchildcase/" + id, missingchildcase);

        MissingChildCase updatedMissingChildCase = restTemplate.getForObject(getRootUrl() + "/missingchildcase/" + id, MissingChildCase.class);
        assertNotNull(updatedMissingChildCase);
    }

    @Test
    public void testDeleteMissingChildCase() {
        int id = 2;
        MissingChildCase missingchildcase = restTemplate.getForObject(getRootUrl() + "/missingchildcase/" + id, MissingChildCase.class);
        assertNotNull(missingchildcase);

        restTemplate.delete(getRootUrl() + "/missingchildcase/" + id);

        try {
            missingchildcase = restTemplate.getForObject(getRootUrl() + "/missingchildcase/" + id, MissingChildCase.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}