package br.com.cinq.spring.data.sample.controller;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.QCity;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@Component
@Path("cities")
public class CityController {

    @Autowired
    private CityRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<City> findCities(@QueryParam("country") String countryName) {
        QCity city = QCity.city;

        BooleanExpression expression = null;
        if (countryName != null) {
            expression = city.country.name.like(String.format("%%%s%%", countryName));
        }

        return (List<City>) repo.findAll(expression);
    }
}
