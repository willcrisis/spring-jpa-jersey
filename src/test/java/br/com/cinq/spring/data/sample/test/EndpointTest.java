package br.com.cinq.spring.data.sample.test;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("unit")
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCities() throws InterruptedException {
        String country = "France";

        ResponseEntity<City[]> response = this.restTemplate.getForEntity("/rest/cities?country=" + country, City[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        City[] cities = response.getBody();

        Assert.assertEquals(2, cities.length);

    }
}
