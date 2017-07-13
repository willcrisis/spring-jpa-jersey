package br.com.cinq.spring.data.sample.test;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("unit")
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCities() throws Exception {
        String country = "France";

        ResponseEntity<City[]> response = this.restTemplate.getForEntity("/rest/cities?country=" + country, City[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        City[] cities = response.getBody();

        Assert.assertEquals(2, cities.length);
    }

    @Test
    public void testInsertCities() throws Exception {
        Country brazil = new Country().setId(1L); //Should be Brazil

        List<City> cities = new ArrayList<>();
        cities.add(new City().setName("Goiania").setCountry(brazil));
        cities.add(new City().setName("Salvador").setCountry(brazil));

        JSONArray json = new JSONArray();
        cities.forEach(city -> {
            try {
                json.put(new JSONObject(city.toString()));
            } catch (JSONException e) {
                Assert.fail("Error creating JSON object.");
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        ResponseEntity response = this.restTemplate.postForEntity("/rest/cities", entity, null);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        response = this.restTemplate.getForEntity("/rest/cities?country=Brazil", City[].class);

        City[] citiesFromCountry = (City[]) response.getBody();

        Assert.assertEquals(6, citiesFromCountry.length);

    }

    @Test
    public void testInsertCountries() throws Exception {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country().setName("Canada"));
        countries.add(new Country().setName("Argentina"));

        JSONArray json = new JSONArray();
        countries.forEach(country -> {
            try {
                json.put(new JSONObject(country.toString()));
            } catch (JSONException e) {
                Assert.fail("Error creating JSON object.");
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        ResponseEntity response = this.restTemplate.postForEntity("/rest/countries", entity, null);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        response = this.restTemplate.getForEntity("/rest/countries", Country[].class);

        Country[] countriesFromDatabase = (Country[]) response.getBody();

        Assert.assertEquals(5, countriesFromDatabase.length);

    }
}
